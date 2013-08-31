package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;

public class Char01 extends Char {
	
	public Char01(String name) {
		super(name);
		
		actions.add(new Dmg(100));
		actions.add(new Dmg(200, 2));
		actions.add(new Drain(150, 4));
		
		setTex("ball02red.png");

	}

}
