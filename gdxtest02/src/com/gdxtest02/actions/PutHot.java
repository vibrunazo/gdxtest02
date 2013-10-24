package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Hot;

public class PutHot extends Action {

	@Override
	protected void go(Char self, Char target) {
		Hot buff = new Hot(power, duration);
		self.addBuff(buff.setName("Hot"));
	}

	@Override
	public void ini() {
		setName("Put Hot");
		setAnim("castup");
		setAnimEffect("green");
	}
	public void update() {
		setDescription("Heals " + getPower() + " per sec for " + getDuration() + 
				"sec.");
	}
	
	
	public PutHot() {
		super();
	}

	public PutHot(float value) {
		super(value);
	}
	
	public PutHot(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutHot(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}

}
