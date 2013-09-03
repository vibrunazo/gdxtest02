package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Drain extends Action {
	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power);
		self.incHp(power);
	}

	@Override
	public void ini() {
		setName("Drain");
		setDescription("Drains " + getPower() + " damage and heals you.");
	}
	

	public Drain() {
		super();
	}

	public Drain(int value) {
		super(value);
	}
	
	public Drain(int value, int cooldown) {
		super(value, cooldown);
	}
}
