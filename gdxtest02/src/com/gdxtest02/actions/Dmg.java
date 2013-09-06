package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Dmg extends Action {
	
	public void ini() {
		setName("DD");
		setDescription("Does " + getPower() + " direct damage to the enemy.");
		setAvgDps(power/(cooldown+1));
	}

	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power);
	}
	
	
	public Dmg() {
		super();
	}
	public Dmg(float value) {
		super(value);
	}
	
	public Dmg(float value, int cooldown) {
		super(value, cooldown);
	}

}
