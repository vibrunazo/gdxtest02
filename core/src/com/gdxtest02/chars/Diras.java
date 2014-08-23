package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.anims.Bite01;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.Cast02;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.effects.IceEffect;
import com.gdxtest02.projectiles.Projectile02;

public class Diras extends Char {
	
	public Diras() {
		super();
		
		setName("Diras");
		
		addAction(new Dmg(80)).setName("Bite").setAnim(new Bite01());
		addAction(new Dmg(350, 2)).setName("Frost Bolt").setReflect(false)
		.setAnim(new Cast02()
		.setProjectile(new Projectile02()))
		.setAnimEffect(new IceEffect());
		
		setColor(0.3f, 0.5f, 0.9f, 1);
		setBodyPart("mouth", "mouth_normal");

	}

}
