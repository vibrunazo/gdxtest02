package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;

public class Hot implements Buff {
	private String name;
	private int power;
	private int duration;
	
	private int DEFAULT_POWER = 200;
	private int DEFAULT_DURATION = 2;
	private String DEFAULT_NAME = "Hot";

	public Hot() {
		power = DEFAULT_POWER;
		duration = DEFAULT_DURATION;
		name = DEFAULT_NAME;
	}

	public Hot(int value) {
		power = value;
		duration = DEFAULT_DURATION;
		name = DEFAULT_NAME;
	}
	
	public Hot(int value, int duration) {
		power = value;
		this.duration = duration;
		name = DEFAULT_NAME;
	}
	
	@Override
	public void act(Char self) {
		self.incHp(power);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void incDuration(int delta) {
		duration += delta;
	}

}
