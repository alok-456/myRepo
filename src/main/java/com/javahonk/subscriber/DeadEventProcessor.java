package com.javahonk.subscriber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

public class DeadEventProcessor {

	private static final Logger logger = LogManager.getLogger(DeadEventProcessor.class.getName());
	
	@Subscribe
	public void processDeadEvent(DeadEvent deadEvent){
		
		logger.error("DEADEVENT DETECTED:{}",deadEvent.getEvent().getClass());
		
	}
}
