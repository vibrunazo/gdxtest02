package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Heal extends Action {
	protected String name = "Direct Heal";

	public Heal() {
		super();
		setName(name);
	}

	public Heal(int value) {
		super(value);
		setName(name);
	}
	
	@Override
	public void act(Char self, Char target) {
		self.incHp(power);
	}
}
