package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Hot;

public class PutHot extends Action {

	@Override
	protected void go(Char self, Char target) {
		Hot buff = new Hot(power, duration);
		self.addBuff(buff);
	}

	@Override
	protected void ini() {
		setName("Put Hot");
		setDescription("Heals " + getPower() + " per sec for " + getDuration() + 
				"sec.");		
	}
	
	
	public PutHot() {
		super();
	}

	public PutHot(int value) {
		super(value);
	}
	
	public PutHot(int value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutHot(int value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}

}
