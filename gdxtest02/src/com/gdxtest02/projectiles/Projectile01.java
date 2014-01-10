package com.gdxtest02.projectiles;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Projectile;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.IceEffect;

public class Projectile01 extends Projectile {

	public Projectile01(AnimRenderer renderer) {
		super(renderer);
	}

	@Override
	public void start() {
		effect = new FireEffect();
		
		super.start();
	}
	

}
