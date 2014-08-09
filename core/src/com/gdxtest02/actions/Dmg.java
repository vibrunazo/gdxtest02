package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Punch01;

public class Dmg extends Action implements Cloneable {
	
	public void ini() {
		setName("DD");
		setAnim(new Punch01());
		setReflect(true);
	}
	public void update() {
		setDescription("Does " + getPower() + " direct damage to the enemy.");
	}

	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power, self, getReflect(), this.getTypeList());
	}
	
	public float getDmgAfterRounds(int rounds) {
		return power;
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
