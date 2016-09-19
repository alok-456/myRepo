package com.javahonk.subscriber;

import com.google.common.eventbus.Subscribe;

public class Subscriber3NotSingleton {
	
	@Subscribe
	public void getPublishData(SendPostThourghClass sendPostThourghClass){
		System.out.println("Got post data on Subscriber3NotSingleton: "+sendPostThourghClass.getName());
	}

}
