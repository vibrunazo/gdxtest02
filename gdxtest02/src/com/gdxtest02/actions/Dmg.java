package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Dmg extends Action {
	
	protected String DEFAULT_NAME = "Direct Damage";
	
	public Dmg() {
		super();
	}

	public Dmg(int value) {
		super(value);
	}

	@Override
	public void act(Char self, Char target) {
		target.incHp(-power);
	}
}
