package com.gdxtest02.chars;

import com.gdxtest02.AnimRenderer;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutStun;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.buffs.Stun;

public class TestChar05 extends Char {
	
	public TestChar05() {
		super();
		
		float ratio = 0.47619048f;
		setName("c5");
		//addAction(new Dmg(50*ratio)).setName("test1");
		addAction(new PutStun(2, 5)).setName("stun");
		addAction(new Dmg(100*ratio, 4)).setName("test2");
		addAction(new Dmg(200*ratio, 3)).setName("test3");
		addAction(new Dmg(400*ratio, 3)).setName("test4")
				.setAnim(new PunchRight01());
		
//		addAction(new Heal(300, 1).setName("test2"));
//		addAction(new Drain(100, 1).setName("test3"));
		
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");
		
		setColor(1, 0, 0, 1);

	}

}
