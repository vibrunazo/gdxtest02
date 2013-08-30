package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Dot;

public class PutDot extends Action {
	protected String DEFAULT_NAME = "Put Dot";

	public PutDot() {
		super();
	}

	public PutDot(int value) {
		super(value);
	}
	
	public PutDot(int value, int duration) {
		super(value, duration);
	}
	
	@Override
	public void act(Char self, Char target) {
		Dot buff = new Dot(power, duration);
		target.addBuff(buff);
	}

}
