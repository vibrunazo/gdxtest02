package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.PunchRight01;

public class Pasu extends Char {
	
	public Pasu() {
		super();

		setName("Pasu");
		
		addAction(new Dmg(120)).setName("Punch");
		addAction(new Dmg(250, 4)).setName("Tail whip")
			.setAnim(new PunchRight01());
		
		setColor(0.5f, 0.35f, 0.05f, 1);
		setBodyPart("tail", "tail_normal");
		getAnimData().setTailscale(2f, 1.5f);

	}

}
