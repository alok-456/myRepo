package com.javahonk.eventbusprocessor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.google.common.eventbus.EventBus;

public class EventBusSubscriberProcessorShorten implements BeanPostProcessor {
	
	private static final Logger LOGGER = LogManager.getLogger(EventBusSubscriberProcessorShorten.class.getName());
	
	@Autowired
    private EventBus eventBus;

	@Override
	public Object postProcessAfterInitialization(Object beanObject, String beanObjectName)
			throws BeansException {
		return beanObject;
	}

	@Override
	public Object postProcessBeforeInitialization(Object beanObject, String beanObjectName)
			throws BeansException {
		eventBus.register(beanObject);
		LOGGER.info("Bean {} has been subscribed to the EventBus.", new Object[] { beanObjectName });
		return beanObject;
	}

}
