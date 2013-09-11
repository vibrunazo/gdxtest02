package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;

public class Char02 extends Char {
	
	public Char02(String name) {
		super(name);
		
		actions.add(new Dmg(50).setName("Punch"));
		actions.add(new Heal(250, 3).setName("Heal"));
		actions.add(new PutDot(50, 0, 2).setName("Death Fart"));
//		actions.add(new PutHot(100, 5, 5).setName("Rejuv"));

		setTex("ball02yell.png");
	}

}
