package com.gdxtest02.core.chars;

import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.actions.Dmg;
import com.gdxtest02.core.actions.Drain;
import com.gdxtest02.core.actions.PutDot;
import com.gdxtest02.core.anims.Cast01;
import com.gdxtest02.core.anims.PunchRight01;
import com.gdxtest02.core.effects.IceEffect;
import com.gdxtest02.core.projectiles.Projectile02;
import com.gdxtest02.core.projectiles.Projectile03;

public class Char03 extends Char {
	
	public Char03(String name) {
		super(name);
		
		float ratio = 0.42553192f;
		AnimRenderer renderer = getAnimRenderer();
//		float ratio = 1f;
		addAction(new Dmg(100*ratio)).setName("Yoga Fire");
		addAction(new Dmg(400*ratio, 5)).setName("Overpacarai")
			.setAnim(new PunchRight01(renderer));
		addAction(new PutDot(50*ratio, 0, 5)).setName("DotFoda").addType(new String[] {"fire"});
		addAction(new Dmg(500*ratio, 2)).setName("Frost Bolt").setReflect(false)
				.setAnim(new Cast01(renderer)
				.setProjectile(new Projectile02(renderer)))
				.setAnimEffect(new IceEffect());
				
		setTex("ball02red.png");
		
		setColor(1, 0.2f, 0.6f, 1);
		
	}

}
