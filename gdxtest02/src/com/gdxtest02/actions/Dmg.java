package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Dmg extends Action {
	protected String name = "DD";
	
	public Dmg() {
		super();
		setName(name);
	}

	public Dmg(int value) {
		super(value);
		setName(name);
	}
	
	public Dmg(int value, int cooldown) {
		super(value, cooldown);
		setName(name);
	}

	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power);
	}

}
