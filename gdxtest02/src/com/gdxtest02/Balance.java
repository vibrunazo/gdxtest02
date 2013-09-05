package com.gdxtest02;

import com.badlogic.gdx.Gdx;
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
		log("testing model 1 on char " + player.getName());
		int round = 1;
		int maxrounds = 10;
		Char dummy = new Char("dummy");
		int inihp = dummy.getHp();
		player.resetStats();
		player.setTarget(dummy);
		Array<Integer> actioncombo = new Array<Integer>();
		Array<Array<Integer>> listofcombos = new Array<Array<Integer>>();
		for (round = 1; round <= maxrounds; round++) {
			actioncombo.add(2);
		}
		listofcombos.add(actioncombo);
		Array<Integer> actioncombo2 = new Array<Integer>();
		for (round = 1; round <= maxrounds; round++) {
			actioncombo2.add(round%3+1);
		}
		listofcombos.add(actioncombo2);
		Action a = null;
		int id = 0;
		for (Array<Integer> combo : listofcombos) {
			log("testing combo: " + combo.toString());
			for (round = 1; round <= maxrounds; round++) {
				try {id = combo.get(round-1);}
				catch (Exception e) {log("out of bounds, no action to use this round");}
				player.setActiveActionId(id);
				a = player.getActiveAction();
				log("round: " + round);
				log("hp before: " + dummy.getHp());
				if (a != null) {
					log("using action: " + player.getActiveAction().getName());
					player.updateAll();
					dummy.updateAll();
					dummy.applyDmg();
				}
				else log("action " + player.getActiveActionId() + "is null");
				log("hp after: " + dummy.getHp());
			}
		}
		int dmg = (inihp - dummy.getHp());
		float dps = dmg/maxrounds;
		log("test over, dmg done in 10 rounds: " + dmg + " total dps: " + dps);
	}

	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

}
