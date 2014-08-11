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
import com.gdxtest02.buffs.MortalStrike;


public class PutMortalStrike extends PutBuffAction {
	private Array<String> bufftype;
	
	@Override
	protected void go(Char self, Char target) {
		Buff b = getNewBuffInstance();
		target.addBuff(b.setName("Resist Buff"));
		target.incHp(-power, self, getReflect(), this.getTypeList());
		
	}

	@Override
	public void ini() {
		setName("Put Resist Buff");
		setAnim(new Punch01());
		setBuff(new MortalStrike(power, duration, power02));
//		setReflect(true);
	}
	
	public float getDmgAfterRounds(int rounds) {
		return power;
	}
	 	
	public void update() {
		
				setDescription("Deals " + power + " damage and reduces healing by " + power + " for " + getDuration() + 
						"sec.");			
		}

	
	
	
	public PutMortalStrike() {
		super();
	}

	public PutMortalStrike(float value) {
		super(value);
	}
	
	public PutMortalStrike(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutMortalStrike(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}
	
	public PutMortalStrike(float value, int cooldown, int duration, float value02) {
		super(value, cooldown, duration, value02);
	}
	

}


