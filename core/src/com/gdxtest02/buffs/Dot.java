package com.gdxtest02.buffs;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;

public class Dot extends Buff {
	@Override
	protected void ini() {
		super.ini();
		effect = new FireEffect();
	}
	private Array<String> type;

	@Override
	public void act(Char self) {
		self.incHp(-power, self, false, type );
	}

	public Dot() {
		super();
		type = new Array<String>();
	}
	
	public Dot(Array<String> typelist){
		super();
		type = typelist;
	}

	public Dot(float value, Array<String> typelist) {
		super(value);
		type = typelist;
	}
	
	public Dot(float value, int duration, Array<String> typelist) {
		super(value, duration);
		type = typelist;
	}
	
}
