package com.ashish.spring.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * This class extends JobExecutionListenerSupport and continuously listen the application change and 
 * prints the message once completed.
 * @author Ashish Mondal
 *
 */
public class BatchJobCompletionListener extends JobExecutionListenerSupport {

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
