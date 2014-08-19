package com.gdxtest02.buffs;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.HitEffect;
import com.gdxtest02.effects.PurpleEffect;

public class Stun extends Buff {
	@Override
	protected void ini() {
		super.ini();
		setEffect(new PurpleEffect());
//		setEffect(new HitEffect());
		getEffect().setOffset(0, -30);
		getEffect().setScale(0.8f);
	}
	

	@Override
	public void act(Char self) {
		self.setControl(false);
	}

	public Stun() {
		super();
	
	}

	public Stun(int duration) {
		super(duration);
		
	}
	
}
