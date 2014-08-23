package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.anims.TailWhip01;

public class Pasu extends Char {
	
	public Pasu() {
		super();

		setName("Pasu");
		
		addAction(new Dmg(120)).setName("Punch");
		addAction(new Dmg(200, 1)).setName("Strong Punch")
		.setAnim(new PunchRight01());
		addAction(new Dmg(380, 4)).setName("Tail whip")
		.setAnim(new TailWhip01());
		
		setColor(0.5f, 0.35f, 0.05f, 1);
		setBodyPart("tail", "tail_normal");
		getAnimData().setTailscale(1.5f, 2f);
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");

	}

}
