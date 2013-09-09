package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;

public class Char03 extends Char {
	
	public Char03(String name) {
		super(name);
		

		float ratio = 0.4347826f;
		actions.add(new Dmg((float) Math.ceil(100*ratio)).setName("Yoga Fire"));
		actions.add(new Dmg((float) Math.ceil(400*ratio), 5).setName("Overpacarai"));
		actions.add(new PutDot((float) Math.ceil(50*ratio), 0, 5).setName("DotFoda"));
		
		setTex("ball02red.png");

	}

}
