package com.gdxtest02.core.buffs;

import java.lang.reflect.Constructor;

import com.badlogic.gdx.utils.Array;
import com.gdxtest02.core.Action;
import com.gdxtest02.core.Buff;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.effects.FireEffect;

import static com.gdxtest02.core.GdxTest02.log;
public class BuffDmg extends Buff{
	
	@Override
	protected void ini() {
		super.ini();
		effect = new FireEffect();
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
					log("buff power: "+ a.getBuffPwMultiplier());
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


	/* (non-Javadoc)
	 * @see com.gdxtest02.Buff#getClone()
	 */
	@Override
	public Buff getClone() {
		try {
			Constructor<? extends BuffDmg> constructor = this.getClass().getConstructor();
			Object clone = constructor.newInstance();
			BuffDmg b = (BuffDmg)clone;
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


