package com.javahonk.eventbusprocessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusSubscriberProcessor implements BeanPostProcessor {
	
	private static final Logger LOGGER = LogManager.getLogger(EventBusSubscriberProcessor.class.getName());
	
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
		
		Method[] methods = beanObject.getClass().getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			
			for (Annotation methodAnnotation : annotations) {
				if (methodAnnotation.annotationType().equals(Subscribe.class)) {
					eventBus.register(beanObject);
					LOGGER.info("Bean {} method {} has been subscribed to the EventBus.", new Object[] { beanObjectName, method.getName() });
					return beanObject;
				}
			}
		}

		return beanObject;
	}

}
