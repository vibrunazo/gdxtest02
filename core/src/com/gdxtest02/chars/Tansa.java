package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.anims.Bite01;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.PunchRight01;

public class Tansa extends Char {
	
	public Tansa() {
		super();

		setName("Tansa");
		
		addAction(new Dmg(150)).setName("Bite").setAnim(new Bite01());
		addAction(new Dmg(250, 2)).setName("FireBall")
			.setAnim(new Cast01());
		
		setColor(0.9f, 0.2f, 0.1f, 1);
		setBodyPart("mouth", "mouth_normal");
		setBodyPart("teeth", "teeth_01");

	}

}
