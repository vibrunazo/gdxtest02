package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Dot;

public class PutDot implements Action {
	private String name;
	private int power;
	private int duration;
	
	private int DEFAULT_POWER = 200;
	private int DEFAULT_DURATION = 2;
	private String DEFAULT_NAME = "Put Dot";

	public PutDot() {
		power = DEFAULT_POWER;
		duration = DEFAULT_DURATION;
		name = DEFAULT_NAME;
	}

	public PutDot(int value) {
		power = value;
		duration = DEFAULT_DURATION;
		name = DEFAULT_NAME;
	}
	
	public PutDot(int value, int duration) {
		power = value;
		this.duration = duration;
		name = DEFAULT_NAME;
	}
	
	@Override
	public void act(Char self, Char target) {
		Dot buff = new Dot(power, duration);
		target.addBuff(buff);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setType(String name) {
		this.name = name;
		
	}

}
