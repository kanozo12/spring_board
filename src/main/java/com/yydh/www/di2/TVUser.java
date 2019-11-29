package com.yydh.www.di2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

		TV tv = (TV) context.getBean("tv");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
