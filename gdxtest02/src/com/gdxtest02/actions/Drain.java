package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Drain extends Action {
	protected String name = "Drain";

	public Drain() {
		super();
		setName(name);
	}

	public Drain(int value) {
		super(value);
		setName(name);
	}
	
	@Override
	public void act(Char self, Char target) {
		target.incHp(-power);
		self.incHp(power);
	}
}
