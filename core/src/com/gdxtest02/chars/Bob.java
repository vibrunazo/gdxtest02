package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.anims.PunchRight01;

public class Bob extends Char {
	
	public Bob() {
		super();
		
		setName("Bob");
		AnimRenderer renderer = getAnimRenderer();
		addAction(new Dmg(120)).setName("Punch");
		addAction(new Dmg(250, 2)).setName("Strong Punch")
			.setAnim(new PunchRight01(renderer));
		addAction(new Drain(240, 4)).setName("Absorb");
		
		setColor(0.3f, 0.1f, 0.7f, 1);

	}

}
