package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Dmg implements Action {
	private String type;
	private int power;

	public Dmg() {
		power = 200;
	}

	public Dmg(int value) {
		power = value;
	}
	
	@Override
	public void act(Char self, Char target) {
		target.incHp(-power);
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
