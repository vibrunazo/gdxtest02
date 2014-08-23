package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.anims.PunchRight01;

public class TestChar01 extends Char {
	
	public TestChar01() {
		super();
		
		setName("c1");
		addAction(new Dmg(120)).setName("Tiger");
		addAction(new Dmg(250, 2)).setName("Shoryuken")
			.setAnim(new PunchRight01());
		addAction(new Drain(240, 4).setName("Death Coil"));
		
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");
		
		setColor(0.2f, 0.2f, 0.8f, 1);

	}

}
