package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Hot;

public class PutHot extends Action {
	protected String name = "Put Hot";

	public PutHot() {
		super();
		setName(name);
	}

	public PutHot(int value) {
		super(value);
		setName(name);
	}
	
	public PutHot(int value, int duration) {
		super(value, duration);
		setName(name);
	}
	
	@Override
	public void act(Char self, Char target) {
		Hot buff = new Hot(power, duration);
		self.addBuff(buff);
	}

}
