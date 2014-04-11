package com.gdxtest02.core.buffs;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.core.Buff;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.effects.FireEffect;

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
