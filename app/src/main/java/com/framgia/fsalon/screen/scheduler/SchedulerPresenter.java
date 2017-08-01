package com.framgia.fsalon.screen.scheduler;

import com.framgia.fsalon.data.model.Scheduler;
import com.framgia.fsalon.data.model.SchedulerSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link SchedulerFragment}), retrieves the data and updates
 * the UI as required.
 */
final class SchedulerPresenter implements SchedulerContract.Presenter {
    private static final String TAG = SchedulerPresenter.class.getName();
    private final SchedulerContract.ViewModel mViewModel;

    public SchedulerPresenter(SchedulerContract.ViewModel viewModel) {
        mViewModel = viewModel;
        getSchedulers();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void getSchedulers() {
        List<SchedulerSection> sections = new ArrayList<>();
        List<Scheduler> schedulers = new ArrayList<>();
        String title = new String("12-07-2017");
        Scheduler schedulerFinish = new Scheduler("Dat chizz", true, "09:09");
        Scheduler schedulerCancel = new Scheduler("Go Yun Pio", false, "00:00");
        SchedulerSection section = new SchedulerSection();
        /**
         * make a section
         */
        section.setTitle(title);
        schedulers.add(schedulerFinish);
        schedulers.add(schedulerCancel);
        schedulers.add(schedulerFinish);
        section.setSections(schedulers);
        sections.add(section);
        sections.add(section);
        mViewModel.onSchedulerSuccessful(sections);
    }
}
