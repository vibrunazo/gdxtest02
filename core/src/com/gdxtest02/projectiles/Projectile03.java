package com.gdxtest02.projectiles;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Projectile;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.IceEffect;
import com.gdxtest02.effects.SpineBaseEffect;

public class Projectile03 extends Projectile {

	public Projectile03(AnimRenderer renderer) {
		super(renderer);
	}

	@Override
	public void start() {
//		effect = new SpineBaseEffect();
		effect = new GreenEffect();
		
		super.start();
	}
	

}
