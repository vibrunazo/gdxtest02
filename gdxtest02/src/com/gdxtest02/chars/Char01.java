package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;

public class Char01 extends Char {
	
	public Char01(String name) {
		super(name);
		
		actions.clear();
		actions.add(new Dmg(100));
		actions.add(new Dmg(200));
		setTex("ball02red.png");
//		maxhp = 100;
	}

}
