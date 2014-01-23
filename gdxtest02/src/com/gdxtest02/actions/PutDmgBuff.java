package com.gdxtest02.actions;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Castup01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.buffs.BuffDmg;


public class PutDmgBuff extends Action{
	private Array<String> bufftype;
	
	@Override
	protected void go(Char self, Char target) {
		BuffDmg buff = new BuffDmg(power, duration, bufftype);
		self.addBuff(buff.setName("Dmg Buff"));
		
	}

	@Override
	public void ini() {
		setName("Put Dmg Buff");
		setAnim(new Castup01(owner.getAnimRenderer()));
	}
	public void update() {
		setDescription("increases power by " + getPower()*100 + "%" + " for " + getDuration() + 
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
	
	public void addBuffType(String type){
		if (bufftype == null)
			bufftype = new Array<String>();
		bufftype.add(type);
	}

}


