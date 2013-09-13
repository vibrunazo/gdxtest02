package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class Balance {
	Char player;
	private int total;
	private float bestdmg;
	private Array<Integer> bestcombo;
	private TestResult testresult;
	private int numberofbests;

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
		testModelA(10, 1000, 0);
	}

	public void testModel2() {
		testModelA(30, 1000, 0);
		testModelA(30, 1000, 1);
//		testModelA(10, 1000, 2);
////		[2, 3, 3, 3, 3, 3, 3, 1, 1, 2]
//		Integer[] c = {2, 3, 3, 3, 3, 3, 3, 1, 1, 2};
//		Array<Integer> combo = new Array<Integer>();
//		combo.addAll(c);
//		calculateDmgFromCombo(combo);

	}

	// TODO check for abilities with only 1 ability with a large cooldown
	public void testModelA(int maxrounds, float targetdamage, int type) {
		log("testing model 1 on char " + player.getName());
		TestResult testresult;
		if (type == 0) {
			log("doing best combo test");
			testresult = testBestCombo(maxrounds);
		}
		else if (type == 1) {
			log("doing tree test");
			testresult = testTree(maxrounds);
		}
		else {
			log("doing brute force test");
			testresult = testAllCombinations(maxrounds);
		}


		int bestdmg = testresult.getBestdmg();
		Array<Integer> bestcombo = testresult.getBestcombo();
		int numberofbests = testresult.getNumberofbests();

		log("test over, best dmg: " + bestdmg + " combo: " + bestcombo 
				+ " number of bests: " + numberofbests);
		if (bestdmg > 0) {

			float ratio = targetdamage/bestdmg;
			balanceDmg(player, ratio);

		}
	}

	/**Build a game tree to find the best combo
	 * @param maxrounds
	 * @return
	 */
	private TestResult testTree(int maxrounds) {
		// prepare for calculations
		int round = 1;
		total = 0;
		bestdmg = 0;
		numberofbests = 0;
		bestcombo = new Array<Integer>(); 
		Array<Integer> combo = new Array<Integer>();
		loopToNextBranches(maxrounds, round, combo);

		// record results on a TestResult object and return it
		//		TestResult testresult = new TestResult();
		//		testresult.setBestdmg(0);
		//		testresult.setBestcombo(null);
		//		testresult.setNumberofbests(1);
		return testresult;
	}

	/**Loop through all next branches
	 * 	
	 *    o
	 * [/ | \] -> You are here
	 * o  o  o
	 * 
	 * @param round 
	 * @param maxrounds 
	 * @param combo 
	 */
	private void loopToNextBranches(int maxrounds, int round, Array<Integer> combo) {
//		log("looping on round: " + round + "/" + maxrounds);

		int numberofskills = player.actions.size;

		for (int id = 1; id < numberofskills + 1; id++) {
			Action a = player.getAction(id);
			nextBranch(a, maxrounds, round, combo);
		}
	}

	/**Calculate the next branch on the game tree
	 * 
	 *    o
	 *  / |[\] -> You are here
	 * o  o  o
	 * 
	 * @param a
	 * @param round 
	 * @param maxrounds 
	 * @param combo 
	 */
	private void nextBranch(Action a, int maxrounds, int round, Array<Integer> combo) {
		int id = player.getIdOfAction(a);
		// Pruning goes here
		//		if (a == null) log("action is null");

		combo = new Array<Integer>(combo);
		combo.add(id);
//		log("branch: " + id + " combo so far: " + combo);
		
		if (isThisSkillOnCooldown(a, round, combo)) {
//			log("yes, skill " + id + " is on cd on combo: " + combo + " prunning");
			return;
		}

		round++;
		if (round <= maxrounds) {
			loopToNextBranches(maxrounds, round, combo);
		}
		else {
			// finish, success
			float dmg = calculateDmgFromCombo(combo);
//			log("and it's all over! Total loops: " + ++total + " combo: " + combo + " dmg: " +dmg);

			if (dmg > bestdmg) {
				bestdmg = dmg;
				bestcombo = combo;
				numberofbests = 0;
			}
			if (dmg == bestdmg) {
				numberofbests++;
			}

			testresult = new TestResult();
			testresult.setBestdmg((int) bestdmg);
			testresult.setBestcombo(bestcombo);
			testresult.setNumberofbests(numberofbests);
		}
	}

	/**Calculates how much dmg a combo does
	 * @param combo
	 * @return
	 */
	private float calculateDmgFromCombo(Array<Integer> combo) {
		int maxrounds = combo.size;
		if (maxrounds == 0) return 0;
		float dmg = 0;
		int id; Action a = null; int roundsleft;
		for (int round = 1; round <= maxrounds; round++) {
			id = combo.get(round-1); 
			a = player.getAction(id);
			roundsleft = maxrounds - round + 1;
			if (isThisSkillOnCooldown(a, round, combo)) {
//				log("skill " + id + " is on cd on round " + round);
			}
			else {
//				log("round: " + round + " left: " + roundsleft + " skill: " + id + " dmg: " + a.getDmgAfterRounds(roundsleft));
				dmg += a.getDmgAfterRounds(roundsleft);
			}
		}
//		log("combo: " + combo + " dmg: " + dmg);
		return dmg;
	}

	/**Will check if this skill is on cooldown on this round if this combination
	 * is used
	 * 
	 * @param a
	 * @param round
	 * @param combo
	 * @return
	 */
	private boolean isThisSkillOnCooldown(Action a, int round,
			Array<Integer> combo) {
//		log("checking if skill " + player.getIdOfAction(a) + " is on cd on round " + round);
		if (round == 1) return false;
		int cd = a.getCooldown();
		int lastuse = lastTimeThisSkillWasUsed(a, round, combo);
		if (lastuse == 0) return false;
		int timesincelastuse = round - lastuse;
		if (timesincelastuse <= cd) {
//			log("yes");
			return true;
		}
		else {
//			log("no, cd: " + cd + " lastuse: " + lastuse + " timesince: " + timesincelastuse);
			return false;
		}
	}

	/**The last time this skill was used in this combination, before this 
	 * specific round
	 * 
	 * @param a
	 * @param round
	 * @param combo
	 * @return
	 */
	private int lastTimeThisSkillWasUsed(Action a, int round,
			Array<Integer> combo) {
		int id = player.getIdOfAction(a);
		Array<Integer> combobefore = new Array<Integer>(combo);
		combobefore.truncate(round - 1);
		return combobefore.lastIndexOf(id, true) + 1;
//		log("last use of: " + id + " combobefore: " + combobefore + " lastuse: " + lastuse);
	}

	/**Will do only 1 simulation trying to use only the best skills available at
	 * each round, then returns the results
	 * 
	 * @param maxrounds
	 * @return
	 */
	private TestResult testBestCombo(int maxrounds) {
		// prepare for simulation
		Char dummy = new Char("dummy");
		dummy.setMaxhp(100000);
		int inihp = dummy.getMaxhp();
		player.setTarget(dummy);
		Array<Integer> combo = new Array<Integer>();
		int dmg = 0;
		player.resetStats();
		dummy.resetStats();
		int id = 1;
		int roundsleft = maxrounds;
		Action a = null;

		// start simulation
		for (int round = 1; round <= maxrounds; round++) {

			id = getNextBestSkill(player, roundsleft);
			combo.add(id);
			player.setActiveActionId(id);
			a = player.getActiveAction();
			player.updateAll();
			dummy.updateAll();
			dummy.applyDmg();
			roundsleft--;
		}
		// simulation ended
		// get damage done
		dmg = (inihp - dummy.getHp());

		// record results on a TestResult object and return it
		TestResult testresult = new TestResult();
		testresult.setBestdmg(dmg);
		testresult.setBestcombo(combo);
		testresult.setNumberofbests(1);
		return testresult;
	}

	/**Returns what is the id of the best skill Char p can use,
	 * based on which one does the best damage until game is over
	 * and is off cooldown
	 * 
	 * @param p
	 * @param roundsleft
	 * @return
	 */
	private int getNextBestSkill(Char p, int roundsleft) {
		int bestid = 0;
		Action a;
		float bestdmg = 0;
		float dmg = 0;
		for (int id = 1; id <= 4; id++) {
			a = p.getAction(id);
			if (a == null || a.getCurcooldown() > 0) continue;
			dmg = a.getDmgAfterRounds(roundsleft);
			if (dmg > bestdmg) {
				bestdmg = dmg;
				bestid = id;
			}
		}

		return bestid;
	}

	/**Build all possible combinations of combos with maxrounds duration,
	 * then will simulate all of them, then return the results
	 * 
	 * @param maxrounds
	 * @return TestResult with best combo, dmg and number of bests
	 */
	private TestResult testAllCombinations(int maxrounds) {
		Array<Array<Integer>> listofcombos = new Array<Array<Integer>>();
		listofcombos = makeCombination(maxrounds, 4);
		TestResult testresult = bruteForceAllCombos(maxrounds, listofcombos);
		return testresult;
	}

	/**Takes a list of combos, then do simulations of all of them and return results
	 * 
	 * @param maxrounds
	 * @param listofcombos
	 * @return
	 */
	private TestResult bruteForceAllCombos(int maxrounds, 
			Array<Array<Integer>> listofcombos) {

		Char dummy = new Char("dummy");
		dummy.setMaxhp(100000);

		player.resetStats();
		player.setTarget(dummy);

		Array<Integer> bestcombo = new Array<Integer>();
		int bestdmg = 0;
		int dmg = 0;
		int numberofbests = 0;

		TestResult testresult = new TestResult();
		for (Array<Integer> combo : listofcombos) {

			dmg = getDmgFromCombo(maxrounds, dummy, combo);
			//			log("dmg: " + dmg + " bestdmg: " + bestdmg);
			if (dmg > bestdmg) {
				bestdmg = dmg;
				bestcombo = combo;
				numberofbests = 0;
			}
			if (dmg == bestdmg) {
				numberofbests++;
			}
		}

		testresult.setBestdmg(bestdmg);
		testresult.setBestcombo(bestcombo);
		testresult.setNumberofbests(numberofbests);
		return testresult;
	}

	/**Do one simulation of a fight and return the damage done
	 * 
	 * @param maxrounds
	 * @param dummy
	 * @param combo
	 * @return
	 */
	private int getDmgFromCombo(int maxrounds, Char dummy, Array<Integer> combo) {
		Action a = null;
		int dmg;
		int inihp = dummy.getMaxhp();
		//			log("testing combo: " + combo.toString());
		int id = 0;
		player.resetStats();
		dummy.resetStats();
		for (int round = 1; round <= maxrounds; round++) {

			try {id = combo.get(round-1);}
			catch (Exception e) {log("out of bounds, no action to use this round");}
			player.setActiveActionId(id);
			a = player.getActiveAction();
			//				log("round: " + round);
			//				log("hp before: " + dummy.getHp());
			//			if (a != null) {
			//					log("using action: " + player.getActiveAction().getName());
			//			}
			player.updateAll();
			dummy.updateAll();
			dummy.applyDmg();
			//				else log("action " + player.getActiveActionId() + "is null");
			//				log("hp after: " + dummy.getHp());
		}
		dmg = (inihp - dummy.getHp());
		return dmg;
	}

	/**Suggests a fix to balance the player skills using ratio
	 * 
	 * @param player
	 * @param ratio
	 */
	private void balanceDmg(Char player, float ratio) {
		int id = 1;
		player.getAction(id);
		Array<Float> skillsdmg = new Array<Float>(); 
		for (id = 1; id <= 4; id++) {
			Action a = player.getAction(id);
			if (a != null) {
				skillsdmg.add(a.getPower()*ratio);
			}
			else skillsdmg.add(0f); 
		}
		log("ratio: " + ratio + " new skills: " + skillsdmg.toString());		
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

	private Array<Array<Integer>> makeCombination(int sizex, int sizey) {
		Array<Array<Integer>> list = new Array<Array<Integer>>();
		//		int sizex = 10;
		//		int sizey = 4;
		int[] loop = new int[sizex];
		for (int i : loop) {
			loop[i] = 0;
		}
		int i = 0;

		recb(list, loop, sizex, sizey, i);

		return list;
		//		log("list: " + list);
	}

	private void recb(Array<Array<Integer>> list, int[] loop, int sizex, int sizey, int i) {
		while (i<Math.pow(sizey,sizex)) {
			Array<Integer> combo = new Array<Integer>();

			for (int p = 0; p < sizex; p++) {
				combo.add(loop[p]+1);	
			}

			//			log("combo(" + i + "): " + combo);
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
