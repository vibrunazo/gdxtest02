package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;

public class Char03 extends Char {
	
	public Char03(String name) {
		super(name);
		
		actions.add(new Dmg(200).setName("Yoga Fire"));
		actions.add(new Dmg(400, 5).setName("Overpacarai"));
		actions.add(new PutDot(50, 0, 5).setName("DotFoda"));
		
		setTex("ball02red.png");

	}

}