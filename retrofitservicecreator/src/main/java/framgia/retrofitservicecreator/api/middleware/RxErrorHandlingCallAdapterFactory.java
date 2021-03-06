package framgia.retrofitservicecreator.api.middleware;

import framgia.retrofitservicecreator.api.error.BaseException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.HttpException;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * ErrorHandling:
 * http://bytes.babbel.com/en/articles/2016-03-16-retrofit2-rxjava-error-handling.html
 * This class only for Call in mRetrofit 2
 */
public final class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private static final String TAG = RxErrorHandlingCallAdapterFactory.class.getName();
    private final RxJava2CallAdapterFactory mOriginal;

    private RxErrorHandlingCallAdapterFactory() {
        mOriginal = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, mOriginal.get(returnType, annotations, retrofit));
    }

    /**
     * RxCallAdapterWrapper
     */
    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Object> {
        private final Retrofit retrofit;
        private final CallAdapter<R, Object> wrapped;

        public RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<R, Object> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @Override
        public Object adapt(Call<R> call) {
            Object result = wrapped.adapt(call);
            if (result instanceof Single) {
                return ((Single) result).onErrorResumeNext(new Function<Throwable, SingleSource>() {
                    @Override
                    public SingleSource apply(@NonNull Throwable throwable) throws Exception {
                        return Single.error(convertToBaseException(throwable));
                    }
                });
            }
            if (result instanceof Observable) {
                return ((Observable) result)
                    .onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                        @Override
                        public ObservableSource apply(@NonNull Throwable throwable)
                            throws Exception {
                            return Observable.error(convertToBaseException(throwable));
                        }
                    });
            }
            if (result instanceof Completable) {
                return ((Completable) result)
                    .onErrorResumeNext(new Function<Throwable, CompletableSource>() {
                        @Override
                        public CompletableSource apply(@NonNull Throwable throwable)
                            throws Exception {
                            return Completable.error(convertToBaseException(throwable));
                        }
                    });
            }
            return result;
        }

        private RetrofitException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException
                    .httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            }
            // We don't know what happened. We need to simply convert to an unknown error
            return RetrofitException.unexpectedError(throwable);
        }

        private BaseException convertToBaseException(Throwable throwable) {
            if (throwable instanceof BaseException) {
                return (BaseException) throwable;
            }
            if (throwable instanceof IOException) {
                return BaseException.toNetworkError(throwable);
            }
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                if (response.errorBody() != null) {
                    return BaseException.toServerError(throwable);
                } else {
                    return BaseException.toHttpError(response);
                }
            }
            return BaseException.toUnexpectedError(throwable);
        }
    }
}
