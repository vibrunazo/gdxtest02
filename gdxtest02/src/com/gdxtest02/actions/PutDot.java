package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Dot;

public class PutDot extends Action {

	@Override
	protected void go(Char self, Char target) {
		Dot buff = new Dot(power, duration);
		target.addBuff(buff);
	}

	@Override
	protected void ini() {
		setName("Put Dot");
		setDescription("Deals " + getPower() + " damage per sec for " + getDuration() + 
				"sec.");
	}
	

	public PutDot() {
		super();
	}

	public PutDot(int value) {
		super(value);
	}
	
	public PutDot(int value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutDot(int value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}
}
