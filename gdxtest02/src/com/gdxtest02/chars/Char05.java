package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;

public class Char05 extends Char {
	
	public Char05(String name) {
		super(name);
		
		float ratio = 0.47619048f;
		actions.add(new Dmg((float) Math.ceil(50*ratio)).setName("test1"));
		actions.add(new Dmg((float) Math.ceil(100*ratio), 4).setName("test2"));
		actions.add(new Dmg((float) Math.ceil(200*ratio), 3).setName("test3"));
		actions.add(new Dmg((float) Math.ceil(400*ratio), 3).setName("test4"));
		
//		actions.add(new Heal(300, 1).setName("test2"));
//		actions.add(new Drain(100, 1).setName("test3"));
		
		setTex("ball02red.png");

	}

}
