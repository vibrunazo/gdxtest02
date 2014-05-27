package com.gdxtest02.projectiles;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Projectile;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.IceEffect;

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
