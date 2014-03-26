package com.gdxtest02.actions;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Castup01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.effects.GreenEffect;

public class Heal extends Action {

	@Override
	public void ini() {
		setName("Direct Heal");
		setAnim(new Castup01(owner.getAnimRenderer()));
		setAnimEffect(new GreenEffect());
	}
	public void update() {
		setDescription("Heals " + getPower() + " damage.");
	}
	
	@Override
	protected void go(Char self, Char target) {
		String[] a = {"heal"};
		self.incHp(power, self, getReflect(), new Array<String>(a));
	}
	
	public float getDmgAfterRounds(int rounds) {
		return 0;
	}
	
	public float getHealAfterRounds(int rounds) {
		return power;
	}
	
	public Heal() {
		super();
	}

	public Heal(float value) {
		super(value);
	}
	
	public Heal(float value, int cooldown) {
		super(value, cooldown);
	}

	
}
