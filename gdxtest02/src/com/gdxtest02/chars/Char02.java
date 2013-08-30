package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;

public class Char02 extends Char {
	
	public Char02(String name) {
		super(name);
		
		actions.add(new Dmg(50));
		actions.add(new Heal(400, 3));
		actions.add(new PutDot(50, 0, 10));
		actions.add(new PutHot(100, 0, 5));

		setTex("ball02yell.png");
	}

}