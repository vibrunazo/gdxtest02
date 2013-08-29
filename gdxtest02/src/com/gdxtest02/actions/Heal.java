package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Heal implements Action {
	private String type;
	private int power;

	public Heal() {
		power = 200;
	}

	public Heal(int value) {
		power = value;
	}
	
	@Override
	public void act(Char self, Char target) {
		self.incHp(power);
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
		
	}

}
