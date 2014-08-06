package com.gdxtest02.buffs;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.effects.GreenEffect;
import com.gdxtest02.effects.ShieldEffect;
import com.gdxtest02.util.Util;
public class BuffResist extends Buff{
	
	@Override
	protected void ini() {
		super.ini();
		setEffect(new ShieldEffect());
		getEffect().setOffset(0, 50);
		getEffect().setScale(0.6f);
	}
	
	private Array<String> bufftype;
	@Override
	public void act(Char self) {
		
		if(bufftype == null)
		bufftype = new Array<String>();
		for (int x = 0; x < bufftype.size; x++){
			if(self.getResists().containsKey(bufftype.get(x))){
				self.editActualResists(bufftype.get(x), self.getResists().get(bufftype.get(x))-power);
				if(self.getResists().get(bufftype.get(x)) < 0){
					self.editActualResists(bufftype.get(x), 0);
				}
			}
			else{
				self.editActualResists(bufftype.get(x), self.getResists().get("all")-power);
				if(self.getResists().get(bufftype.get(x)) < 0){
					self.editActualResists(bufftype.get(x), 0);
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
		
	public BuffResist() {
		super();
		bufftype = new Array<String>();
	}
	
	public BuffResist(Array<String> bufflist) {
		super();
		bufftype = bufflist;
		
	}

	public BuffResist(float value, Array<String> bufflist) {
		super(value);
		bufftype = bufflist;
	}
	
	public BuffResist(float value, int duration, Array<String> bufflist) {
		super(value, duration);
		bufftype = bufflist;
	}


}


