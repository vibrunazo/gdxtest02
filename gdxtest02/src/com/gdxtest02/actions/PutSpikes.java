package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.buffs.Spikes;

public class PutSpikes extends Action{
	@Override
	protected void go(Char self, Char target) {
		Spikes buff = new Spikes(power, duration);
		self.addBuff(buff.setName("Spikes"));
		
	}

	@Override
	public void ini() {
		setName("Put Spikes");
	}
	public void update() {
		setDescription("Reflects " + getPower() + " each Direct Damage for " + getDuration() + 
				"sec.");
	}
	
	
	public PutSpikes() {
		super();
	}

	public PutSpikes(float value) {
		super(value);
	}
	
	public PutSpikes(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutSpikes(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}

}


