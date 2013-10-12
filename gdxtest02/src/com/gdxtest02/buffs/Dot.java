package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;

public class Dot extends Buff {
	@Override
	public void act(Char self) {
		self.incHp(-power);
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
