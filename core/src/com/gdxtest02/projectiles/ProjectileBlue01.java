package com.gdxtest02.projectiles;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Projectile;
import com.gdxtest02.effects.BlueEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.IceEffect;

public class ProjectileBlue01 extends Projectile {

	public ProjectileBlue01() {
		super();
	}

	@Override
	public void start(AnimRenderer renderer) {
		effect = new BlueEffect();
		
		super.start(renderer);
	}
	

}
