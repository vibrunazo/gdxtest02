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

	public void act(Char self, Char target) {
		super.act();
		if (getCurcooldown() > 0) return;
		target.incHp(-power);
	}
}
