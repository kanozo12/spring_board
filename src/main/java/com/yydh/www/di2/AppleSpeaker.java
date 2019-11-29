package com.yydh.www.di2;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("==> SonySpekaer 객체 생성");
	}

	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker -- 소리 올린다");

	}

	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker -- 소리 내린다");

	}
}
