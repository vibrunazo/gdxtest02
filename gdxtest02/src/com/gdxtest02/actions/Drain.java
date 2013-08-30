package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Drain extends Action {
	protected String DEFAULT_NAME = "Drain";

	public Drain() {
		super();
	}

	public Drain(int value) {
		super(value);
	}
	
	@Override
	public void act(Char self, Char target) {
		target.incHp(-power);
		self.incHp(power);
	}
}
