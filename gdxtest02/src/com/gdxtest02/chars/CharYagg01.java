package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;

public class CharYagg01 extends Char {
	
	public CharYagg01(String name) {
		super(name);
		
		actions.add(new PutDot(50).setName("Vorpal Bite"));
		actions.add(new PutHot(20).setName("Shrubbery"));
		actions.add(new PutDot(1000, 3, 2).setName("Holy Hand Grenade"));
		actions.add(new PutHot(500, 3, 2).setName("Just a Flesh Wound"));
		
		setTex("ball02red.png");

	}

}
