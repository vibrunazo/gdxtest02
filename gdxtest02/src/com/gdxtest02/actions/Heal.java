package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Heal extends Action {
	protected String DEFAULT_NAME = "Direct Heal";

	public Heal() {
		super();
	}

	public Heal(int value) {
		super(value);
	}
	
	@Override
	public void act(Char self, Char target) {
		self.incHp(power);
	}
}
