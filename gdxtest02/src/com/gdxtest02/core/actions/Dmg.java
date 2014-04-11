package com.gdxtest02.core.actions;

import com.gdxtest02.core.Action;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.anims.Punch01;

public class Dmg extends Action implements Cloneable {
	
	public void ini() {
		setName("DD");
		setAnim(new Punch01(owner.getAnimRenderer()));
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
	
	public Action getClone() {
		try {
			Action clone = (Action) this.clone(); 
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
