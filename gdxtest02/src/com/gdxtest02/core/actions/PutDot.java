package com.gdxtest02.core.actions;

import com.gdxtest02.core.Action;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.PutBuffAction;
import com.gdxtest02.core.anims.Cast01;
import com.gdxtest02.core.anims.Punch01;
import com.gdxtest02.core.buffs.Dot;

public class PutDot extends PutBuffAction {

	@Override
	protected void go(Char self, Char target) {
		Dot buff = new Dot(power, duration, getTypeList());
		target.addBuff(buff.setName("Dot"));
		
	}

	@Override
	public void ini() {
		setName("Put Dot");
		setAnim(new Cast01(owner.getAnimRenderer()));
	}
	
	public void update() {
		setDescription("Deals " + getPower() + " damage per sec for " + getDuration() + 
				"sec.");
	}
	
	public float getDmgAfterRounds(int rounds) {
		return power*Math.min(duration, rounds-1);
	}

	public PutDot() {
		super();
	}

	public PutDot(float value) {
		super(value);
	}
	
	public PutDot(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutDot(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}
}
