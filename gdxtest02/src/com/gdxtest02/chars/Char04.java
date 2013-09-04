package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;

public class Char04 extends Char {
	
	public Char04(String name) {
		super(name);
		
		actions.add(new Dmg(100).setName("moo"));
		actions.add(new Dmg(200, 1).setName("meh"));
		actions.add(new Dmg(400, 3).setName("hihihi"));
		
		setTex("ball02yell.png");

	}

}
