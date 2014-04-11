package com.gdxtest02.core.actions;

import com.gdxtest02.core.Action;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.PutBuffAction;
import com.gdxtest02.core.anims.Castup01;
import com.gdxtest02.core.anims.Punch01;
import com.gdxtest02.core.buffs.Hot;
import com.gdxtest02.core.effects.GreenEffect;

public class PutHot extends PutBuffAction {

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
