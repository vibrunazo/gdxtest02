package com.gdxtest02.core.projectiles;

import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.Projectile;
import com.gdxtest02.core.effects.GreenEffect;
import com.gdxtest02.core.effects.IceEffect;

public class Projectile02 extends Projectile {

	public Projectile02(AnimRenderer renderer) {
		super(renderer);
	}

	@Override
	public void start() {
		effect = new IceEffect();
		
		super.start();
	}
	

}
