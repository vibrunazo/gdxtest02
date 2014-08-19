package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Buff;
import com.gdxtest02.Char;
import com.gdxtest02.GdxTest02;
import com.gdxtest02.PutBuffAction;
import com.gdxtest02.anims.Cast01;
import com.gdxtest02.anims.Punch01;
import com.gdxtest02.buffs.Dot;
import com.gdxtest02.buffs.Stun;
import com.gdxtest02.util.Util;

public class PutStun extends PutBuffAction {

	@Override
	protected void go(Char self, Char target) {
		Buff buff = getNewBuffInstance();
		target.addBuff(buff.setName("Stun"));
//		if (getAnimEffect() != null) buff.setEffect(getAnimEffect()); 
		
	}

	@Override
	public void ini() {
		setName("Put Stun");
		setAnim(new Cast01());
		setBuff(new Stun(duration));;
	}
	
	public void update() {
		setDescription("Stuns target for " + getDuration() + 
				" sec.");
	}
	
	
	public PutStun() {
		super();
	}

		
	public PutStun( int cooldown) {
		super(cooldown);
	}
	
	public PutStun(int cooldown, int duration) {
		super(cooldown, duration);
	}
}
