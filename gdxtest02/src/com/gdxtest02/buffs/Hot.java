package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;

public class Hot extends Buff {

	@Override
	public void act(Char self) {
		self.incHp(power);
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
