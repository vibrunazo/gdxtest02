package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;

public class Heal implements Action {
	private String name;
	private int power;
	
	private int DEFAULT_POWER = 100;
	private String DEFAULT_NAME = "Direct Heal";

	public Heal() {
		power = DEFAULT_POWER;
		name = DEFAULT_NAME;
	}

	public Heal(int value) {
		power = value;
		name = DEFAULT_NAME;
	}
	
	@Override
	public void act(Char self, Char target) {
		target.incHp(-power);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setType(String name) {
		this.name = name;
		
	}

}
