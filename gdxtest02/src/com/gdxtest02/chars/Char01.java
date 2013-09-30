package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;

public class Char01 extends Char {
	
	public Char01(String name) {
		super(name);
		
		addAction(new Dmg(120).setName("Tiger"));
		addAction(new Dmg(250, 2).setName("Shoryuken"));
		addAction(new Drain(240, 4).setName("Death Coil"));
		
		setTex("ball02red.png");

	}

}
