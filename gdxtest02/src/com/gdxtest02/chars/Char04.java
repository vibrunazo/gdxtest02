package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;

public class Char04 extends Char {
	
	public Char04(String name) {
		super(name);
		
		float ratio = 0.6666667f;
		addAction(new Dmg(100*ratio).setName("moo"));
		addAction(new Dmg(200*ratio, 1).setName("meh"));
		addAction(new PutDot(50*ratio, 1, 6).setName("a dot"));
		addAction(new PutHot(50*ratio, 1, 6).setName("a hot"));
//		actions.add(new Dmg(400, 3).setName("hihihi"));
		
		setTex("ball02yell.png");
		
		setColor(1, 0.7f, 0.1f, 1);

	}

}
