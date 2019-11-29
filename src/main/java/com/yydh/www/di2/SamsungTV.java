package com.yydh.www.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("tv")
public class SamsungTV implements TV {

	@Autowired
	@Qualifier("sonySpeaker")
	public Speaker speaker;
	@Value("1500000")
	public int price;

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성");
	}

	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
		System.out.println("==> SamsungTV(2) 객체생성");
	}

	public SamsungTV(Speaker speaker, int price) {
		super();
		this.speaker = speaker;
		this.price = price;
		System.out.println("==> SamsungTV(3) 객체생성");
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV --전원을 킵니다." + price); 

	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV --전원을 끕니다.");

	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();

	}

	@Override
	public void volumeDown() {
		speaker.volumeDown();

	}

}
