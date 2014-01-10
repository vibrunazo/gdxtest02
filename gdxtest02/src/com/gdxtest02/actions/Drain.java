package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.projectiles.Projectile03;

public class Drain extends Action {
	@Override
	protected void go(Char self, Char target) {
		target.incHp(-power, self, getReflect());
		self.incHp(power, self, getReflect());
	}

	@Override
	public void ini() {
		setName("Drain");
		setAnimEffect("green");
		AnimRenderer renderer = owner.getAnimRenderer();
		setAnim(new Cast01(renderer)
		.setProjectile(new Projectile03(renderer)),
				"green");

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
