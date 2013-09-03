package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;

public class Char01 extends Char {
	
	public Char01(String name) {
		super(name);
		
		actions.add(new Dmg(120).setName("Tiger"));
		actions.add(new Dmg(250, 2).setName("Shoryuken"));
		actions.add(new Drain(240, 4).setName("Death Coil"));
		
		setTex("ball02red.png");

	}

}
