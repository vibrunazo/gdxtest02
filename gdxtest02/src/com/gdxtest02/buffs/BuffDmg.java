package com.gdxtest02.buffs;

import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import static com.gdxtest02.GdxTest02.log;
public class BuffDmg extends Buff{
	@Override
	public void act(Char self) {
		
		for (Action a : self.getActionBar()) {
			a.setBuffPwMultiplier(a.getBuffPwMultiplier()+power);
			a.updatePower();
			a.update();
			log("buff power: "+ a.getBuffPwMultiplier());
		}
		
		
			
	}
	
	public void ini(Char self){
		self.setBuffPwMultiplier(self.getBuffPwMultiplier() + power);
		
	}
	
	public void end(Char self){
		self.setBuffPwMultiplier(self.getBuffPwMultiplier() - power);
	}
		
	public BuffDmg() {
		super();
	}

	public BuffDmg(float value) {
		super(value);
	}
	
	public BuffDmg(float value, int duration) {
		super(value, duration);
	}
}


