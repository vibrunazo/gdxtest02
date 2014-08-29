package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Punch01;

public class Dispel extends Action implements Cloneable {
	
	public void ini() {
		setName("Dispel");
		setAnim(new Punch01());
		
	}
	public void update() {
		setDescription("Does " + getPower() + " direct damage to the enemy.");
	}

	@Override
	protected void go(Char self, Char target) {
	self.removeBuffType("fire");
	}
	
	
	public Dispel() {
		super();
	}
	
	public Dispel( int cooldown) {
		super(cooldown);
	}
	
}
