package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Heal extends Action {

	@Override
	public void ini() {
		setName("Direct Heal");
		setAnim("castup02");
	}
	public void update() {
		setDescription("Heals " + getPower() + " damage.");
	}
	
	@Override
	protected void go(Char self, Char target) {
		self.incHp(power, self, getReflect());
	}
	
	public float getDmgAfterRounds(int rounds) {
		return 0;
	}
	
	public float getHealAfterRounds(int rounds) {
		return power;
	}
	
	public Heal() {
		super();
	}

	public Heal(float value) {
		super(value);
	}
	
	public Heal(float value, int cooldown) {
		super(value, cooldown);
	}

	
}
