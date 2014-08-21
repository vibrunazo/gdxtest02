package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.anims.SlashDown01;
import com.gdxtest02.effects.BleedingEffect;
import com.gdxtest02.effects.BloodEffect;
import com.gdxtest02.effects.RedEffect;

public class Linzer extends Char {
	
	public Linzer() {
		super();

		setName("Linzer");
		
		addAction(new Dmg(120)).setName("Punch");
		addAction(new PutDot(180, 2).setBuffEffect(new BleedingEffect()))
			.setName("Bleed")
			.setAnim(new SlashDown01());
		
		setColor(0.7f, 0.6f, 0.2f, 1);
		setBodyPart("hands", "hands_scythe");
		setBodyPart("tail", "tail_normal");
		getAnimData().setTailscale(0.8f, 0.5f);

	}

}
