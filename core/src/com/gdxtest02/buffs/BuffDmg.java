package com.gdxtest02.buffs;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.util.Util;
public class BuffDmg extends Buff{
	
	@Override
	protected void ini() {
		super.ini();
		setEffect(0, new FireEffect());
		setEffect(1, new FireEffect());
		resetEffectPosition();
	}

	private Array<String> bufftype;
	@Override
	public void act(Char self) {
		
		if(bufftype == null)
		bufftype = new Array<String>();
		for (Action a : self.getActionBar()) {
			for (int x = 0; x < bufftype.size; x++){
				if(a.getTypeList().contains(bufftype.get(x), true) ){
					a.setBuffPwMultiplier(a.getBuffPwMultiplier()+power);
					a.updatePower();
					a.update();
					Util.log("buff power: "+ a.getBuffPwMultiplier());
					break;
				}
			}
		}	
		
		
		
}		
	
	
	public void ini(Char self){
	//	self.setBuffPwMultiplier(self.getBuffPwMultiplier() + power);
		
	}
	
	public void end(Char self){
	//	self.setBuffPwMultiplier(self.getBuffPwMultiplier() - power);
	}
		
	public BuffDmg() {
		super();
		bufftype = new Array<String>();
	}
	
	public BuffDmg(Array<String> bufflist) {
		super();
		bufftype = bufflist;
		
	}

	public BuffDmg(float value, Array<String> bufflist) {
		super(value);
		bufftype = bufflist;
	}
	
	public BuffDmg(float value, int duration, Array<String> bufflist) {
		super(value, duration);
		bufftype = bufflist;
	}

}


