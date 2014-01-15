package com.gdxtest02.chars;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Char;
import com.gdxtest02.actions.Dmg;
import com.gdxtest02.actions.Heal;
import com.gdxtest02.actions.PutDmgBuff;
import com.gdxtest02.actions.PutDot;
import com.gdxtest02.actions.PutHot;
import com.gdxtest02.actions.PutSpikes;

public class Char02 extends Char {
	
	public Char02(String name) {
		super(name);
		
		float ratio = 0.95238096f;
		float hratio = 0.5f;
		
		addAction(new Dmg(50*ratio)).setName("Punch");
		addAction(new Heal(250*hratio, 3)).setName("Heal");
		addAction(new PutDmgBuff(1, 0, 5)).setName("aaa").setType("Buff");
		//addAction(new PutDot(50*ratio, 0, 2).setName("Death Fart"));
		addAction(new PutSpikes(300, 0, 5)).setName("Flame Shield").addType("Fire");
//		actions.add(new PutHot(100, 5, 5).setName("Rejuv"));

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
