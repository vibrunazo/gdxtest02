package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Castup01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.buffs.Hot;
import com.gdxtest02.effects.GreenEffect;

public class PutHot extends Action {

	@Override
	protected void go(Char self, Char target) {
		Hot buff = new Hot(power, duration);
		self.addBuff(buff.setName("Hot"));
	}

	@Override
	public void ini() {
		setName("Put Hot");
		setAnim(new Castup01(owner.getAnimRenderer()));
		setAnimEffect(new GreenEffect());
	}
	public void update() {
		setDescription("Heals " + getPower() + " per sec for " + getDuration() + 
				"sec.");
	}
	
	
	public PutHot() {
		super();
	}

	public PutHot(float value) {
		super(value);
	}
	
	public PutHot(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutHot(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}

}
