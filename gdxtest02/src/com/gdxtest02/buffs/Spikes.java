package com.gdxtest02.buffs;

import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import static com.gdxtest02.GdxTest02.log;
public class Spikes extends Buff{
	@Override
	public void act(Char self) {
		self.setSpikes(+power);
		log("Spikes: "+ self.getSpikes());
		if (getDuration()==0){
			self.setSpikes(-power);
		}
			
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


