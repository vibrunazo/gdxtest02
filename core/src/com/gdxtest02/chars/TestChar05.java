package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.anims.PunchRight01;

public class TestChar05 extends Char {
	
	public TestChar05(String name) {
		super(name);
		
		float ratio = 0.47619048f;
		AnimRenderer renderer = getAnimRenderer();
		addAction(new Dmg(50*ratio)).setName("test1");
		addAction(new Dmg(100*ratio, 4)).setName("test2");
		addAction(new Dmg(200*ratio, 3)).setName("test3");
		addAction(new Dmg(400*ratio, 3)).setName("test4")
				.setAnim(new PunchRight01(renderer));
		
//		addAction(new Heal(300, 1).setName("test2"));
//		addAction(new Drain(100, 1).setName("test3"));
		
		setTex("ball02red.png");
		
		setColor(1, 0, 0, 1);

	}

}
