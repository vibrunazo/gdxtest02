package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Dmg extends Action implements Cloneable {
	
	public void ini() {
		setName("DD");
		setAnim("punch");
	}
	public void update() {
		setDescription("Does " + getPower() + " direct damage to the enemy.");
	}

	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power);
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
