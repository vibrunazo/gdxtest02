package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;

public class Hot extends Buff {
	
	@Override
	protected void ini() {
		super.ini();
		effect = new GreenEffect();
	}

	@Override
	public void act(Char self) {
		self.incHp(power, self, false);
	}
	
	public Hot() {
		super();
	}

	public Hot(float value) {
		super(value);
	}
	
	public Hot(float value, int duration) {
		super(value, duration);
	}
}
