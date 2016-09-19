package com.javahonk.subscriber;

import com.google.common.eventbus.Subscribe;

public class Subscriber1 {
	
	@Subscribe
	public void getPublishData(SendPostThourghClass sendPostThourghClass){
		System.out.println("Got post data on Subscriber1: "+sendPostThourghClass.getName());
	}

}
