package com.gdxtest02.actions;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.PutBuffAction;
import com.gdxtest02.anims.Castup01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.buffs.BuffDmg;
import com.gdxtest02.buffs.BuffResist;


public class PutResistBuff extends PutBuffAction {
	private Array<String> bufftype;
	
	@Override
	protected void go(Char self, Char target) {
		Buff b = getNewBuffInstance();
		self.addBuff(b.setName("Resist Buff"));
		
	}

	@Override
	public void ini() {
		setName("Put Resist Buff");
		setAnim(new Castup01(owner.getAnimRenderer()));
		setBuff(new BuffResist(power, duration, bufftype));
	}
	
	 	
	public void update() {
		setDescription("increases " + bufftype.toString() + " resists by " + getPower()*100 + "%" + " for " + getDuration() + 
				"sec.");
	}
	
	
	public PutResistBuff() {
		super();
	}

	public PutResistBuff(float value) {
		super(value);
	}
	
	public PutResistBuff(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutResistBuff(float d, int cooldown, int duration) {
		super(d, cooldown, duration);
	}
	
	public void addBuffType(String[] strings){
		if (bufftype == null)
			bufftype = new Array<String>();
		bufftype.addAll(strings);
	}

}


