package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.effects.FireEffect;

import static com.gdxtest02.GdxTest02.log;
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


