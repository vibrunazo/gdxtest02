package com.gdxtest02.core.chars;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.actions.Dmg;
import com.gdxtest02.core.actions.Heal;
import com.gdxtest02.core.actions.PutDmgBuff;
import com.gdxtest02.core.actions.PutDot;
import com.gdxtest02.core.actions.PutHot;
import com.gdxtest02.core.actions.PutSpikes;
import com.gdxtest02.core.effects.IceEffect;

public class Char02 extends Char {
	
	public Char02(String name) {
		super(name);
		
		float ratio = 0.95238096f;
		float hratio = 0.5f;
		
		addAction(new Dmg(50*ratio)).setName("Punch").addType(new String[] { "malcon", "normal", "melee", "falcon" });
		addAction(new Heal(250*hratio, 3)).setName("Heal");
		//(addAction(new PutDmgBuff(1, 0, 5))).setName("aaa").setType("Buff");
		PutDmgBuff b = new PutDmgBuff(1, 0, 5);
		b.setName("aaa");
//		b.addType("buff");
		b.addBuffType((new String[] {"falcon", "satan", "normal"}));
		b.setEffect(new IceEffect());
		addAction(b);
		//addAction(new PutDot(50*ratio, 0, 2).setName("Death Fart"));
		addAction(new PutSpikes(300, 0, 5)).setName("Flame Shield");
//		actions.add(new PutHot(100, 5, 5).setName("Rejuv"));
		editResists("fire", 2);
		editResists("normal", 0);

		setTex("ball02yell.png");
		
		setColor(0.1f, 1f, 0f, 1);
		
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
