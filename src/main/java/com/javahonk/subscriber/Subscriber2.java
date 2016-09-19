package com.javahonk.subscriber;

import com.google.common.eventbus.Subscribe;

public class Subscriber2 {
	
	@Subscribe
	public void getPublishData(SendPostThourghClass sendPostThourghClass){
		System.out.println("Got post data on Subscriber2: "+sendPostThourghClass.getName());
	}

}
