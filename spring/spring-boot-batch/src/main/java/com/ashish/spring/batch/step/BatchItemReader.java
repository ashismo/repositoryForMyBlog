package com.ashish.spring.batch.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BatchItemReader implements ItemReader<String> {

	private String[] messages = { "Hello World",
			"Welcome to Spring Batch using Spring boot Example",
			"H2 Database has been used in this example" };

	private int count = 0;

//	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		return null;
	}

}
