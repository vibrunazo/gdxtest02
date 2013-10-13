package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;

public class Char08 extends Char {
	
	public Char08(String name) {
		super(name);
		
//		float ratio = 0.6666667f;
		float ratio = 1f;
		addAction(new Dmg(100*ratio).setName("a"));
		addAction(new Dmg(200*ratio, 2).setName("b"));
		addAction(new PutDot(20*ratio, 4, 10).setName("c"));
//		addAction(new Drain(60*ratio, 1).setName("d"));
//		addAction(new Dmg(120*ratio, 1).setName("e"));
//		addAction(new Heal(300*ratio, 3).setName("f"));
		
		addActionForLevel(2, new Dmg(120*ratio, 1).setName("e"));
		addActionForLevel(4, new Heal(300*ratio, 3).setName("f"));
		
		setTex("ball02yell.png");
		setColor("blue");

	}

}
