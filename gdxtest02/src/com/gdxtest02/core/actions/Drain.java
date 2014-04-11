package com.gdxtest02.core.actions;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.core.Action;
import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.anims.Cast01;
import com.gdxtest02.core.anims.Punch01;
import com.gdxtest02.core.effects.GreenEffect;
import com.gdxtest02.core.projectiles.Projectile03;

public class Drain extends Action {
	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power, self, getReflect(), this.getTypeList());
		String[] a = {"heal"};
		self.incHp(power, self, getReflect(), new Array<String>(a));
	}

	@Override
	public void ini() {
		setName("Drain");
		setAnimEffect(new GreenEffect());
		AnimRenderer renderer = owner.getAnimRenderer();
		setAnim(new Cast01(renderer)
			.setProjectile(new Projectile03(renderer)),
			new GreenEffect());

	}
	public void update() {
		setDescription("Drains " + getPower() + " damage and heals you.");
	}
	
	
	public float getDmgAfterRounds(int rounds) {
		return power;
	}
	
	public float getHealAfterRounds(int rounds) {
		return power;
	}
	

	public Drain() {
		super();
	}

	public Drain(float value) {
		super(value);
	}
	
	public Drain(float value, int cooldown) {
		super(value, cooldown);
	}
}
