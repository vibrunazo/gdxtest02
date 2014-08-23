package com.gdxtest02.chars;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDmgBuff;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;
import com.gdxtest02.actions.PutSpikes;
import com.gdxtest02.effects.IceEffect;

public class TestChar02 extends Char {
	
	public TestChar02() {
		super();
		
		float ratio = 0.95238096f;
		float hratio = 0.5f;
		
		setName("c2");
		addAction(new Dmg(50f*ratio)).setName("Punch").addType(new String[] { "malcon", "normal", "melee", "falcon" });
		addAction(new Heal(250*hratio, 3)).setName("Heal");
		//(addAction(new PutDmgBuff(1, 0, 5))).setName("aaa").setType("Buff");
		PutDmgBuff b = new PutDmgBuff(1, 0, 5);
		b.setName("aaa");

		b.addBuffType((new String[] {"falcon", "satan", "fire", "normal"}));

		addAction(b);
		//addAction(new PutDot(50*ratio, 0, 2).setName("Death Fart"));
		addAction(new PutSpikes(300, 0, 5)).setName("Flame Shield");
//		actions.add(new PutHot(100, 5, 5).setName("Rejuv"));
		editDefaultResists("fire", 2);
		editDefaultResists("normal", 1);

		
		setColor(0.1f, 1f, 0f, 1);
		setBodyPart("tail", "tail_normal");
		setBodyPart("arms", "arms_normal");
		setBodyPart("hands", "hands_normal");
		
		Array<Integer> skillList = new Array<Integer>();
		skillList.add(3);
		skillList.add(3);
		skillList.add(2);
		skillList.add(3);
		skillList.add(3);
		skillList.add(3);
		skillList.add(2);
		skillList.add(3);
		skillList.add(3);
		skillList.add(3);
		setAiSkillList(skillList);
	}
	
	
}
