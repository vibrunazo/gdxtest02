package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;

public class Dot extends Buff {
	@Override
	protected void ini() {
		super.ini();
		effect = new FireEffect();
	}

	@Override
	public void act(Char self) {
		self.incHp(-power, self, false);
	}

	public Dot() {
		super();
	}

	public Dot(float value) {
		super(value);
	}
	
	public Dot(float value, int duration) {
		super(value, duration);
	}
	
}
