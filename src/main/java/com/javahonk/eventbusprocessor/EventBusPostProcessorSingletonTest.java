package com.javahonk.eventbusprocessor;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusPostProcessorSingletonTest implements BeanPostProcessor
{
	@Autowired private ApplicationContext context;
    @Autowired private EventBus eventBus;
	
	private static final Logger LOGGER = LogManager.getLogger(EventBusSubscriberProcessor.class.getName());

    @Override
    public Object postProcessBeforeInitialization(Object beanObject, String beanObjectName) throws BeansException
    {
        return beanObject;
    }

    @Override
    public Object postProcessAfterInitialization(Object beanObject, String beanObjectName)
                  throws BeansException
    {
        //If bean is singleton register with event bus
    	if(context.isSingleton(beanObjectName)) {
            eventBus.register(beanObject);
            LOGGER.info("Bean {} method {} has been subscribed to the EventBus.", new Object[] { beanObjectName});			
        } else {        	
        	LOGGER.info("Bean {} method {} has not been subscribed to the EventBus because it's not Singleton class.", new Object[] { beanObjectName});	
            if(isSubscribe(beanObject)) {
				LOGGER.warn("Bean {} class method @Subscribe annotation not registered with EventBus "
						+ "because it's not Singleton class and will create memory leak. "
						+ "Please change your bean class to singleton by making it's scope singleton.",
						beanObjectName);
			}
        }
        return beanObject;
    }
    
    public static boolean isSubscribe(Object beanObject)
    {
        Method[] methods = beanObject.getClass().getMethods();
        for(Method method : methods)
        {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if(subscribe != null)
            {
                return true;
            }
        }
        return false;
    }
    
    
}