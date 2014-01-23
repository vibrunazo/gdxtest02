package com.gdxtest02.buffs;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import static com.gdxtest02.GdxTest02.log;
public class BuffDmg extends Buff{
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
					log("buff power: "+ a.getBuffPwMultiplier());
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


