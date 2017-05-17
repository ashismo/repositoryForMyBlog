package com.ashish.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.ashish.spring.batch.listener.BatchJobCompletionListener;
import com.ashish.spring.batch.step.BatchItemProcessor;
import com.ashish.spring.batch.step.BatchItemReader;
import com.ashish.spring.batch.step.BatchItemWriter;

@Configuration
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job processJob;

	// This job runs in every 5 seconds
	@Scheduled(fixedRate = 5000)
	public void printMessage() {
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong(
					"time", System.currentTimeMillis()).toJobParameters();
			jobLauncher.run(processJob, jobParameters);
			System.out.println("I have been scheduled with Spring scheduler");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	/**
	 * To create a step, reader, processor and writer has been passed serially
	 * 
	 * @return
	 */
	@Bean
	public Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
				.reader(new BatchItemReader())
				.processor(new BatchItemProcessor())
				.writer(new BatchItemWriter()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new BatchJobCompletionListener();
	}

	@Bean
	public ResourcelessTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

}