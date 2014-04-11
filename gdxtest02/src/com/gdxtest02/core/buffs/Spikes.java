package com.gdxtest02.core.buffs;

import com.gdxtest02.core.Buff;
import com.gdxtest02.core.Char;
import com.gdxtest02.core.effects.FireEffect;

import static com.gdxtest02.core.GdxTest02.log;
public class Spikes extends Buff{
	
	@Override
	protected void ini() {
		super.ini();
		effect = new FireEffect();
	}
	
	
	@Override
	public void act(Char self) {
		self.setSpikes(self.getSpikes()+power);
		log("Spikes act, self.getSpikes: "+ self.getSpikes());
	}
	
	public void ini(Char self){
	//	self.setSpikes(self.getSpikes()+power);
	}
	
	public void end(Char self){
	//	self.setSpikes(self.getSpikes()-power);
	}
		
	public Spikes() {
		super();
	}

	public Spikes(float value) {
		super(value);
	}
	
	public Spikes(float value, int duration) {
		super(value, duration);
	}
}


