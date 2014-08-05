package com.gdxtest02.buffs;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.BlackEffect;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.RedEffect;
import com.gdxtest02.effects.WhiteEffect;
import com.gdxtest02.util.Util;
public class MortalStrike extends Buff{
	
	@Override
	protected void ini() {
		super.ini();
		setEffect(new RedEffect());
		getEffect().setOffset(0, 50);
	}
	
	@Override
	public void act(Char self) {
		
	if(target.getResists().containsKey("heal")){
		target.editActualResists("heal", target.getResists().get("heal") - power/100);
		if(target.getResists().get("heal") < 0){
			target.editActualResists("heal", 0);
		}
	}
	else{
		target.editActualResists("heal", self.getResists().get("all")-power/100);
		if(target.getResists().get("heal") < 0){
			target.editActualResists("heal", 0);
		}
	}
	}
			

		
		
		
		
	
	
	public void ini(Char self){
	//	self.setBuffPwMultiplier(self.getBuffPwMultiplier() + power);
		
	}
	
	public void end(Char self){
	//	self.setBuffPwMultiplier(self.getBuffPwMultiplier() - power);
	}
		
	public MortalStrike() {
		super();
		
	}
	


	public MortalStrike(float value) {
		super(value);
		
		
	}
	
	public MortalStrike(float value, int duration) {
		super(value, duration);
		
		
	}


}


