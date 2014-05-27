package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.anims.PunchRight01;

public class Char01 extends Char {
	
	public Char01(String name) {
		super(name);
		
		AnimRenderer renderer = getAnimRenderer();
		addAction(new Dmg(120)).setName("Tiger");
		addAction(new Dmg(250, 2)).setName("Shoryuken")
			.setAnim(new PunchRight01(renderer));
		addAction(new Drain(240, 4).setName("Death Coil"));
		
		setTex("ball02red.png");
		
		setColor(0.2f, 0.2f, 0.8f, 1);

	}

}
