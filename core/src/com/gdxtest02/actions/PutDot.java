package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.GdxTest02;
import com.gdxtest02.PutBuffAction;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.buffs.Dot;
import com.gdxtest02.util.Util;

public class PutDot extends PutBuffAction {
	Buff bufftype;

	@Override
	protected void go(Char self, Char target) {
		Buff buff = Util.copy(bufftype);
		target.addBuff(buff.setName("Dot"));
		
	}

	@Override
	public void ini() {
		setName("Put Dot");
		setAnim(new Cast01(owner.getAnimRenderer()));
		bufftype = new Dot(power, duration, getTypeList());
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
