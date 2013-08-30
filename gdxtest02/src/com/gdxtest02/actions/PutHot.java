package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Hot;

public class PutHot extends Action {
	protected String DEFAULT_NAME = "Put Hot";

	public PutHot() {
		super();
	}

	public PutHot(int value) {
		super(value);
	}
	
	public PutHot(int value, int duration) {
		super(value, duration);
	}
	
	@Override
	public void act(Char self, Char target) {
		Hot buff = new Hot(power, duration);
		self.addBuff(buff);
	}

}
