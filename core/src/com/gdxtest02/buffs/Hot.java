package com.gdxtest02.buffs;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;

public class Hot extends Buff {
	
	@Override
	protected void ini() {
		super.ini();
		effect = new GreenEffect();
		effect.setOffset(0, 10);
	}

	@Override
	public void act(Char self) {
		String[] a = {"heal"};
		self.incHp(power, self, false, new Array<String>(a));
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
