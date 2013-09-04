package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Dmg extends Action {
	
	protected void ini() {
		setName("DD");
		setDescription("Does " + getPower() + " direct damage to the enemy.");
	}

	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power);
	}
	
	
	public Dmg() {
		super();
	}
	public Dmg(int value) {
		super(value);
	}
	
	public Dmg(int value, int cooldown) {
		super(value, cooldown);
	}

}
