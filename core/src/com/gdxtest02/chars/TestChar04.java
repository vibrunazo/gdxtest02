package com.gdxtest02.chars;

import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Drain;
import com.gdxtest02.actions.PutDmgBuff;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;
import com.gdxtest02.actions.PutMortalStrike;
import com.gdxtest02.actions.PutResistBuff;
import com.gdxtest02.effects.IceEffect;

public class TestChar04 extends Char {
	
	public TestChar04() {
		super();
		
//		float ratio = 0.6666667f;
		float ratio = 0.4f;
		setName("c4");
		addAction(new Dmg(100*ratio)).setName("moo");
		//addAction(new Dmg(200*ratio, 1)).setName("meh");
		PutResistBuff b = new PutResistBuff(0.5f ,5, 5);
		b.setName("resist power");
//		b.addType("buff");
		b.addBuffType((new String[] {"falcon", "satan", "fire", "normal"}));
		addAction(b);
		//addAction(new PutDot(50*ratio, 0, 6)).setName("a dot");
		addAction(new PutMortalStrike(50, 0, 6, 50)).setName("MS");
		addAction(new PutHot(30*ratio, 3, 6)).setName("a hot");
//		actions.add(new Dmg(400, 3).setName("hihihi"));
//		editDefaultResists("fire", 2);
//		editDefaultResists("normal", 1);
		
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");
		
		setColor(1, 1f, 0f, 1);

	}

}
