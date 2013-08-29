package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Dot;

public class PutDot implements Action {
	private String type;
	private int power;
	private int duration;
	
	private int DEFAULT_POWER = 200;
	private int DEFAULT_DURATION = 2;

	public PutDot() {
		power = DEFAULT_POWER;
		duration = DEFAULT_DURATION;
	}

	public PutDot(int value) {
		power = value;
		duration = DEFAULT_DURATION;
	}
	
	public PutDot(int value, int duration) {
		power = value;
		this.duration = duration;
	}
	
	@Override
	public void act(Char self, Char target) {
		Dot buff = new Dot(power, duration);
		target.addBuff(buff);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
		
	}

}
