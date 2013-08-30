package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;

public class Char02 extends Char {
	
	public Char02(String name) {
		super(name);
		
		actions.clear();
		actions.add(new Dmg(100));
		actions.add(new Heal(100));
		actions.add(new PutDot(50, 10));
		actions.add(new Dmg(200));
		setTex("ball02yell.png");
//		maxhp = 100;
	}

}
