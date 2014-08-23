package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.anims.PunchRight01;

public class TestChar06 extends Char {
	
	public TestChar06() {
		super();
		
		float ratio = 0.85470086f;
		setName("c6");
		addAction(new Dmg(20*ratio)).setName("testa1");
		addAction(new Dmg(25*ratio, 3)).setName("testa2");
		addAction(new Dmg(500*ratio, 8)).setName("testa3")
				.setOwner(this)
				.setAnim(new PunchRight01());
//		addAction(new Dmg(400*ratio, 3).setName("testa4"));
		
//		addAction(new Heal(300, 1).setName("test2"));
//		addAction(new Drain(100, 1).setName("test3"));
		
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");
		
		setColor(0.8f, 0f, 1f, 1);

	}

}
