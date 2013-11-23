package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Castup01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.buffs.BuffDmg;


public class PutDmgBuff extends Action{
	@Override
	protected void go(Char self, Char target) {
		BuffDmg buff = new BuffDmg(power, duration);
		self.addBuff(buff.setName("Dmg Buff"));
		
	}

	@Override
	public void ini() {
		setName("Put Dmg Buff");
		setAnim(new Castup01(owner.getAnimRenderer()));
	}
	public void update() {
		setDescription("doubles power by " + getPower() + " for " + getDuration() + 
				"sec.");
	}
	
	
	public PutDmgBuff() {
		super();
	}

	public PutDmgBuff(float value) {
		super(value);
	}
	
	public PutDmgBuff(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutDmgBuff(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}

}


