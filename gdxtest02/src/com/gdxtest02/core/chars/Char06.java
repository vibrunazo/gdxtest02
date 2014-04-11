package com.gdxtest02.core.chars;

import com.gdxtest02.core.AnimRenderer;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.actions.Dmg;
import com.gdxtest02.core.actions.Drain;
import com.gdxtest02.core.actions.Heal;
import com.gdxtest02.core.actions.PutDot;
import com.gdxtest02.core.anims.PunchRight01;

public class Char06 extends Char {
	
	public Char06(String name) {
		super(name);
		
		float ratio = 0.85470086f;
		AnimRenderer renderer = getAnimRenderer();
		addAction(new Dmg(20*ratio)).setName("testa1");
		addAction(new Dmg(25*ratio, 3)).setName("testa2");
		addAction(new Dmg(500*ratio, 8)).setName("testa3")
				.setOwner(this)
				.setAnim(new PunchRight01(renderer));
//		addAction(new Dmg(400*ratio, 3).setName("testa4"));
		
//		addAction(new Heal(300, 1).setName("test2"));
//		addAction(new Drain(100, 1).setName("test3"));
		
		setTex("ball02red.png");
		
		setColor(0.8f, 0f, 1f, 1);

	}

}
