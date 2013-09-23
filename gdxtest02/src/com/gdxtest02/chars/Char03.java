package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;

public class Char03 extends Char {
	
	public Char03(String name) {
		super(name);
		

//		float ratio = 0.4347826f;
		float ratio = 0.42553192f;
//		float ratio = 1f;
		actions.add(new Dmg(100*ratio).setName("Yoga Fire"));
		actions.add(new Dmg(400*ratio, 5).setName("Overpacarai"));
		actions.add(new PutDot(50*ratio, 0, 5).setName("DotFoda"));
		// 44, 174, 22
		// 43, 171, 22
		setTex("ball02red.png");

	}

}
