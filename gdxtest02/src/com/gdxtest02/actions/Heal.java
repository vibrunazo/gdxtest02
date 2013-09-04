package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Heal extends Action {

	@Override
	public void ini() {
		setName("Direct Heal");
		setDescription("Heals " + getPower() + " damage.");
		setAvgDps(power/(cooldown+1));
	}
	
	@Override
	protected void go(Char self, Char target) {
		self.incHp(power);
	}
	
	
	
	public Heal() {
		super();
	}

	public Heal(int value) {
		super(value);
	}
	
	public Heal(int value, int cooldown) {
		super(value, cooldown);
	}

	
}
