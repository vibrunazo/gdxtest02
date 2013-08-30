package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Dot;
import com.gdxtest02.buffs.Hot;

public class PutHot implements Action {
	private String name;
	private int power;
	private int duration;
	
	private int DEFAULT_POWER = 200;
	private int DEFAULT_DURATION = 2;
	private String DEFAULT_NAME = "Put Hot";

	public PutHot() {
		power = DEFAULT_POWER;
		duration = DEFAULT_DURATION;
		name = DEFAULT_NAME;
	}

	public PutHot(int value) {
		power = value;
		duration = DEFAULT_DURATION;
		name = DEFAULT_NAME;
	}
	
	public PutHot(int value, int duration) {
		power = value;
		this.duration = duration;
		name = DEFAULT_NAME;
	}
	
	@Override
	public void act(Char self, Char target) {
		Hot buff = new Hot(power, duration);
		self.addBuff(buff);
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
