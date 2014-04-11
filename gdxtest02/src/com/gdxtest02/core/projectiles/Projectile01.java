package com.gdxtest02.core.projectiles;

import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.Projectile;
import com.gdxtest02.core.effects.FireEffect;
import com.gdxtest02.core.effects.GreenEffect;
import com.gdxtest02.core.effects.IceEffect;

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
