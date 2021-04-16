package com.andromate.Services;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class JobSchedulerService  extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}