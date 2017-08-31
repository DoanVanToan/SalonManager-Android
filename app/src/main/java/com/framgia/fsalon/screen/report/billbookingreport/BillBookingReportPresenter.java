package com.framgia.fsalon.screen.report.billbookingreport;

import com.framgia.fsalon.data.model.BillReportResponse;
import com.framgia.fsalon.data.model.Report;
import com.framgia.fsalon.data.source.ReportRepository;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link BillBookingReportFragment}),
 * retrieves the data and updates the UI as required.
 */
final class BillBookingReportPresenter implements BillBookingReportContract.Presenter {
    private static final String TAG = BillBookingReportPresenter.class.getName();
    private final BillBookingReportContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ReportRepository mReportRepository;

    BillBookingReportPresenter(BillBookingReportContract.ViewModel viewModel,
                                      ReportRepository reportRepository, String type, int status,
                                      long start, long end) {
        mViewModel = viewModel;
        mReportRepository = reportRepository;
        getReport(type, status, start, end);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getReport(String type, int status, long start, long end) {
        Disposable disposable = mReportRepository.getBillReport(type, status, start, end)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<BillReportResponse>() {
                @Override
                public void onNext(@NonNull BillReportResponse billReportResponse) {
                    List<Entry> barList = new ArrayList<>();
                    List<Report> reportList = billReportResponse.getData();
                    List<String> labels = new ArrayList<>();
                    for (Report report : reportList) {
                        int position = reportList.indexOf(report);
                        labels.add(report.getLabel());
                        barList.add(new Entry(position, report.getValue()));
                    }
                    mViewModel
                        .onGetReportSuccess(barList, billReportResponse.getTotalSale(), labels);
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
