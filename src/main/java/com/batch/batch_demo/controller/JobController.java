package com.batch.batch_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PostMapping("/data")
    public String jobLauncher()  {

        final JobParameters jobParameters= new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis()).toJobParameters();

        //execute the Job
        final JobExecution jobExecution;
        try {
            jobExecution = jobLauncher.run( job, jobParameters);
            return jobExecution.getStatus().toString();
        } catch (JobExecutionAlreadyRunningException |JobRestartException
                 |JobInstanceAlreadyCompleteException |JobParametersInvalidException e) {

           e.printStackTrace();
            return "Job failed with exception: " + e.getMessage();
        }




    }

}
