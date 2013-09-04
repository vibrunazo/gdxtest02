package com.gdxtest02;

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

}
