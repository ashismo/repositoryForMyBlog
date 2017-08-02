package com.ashish.learning;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor{

	/**
	 * This gets executed after initialization of the bean
	 * arg0 - Actual bean instance
	 * arg1 - Bean name
	 */
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("This gets executed after initialization of the bean: " + arg1);
		return arg0;
	}

	/**
	 * This gets executed before initialization of the bean
	 * arg0 - Actual bean instance
	 * arg1 - Bean name
	 */
	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		System.out.println("This gets executed before initialization of the bean: " + arg1);
		return arg0;
	}

}
