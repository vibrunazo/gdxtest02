package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.DispelTargetByType;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Neutral;
import com.gdxtest02.anims.Bite01;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.Cast02;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.anims.Taunt02;
import com.gdxtest02.effects.BlueEffect;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.IceEffect;
import com.gdxtest02.projectiles.Projectile02;
import com.gdxtest02.projectiles.ProjectileBlue01;

public class Goala extends Char {
	
	public Goala() {
		super();
		
		setName("Goala");
		
		addAction(new Dmg(120)).setName("Bite").setAnim(new Bite01())
			.addType(new String[] {"normal", "melee"});
//		addAction(new Dmg(150, 1))
//		addAction(new DispelTargetByType(0)).setName("dispel").addType(new String[] {"fire", "heal", "normal"});;
		addAction(new DispelTargetByType(3))
		.setName("Water Bolt").setReflect(false)
		.setAnim(new Cast01()
		.setProjectile(new ProjectileBlue01()))
		.setAnimEffect(new BlueEffect())
		.addType(new String[] {"fire", "rock"});
		
		addAction(new Neutral()).setName("Taunt")
		.setAnim(new Taunt02());
		
		setColor(0.2f, 0.3f, 0.8f, 1);
		setBodyPart("mouth", "mouth_normal");
		setBodyPart("arms", "arms_cannon");
//		setBodyPart("hands", "hands_normal");

	}

}
