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
	public void ini() {
		setName("Put Dot");
		setDescription("Deals " + getPower() + " damage per sec for " + getDuration() + 
				"sec.");
		setAvgDps((power*duration)/(cooldown+1));
	}
	
	public float getDmgAfterRounds(int rounds) {
		return power*Math.min(duration, rounds-1);
	}

	public PutDot() {
		super();
	}

	public PutDot(float value) {
		super(value);
	}
	
	public PutDot(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutDot(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}
}
