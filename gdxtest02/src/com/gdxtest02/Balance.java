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

		Array<Array<Integer>> listofcombos = new Array<Array<Integer>>();
		addCombo1(maxrounds, listofcombos);
		addCombo2(maxrounds, listofcombos);
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

		makeCombination();
	}

	private void addCombo2(int maxrounds, Array<Array<Integer>> listofcombos) {
		int round;
		Array<Integer> actioncombo2 = new Array<Integer>();
		for (round = 1; round <= maxrounds; round++) {
			actioncombo2.add(round%3+1);
		}
		listofcombos.add(actioncombo2);
	}

	private void addCombo1(int maxrounds, Array<Array<Integer>> listofcombos) {
		int round;
		Array<Integer> actioncombo = new Array<Integer>();
		for (round = 1; round <= maxrounds; round++) {
			actioncombo.add(2);
		}
		listofcombos.add(actioncombo);
	}

	private void makeCombination() {
		Array<Array<Integer>> list = new Array<Array<Integer>>();
		int sizex = 4;
		int sizey = 3;
		int[] loop = new int[sizex];
		for (int i : loop) {
			loop[i] = 0;
		}
		int i = 0;

		recb(list, loop, sizex, sizey, i);
		
		log("list: " + list);
	}
	
	private void recb(Array<Array<Integer>> list, int[] loop, int sizex, int sizey, int i) {
		while (i<Math.pow(sizey,sizex)) {
			Array<Integer> combo = new Array<Integer>();
			
			for (int p = 0; p < sizex; p++) {
				combo.add(loop[p]);	
			}
			
			log("combo(" + i + "): " + combo);
			list.add(combo);

			for (int d = 0; d < sizex; d++) {
				if (loop[d] < sizey - 1) {
					loop[d]++;
					if (d>0) {
						for (int a = d; a > 0; a--) {
							loop[a-1] = 0;
						}
						
					}
					break;
				}
			}
			i++;
		}
	}

	private void rec(Array<Array<Integer>> list, int[] loop, int size, int i) {
		int l = 0;
		for (l = 0; l < size; l++) {
			for (loop[l] = 0; loop[l] < size; loop[l]++) {
				Array<Integer> combo = new Array<Integer>();
				for (int p = 0; p < size; p++) {
					combo.add(loop[p]);
				}
				//			combo.add(c);
				log("combo(" + i + "): " + combo);
				list.add(combo);
				i++;
			}
		}
	}

	private void reca(Array<Array<Integer>> list, int i, int c, int b) {
		for (int a = 0; a < 3; a++) {
			Array<Integer> combo = new Array<Integer>();
			combo.add(a);
			combo.add(b);
			combo.add(c);
			log("combo(" + i + "): " + combo);
			list.add(combo);
			i++;
		}
	}

	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

}
