package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.anims.Taunt02;

public class Neutral extends Action implements Cloneable {
	
	public void ini() {
		setName("Taunt");
		setAnim(new Taunt02());
	}
	public void update() {
		setDescription("Does nothing");
	}

	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power, self, getReflect(), this.getTypeList());
	}
	
	public Neutral() {
		super(0);
	}
	public Neutral(int cooldown) {
		super(0, cooldown);
	}
	
}
