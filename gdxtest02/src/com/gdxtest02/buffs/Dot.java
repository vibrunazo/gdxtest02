package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;

public class Dot implements Buff {
	private String type;
	private int power;
	private int duration;

	public Dot() {
		power = 200;
		duration = 5;
	}

	public Dot(int value) {
		power = value;
		duration = 5;
	}
	
	public Dot(int value, int duration) {
		power = value;
		this.duration = duration;
	}
	
	@Override
	public void act(Char self) {
		self.incHp(-power);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
		
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
