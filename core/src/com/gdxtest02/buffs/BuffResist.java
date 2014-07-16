package com.gdxtest02.buffs;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;
import com.gdxtest02.util.Util;
public class BuffResist extends Buff{
	
	@Override
	protected void ini() {
		super.ini();
		setEffect(new FireEffect());
		getEffect().setOffset(0, 50);
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
//		for (Action a : self.getActionBar()) {
//			for (int x = 0; x < bufftype.size; x++){
//				if(a.getTypeList().contains(bufftype.get(x), true) ){
//					a.setBuffPwMultiplier(a.getBuffPwMultiplier()+power);
//					a.updatePower();
//					a.update();
//					Util.log("buff power: "+ a.getBuffPwMultiplier());
//					break;
//				}
//			}
//		}	
		
		
		
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


	/* (non-Javadoc)
	 * @see com.gdxtest02.Buff#getClone()
	 */
	@Override
	public Buff getClone() {
		try {
			Constructor<? extends BuffResist> constructor = this.getClass().getConstructor();
			Object clone = constructor.newInstance();
			BuffResist b = (BuffResist)clone;
			b.duration = this.duration;
			b.power = this.power;
			b.bufftype = this.bufftype;
			return b;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}


