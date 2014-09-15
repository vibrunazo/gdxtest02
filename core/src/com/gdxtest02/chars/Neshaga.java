package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Neutral;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutResistBuff;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.anims.TailWhip01;
import com.gdxtest02.anims.Taunt01;

public class Neshaga extends Char {
	
	public Neshaga() {
		super();

		setName("Neshaga");
		
		addAction(new Dmg(120)).setName("Punch");
		addAction(new Dmg(300, 2)).setName("Strong Punch")
		.setAnim(new PunchRight01());
		
		PutResistBuff a = new PutResistBuff(0.9f, 5, 5);
		a.setName("rock");
		a.addBuffType((new String[] {"fire", "normal"}));
		a.addType(new String[] {"rock"});
		addAction(a);
		
		
		setColor(0.9f, 0.8f, 0.8f, 1);
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");

	}

}
