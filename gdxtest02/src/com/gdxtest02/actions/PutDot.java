package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Dot;

public class PutDot extends Action {
	protected String name = "Put Dot";

	public PutDot() {
		super();
		setName(name);
	}

	public PutDot(int value) {
		super(value);
		setName(name);
	}
	
	public PutDot(int value, int cooldown) {
		super(value, cooldown);
		setName(name);
	}
	
	public PutDot(int value, int cooldown, int duration) {
		super(value, cooldown);
		setDuration(duration);
		setName(name);
	}
	
	public void act(Char self, Char target) {
		Dot buff = new Dot(power, duration);
		target.addBuff(buff);
	}

}
