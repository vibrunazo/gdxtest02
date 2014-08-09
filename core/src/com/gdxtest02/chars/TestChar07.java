package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;

public class TestChar07 extends Char {
	
	public TestChar07() {
		super();
		
		setName("c7");
		addAction(new PutDot(50)).setName("Vorpal Bite");
		addAction(new PutHot(20)).setName("Shrubbery");
		addAction(new PutDot(1000, 3, 2)).setName("Holy Hand Grenade");
		addAction(new PutHot(500, 3, 2)).setName("Just a Flesh Wound");
		
		setTex("ball02red.png");
		
		setColor(0.15f, 0.15f, 0.15f, 1);
	}

}
