package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;

public class Char06 extends Char {
	
	public Char06(String name) {
		super(name);
		
		float ratio = 0.85470086f;
		addAction(new Dmg((float) Math.ceil(20*ratio)).setName("testa1"));
		addAction(new Dmg((float) Math.ceil(25*ratio), 3).setName("testa2"));
		addAction(new Dmg((float) Math.ceil(500*ratio), 8).setName("testa3").setAnim("punchright"));
//		addAction(new Dmg(400*ratio, 3).setName("testa4"));
		
//		addAction(new Heal(300, 1).setName("test2"));
//		addAction(new Drain(100, 1).setName("test3"));
		
		setTex("ball02red.png");
		
		setColor(0.8f, 0f, 1f, 1);

	}

}
