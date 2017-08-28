package com.framgia.fsalon.screen.report.customerreport;

import com.framgia.fsalon.data.model.CustomerReportResponse;
import com.framgia.fsalon.data.model.Report;
import com.framgia.fsalon.data.source.ReportRepository;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link CustomerReportFragment}),
 * retrieves the data and updates the UI as required.
 */
final class CustomerReportPresenter implements CustomerReportContract.Presenter {
    private static final String TAG = CustomerReportPresenter.class.getName();
    private final CustomerReportContract.ViewModel mViewModel;
    private ReportRepository mReportRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    CustomerReportPresenter(CustomerReportContract.ViewModel viewModel,
                            ReportRepository reportRepository, String type, long start, long end) {
        mViewModel = viewModel;
        mReportRepository = reportRepository;
        getReport(type, start, end);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getReport(String type, long start, long end) {
        Disposable disposable = mReportRepository.getBookingReport(type, start, end)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<CustomerReportResponse>() {
                @Override
                public void onNext(@NonNull CustomerReportResponse customerReportResponse) {
                    List<BarEntry> barList = new ArrayList<>();
                    List<Report> reportList = customerReportResponse.getData();
                    List<String> labels = new ArrayList<>();
                    for (Report report : reportList) {
                        int position = reportList.indexOf(report);
                        labels.add(report.getLabel());
                        barList.add(new BarEntry(position,
                            new float[]{report.getOldCustomer(), report.getNewCustomer()}));
                    }
                    mViewModel
                        .onGetReportSuccess(barList, customerReportResponse.getOldCustomer(),
                            customerReportResponse.getNewCustomer(), labels);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onGetReportFail(e.getMessage());
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
