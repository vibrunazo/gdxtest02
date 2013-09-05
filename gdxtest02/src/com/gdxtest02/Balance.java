package com.gdxtest02;

import com.badlogic.gdx.utils.Array;

public class Balance {
	Char player;
	
	public Balance(Char c) {
		player = c;
	}
	
	public float getAvgDps() {
		float total = 0;
		for (Action a : player.actions) {
			total += a.getAvgDps();
		}
		return total;
	}
	
	public void testModel1() {
		int round = 1;
		int maxrounds = 10;
		Char dummy = new Char("dummy");
		Array<Action> list = new Array<Action>();
		for (round = 1; round < maxrounds; round++) {
			list.add(player.actions.get(0));
		}
	}

}
