package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;

public class Char06 extends Char {
	
	public Char06(String name) {
		super(name);
		
		float ratio = 0.5464481f;
		actions.add(new Dmg(100*ratio).setName("testa1"));
		actions.add(new Dmg(110*ratio, 3).setName("testa2"));
		actions.add(new Dmg(500*ratio, 8).setName("testa3"));
//		actions.add(new Dmg(400*ratio, 3).setName("testa4"));
		
//		actions.add(new Heal(300, 1).setName("test2"));
//		actions.add(new Drain(100, 1).setName("test3"));
		
		setTex("ball02red.png");

	}

}
