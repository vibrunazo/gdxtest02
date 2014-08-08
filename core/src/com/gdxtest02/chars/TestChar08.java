package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.anims.PunchRight01;

public class TestChar08 extends Char {
	
	public TestChar08(String name) {
		super(name);
		
//		float ratio = 0.6666667f;
		float ratio = 1f;
		AnimRenderer renderer = getAnimRenderer();
		addAction(new Dmg(150*ratio)).setName("a");
		addAction(new Dmg(220*ratio, 2)).setName("b")
				.setAnim(new PunchRight01(renderer));
		addAction(new PutDot(50*ratio, 4, 10).setName("c"));
//		addAction(new Drain(60*ratio, 1).setName("d"));
//		addAction(new Dmg(120*ratio, 1).setName("e"));
//		addAction(new Heal(300*ratio, 3).setName("f"));
		
		addActionForLevel(2, new Dmg(120*ratio, 1)).setName("e");
		addActionForLevel(4, new Heal(300*ratio, 3)).setName("f");
		
		setTex("ball02yell.png");
		setColor(0, 0, 1, 1);

	}

}