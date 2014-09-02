package com.gdxtest02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.gdxtest02.anims.GetHit01;
import com.gdxtest02.anims.PunchRight01;
import com.gdxtest02.effects.ExplosionEffect;
import com.gdxtest02.effects.HitEffect;
import com.gdxtest02.util.Util;
import com.gdxtest02.util.Util.*;


public class Char implements Cloneable {
	private static final String DEFAULT_NAME = "Char";
	private float spikes;
	private String name;
	private String type;
	private int hp;
	protected int maxhp;
	private int basehp;
	private String description;
	/**Damage to be taken this round. Skills will set this.
	 * Final damage actually taken will only be decided at the end of the round.
	 * 
	 */
	private int dmg;
	private Rectangle box;
	private Texture tex;
	private String texname;
	private int posX, posY;
	/**Number of the current active action
	 * 
	 */
	private int activeAction;
	private Array<Action> actionBar;
	private Array<Action> actionsInventory;
	private ArrayMap<Integer, Action> actionUnlockedPerLevel;
	private int maxActionBarSize = 4;
	private Array<Buff> buffs;

	/**List of buffs to be added this round, 
	 * these will be moved to the main buffs list at the end of the round
	 * so they don't make effect when first applied, only 1 round later
	 */
	private Array<Buff> newbuffs;
	private Balance balance;
	private Char target;
	private boolean canoverheal;
	private boolean control;

	private float buffPwMultiplier;
	private float levelMultiplier;
	private int level;
	private int exp;
	private Array<Integer> aiSkillList;
	private HashMap<String, Float> defaultResists;
	private HashMap<String, Float> actualResists;

	private AnimRenderer animRenderer;
	private CharSkin skin;

	public Char() {
		setName(DEFAULT_NAME);
		
		description = "This is a Char";
		actionBar = new Array<Action>();
		actionsInventory = new Array<Action>();
		actionUnlockedPerLevel = new ArrayMap<Integer, Action>();
		aiSkillList = new Array<Integer>();
		balance = new Balance(this);
		this.maxhp = 1000;
		this.posX = 0;
		this.posY = 0;
		basehp = maxhp;
		canoverheal = true;
		skin = new CharSkin();
		animRenderer = new AnimRenderer(skin);
		animRenderer.setOwner(this);
		defaultResists = new HashMap<String, Float>();
		actualResists = new HashMap<String, Float>();
		defaultResists.put("all", new Float (1));
		actualResists.put("all", new Float (1));
		spikes = 0;
		level = 1;
		levelMultiplier = 1;
		buffPwMultiplier = 1;

		resetStats();
	}
	
	/**Reloads textures and assets
	 * 
	 */
	private void resetAssets() {
	}

	/**Reset all stats to initial values
	 * 
	 */
	public void resetStats() {
		this.hp = maxhp;
		this.activeAction = 0;
		dmg = 0;
		actualResists.clear();
		actualResists.putAll(defaultResists);
		buffs = new Array<Buff>();
		newbuffs = new Array<Buff>();
		spikes = 0;
		buffPwMultiplier = 1;
		control = true;
		updateAllActions();
		getAnimRenderer().removeProjectile();
		getAnimRenderer().setAnimToDefault();
		getAnimRenderer().reset();
//		setAnim(getDefaultAnimName());
		Util.log("resetStats, defaultResists: " + defaultResists.toString() + 
				" actualResists: " + actualResists.toString());
		//reset all actions
		for (Action a : actionBar) a.reset();
	}

	public void draw(PolygonSpriteBatch batch){
		//		batch.draw(tex, posX, posY);
		animRenderer.draw(batch, posX, posY);
	}

	public void draw(PolygonSpriteBatch batch, float x, float y) {
		animRenderer.draw(batch, (int)x, (int)y);
	}

	public void drawParticles(PolygonSpriteBatch batch) {
		animRenderer.drawEffects(batch);
	}

	/**Draw shapes, such as health bars
	 * @param shapeRenderer
	 */
	public void drawShapes(ShapeRenderer shapeRenderer) {
		//		int x = posX + 256/2 - 100; // bar start x position
		//		int y = posY + 256 + 10; // bar start y position
		int x = posX - 100; // bar start x position
		int y = posY + 256 - 50; // bar start y position
		int width = 200; // bar width
		int height = 10; // bar height
		// health normalized between 0 and 1
		float health = (float)hp / (float)Math.max(maxhp, hp);

		shapeRenderer.setColor(0.1f, 0.1f, 0f, 1);
		shapeRenderer.rect(x, y, width, height);

		shapeRenderer.setColor(1f, 1f * health, 0, 1);
		shapeRenderer.rect(x, y, width * health, height);

		//		Gdx.app.log("moo", "char: " + name + " hp: " + hp + " maxhp: " + maxhp
		//				+ " health: " + health);

	}

	public Balance getBalance() {
		return balance;
	}

	/**Finds the id of the action, returns zero if not found 
	 * @param a
	 * @return
	 */
	public int getIdOfAction(Action a) {
		return actionBar.indexOf(a, true) + 1;
	}

	/**Get the action of specific id, starting from 1
	 * @param id
	 */
	public Action getAction(int id) {
		if (id < 1 || id > actionBar.size) return null;
		return actionBar.get(id - 1);
	}

	/**Set the action of this id, starting from 1
	 * @param id
	 * @return
	 */
	public Action setAction(int id, Action action) {
		if (id < 1) return action;
		actionBar.set(id - 1, action);
		action.setOwner(this);
		return action;
	}

	public Action addAction(Action action) {
		actionsInventory.add(action);
		if (actionBar.size < maxActionBarSize) {
			actionBar.add(action);
		}
		action.setOwner(this);
		action.updatePower();
		return action;
	}

	/**Add buff to new buff list
	 * @param buff
	 */
	public void addBuff(Buff buff) {
		buff.setTarget(this);
		buffs.add(buff);
	}
	
	public void removeBuff(Buff buff) {
		buff.setTarget(this);
		buffs.removeValue(buff, true);
	}
	
	public void removeBuffType(Array<String> type){
        if (buffs == null){
        	Array<Buff> buffs = new Array<Buff>();
        }
        for (Buff buff : buffs){
        	for (String types:type){
        	
        		if (buff.getType().contains(types, true)){
        			buffs.removeValue(buff, true);
        			
        	}
        	}
        }
        
        	
	}

	/**Buffs do whatever they do when this is called.
	 * Should be called by the game when it's time for buffs to take effect
	 * such as, every turn
	 */
	public void applyBuffs() {

		updateAllActions();
		control = true;
		spikes = 0;
		actualResists.clear();
		actualResists.putAll(defaultResists);
		// list of buffs that will be removed
		// this is done because you can't remove the items in the middle of a loop
		Array<Buff> toremove = new Array<Buff>(); 

		// loop through all buffs and make their do their thing
		for (Buff buff : buffs) {
			buff.act(this);
			//			buff.setVisible(true);
			buff.incDuration(-1);
			if (buff.getDuration() == 0){
				toremove.add(buff); // this buff will be removed 
				//				buff.end(this);
			}
		}
		// remove buffs from the list of buffs to be removed
		for (Buff buff : toremove) {
			buffs.removeValue(buff, true);
		}

		// move new buffs to main buffs list
		//		buffs.addAll(newbuffs);
		//		for (Buff buff : buffs) {
		//			buff.ini(this);
		//		}
		//		newbuffs.clear();
	}

	/**Returns a string with a description of current buffs
	 * @return
	 */
	public String printBuffs() {
		String r = "(";
		Buff b;
		for (int i = 0; i < buffs.size; i++) {
			b = buffs.get(i);
			r += b.getName() + "(" + b.getDuration() + "s)";
			if (i < buffs.size - 1) r += ", "; 
		}
		r += ")";
		return r;
	}

	public void dispose() {
		tex.dispose();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosX() {
		return posX;
	}
	public int getPosY() {
		return posY;
	}

	public void setPos(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public int getHp() {
		return hp;
	}

	/**Set how much Hit Points I have
	 * Private so others use incHp instead
	 * Because settings the HP directly, without using dmg per round,
	 * could desync the players
	 * @param hp
	 */
	private void setHp(int hp) {
		this.hp = hp;
	}

	/**Increments hp by 'delta', returns final hp after change
	 * @param reflect if damage can be reflected or not 
	 * @param inc how much to change
	 */
	public void incHp(float delta, Char source, boolean reflect, Array<String> type) {
		if(type.size < 1)
			type.add("normal");
		/**if the skill has no type
		 * it's type is normal
		 */
		float y = 0;
		/**y is the value of the resist
		 * or the multiplication of the resists
		 */
		float z;
		/**used only if there is more than one resist. 
		 * It keeps the values of the other resists
		 * and its multiplied by y.
		 * 
		 */
		for (int x = 0; x < type.size; x++){
			if(actualResists.containsKey(type.get(x)) == false)
				actualResists.put(type.get(x), actualResists.get("all"));
			if (x == 0)
				y = actualResists.get(type.get(x));
			else{
				z = actualResists.get(type.get(x));
				y = z*y;
			}

		}
		dmg += delta *y;
		Util.log("incHp, type: " + type.toString());
		Util.log("incHp, defaultResists: " + defaultResists.toString() + 
				" actualResists: " + actualResists.toString());
		if (reflect == true && getSpikes()>0)
		{
			String[] a = {"counter"};
			target.incHp(-getSpikes(), this, false, new Array<String>(a));
		}
	}

	/**Applies the damage to be taken this round. After all the skills used this round are considered.
	 * Called by the game logic
	 * 
	 * @return
	 */
	public int applyDmg() {
		int delta = dmg; // sets current damage to a temporary value
		dmg = 0; // then reset the current damage
		if (delta < 0) {
			if (-delta < hp) {
				return hp += delta;
			}
			return hp = 0;
		}
		else if (delta > 0) {
			hp += delta;
			if (hp > maxhp && !canoverheal) {
				return hp = maxhp;
			}
		}
		return hp;
	}

	/**Update cooldowns of all action
	 * 
	 */
	public void updateCooldowns() {
		for (Action a : actionBar) a.updateCooldown();
	}

	/**Update all that needs to be updated every logic tick
	 * use actions, update cooldowns
	 */
	public void updateAll() {
		applyBuffs(); 
		Action a = getActiveAction();
		if (a != null) a.act(this, getTarget());

		//		applyDmg();
		updateCooldowns();
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxBasehp(int basehp) {
		this.basehp = basehp;
		updateHp();
		// hp cannot be more than maxhp, fix that
		if (hp < maxhp) {
			hp = maxhp;
		}
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(String tex) {
		this.tex = new Texture(Gdx.files.internal(tex));
	}

	public Action getActiveAction() {
		return getAction(getActiveActionId());

	}

	public int getActiveActionId() {
		return activeAction;
	}

	public void setActiveActionId(int activeAction) {
		this.activeAction = activeAction;
	}

	/**Gets how many actions I have
	 * @return
	 */
	public int getNumOfActions() {
		return actionBar.size;
	}

	/**gets the list of actions
	 * 
	 * Avoid using this unless there's no other way
	 * 
	 * @return
	 */
	public Array<Action> getActionBar() {
		return actionBar;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getFullDescription() {
		//		return description + " avgdps: " + balance.getAvgDps();
		return name + " " + description + " level " + level  + " (k: " + levelMultiplier + ") " + getActionListString();
	}

	/**
	 * @param description the description to set
	 */
	public void setFullDescription(String description) {
		this.description = description;
	}

	/**A string with the name of every skill
	 * @return
	 */
	public String getActionListString() {
		String list = "";
		for (Action a : actionBar) {
			list += a.getName() + ", ";
		}
		if (list.length()>0) list = list.substring(0, list.length()-2);
		return list;
	}

	/**
	 * @return the target
	 */
	public Char getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Char target) {
		this.target = target;
	}

	public int getAiSkill(int round){
		Util.log(aiSkillList.toString());
		if (aiSkillList != null && aiSkillList.size >= round){

			return aiSkillList.get(round - 1);


		}
		else {
			int Min = 1;
			int Max = 4;
			int x = Min + (int)(Math.random() * ((Max - Min) + 1));
			if (getAction(x) == null || getAction(x).isLegal() != true)
			{
				do{
					x = Min + (int)(Math.random() * ((Max - Min) + 1));
				} while (getAction(x) == null || getAction(x).isLegal() != true);
			}
			return x;
		}
	}

	/**
	 * @return the powerMultiplier
	 */
	public float getPowerMultiplier() {
		return levelMultiplier;
	}

	/**
	 * @param powerMultiplier the powerMultiplier to set
	 */
	public void setPowerMultiplier(float powerMultiplier) {
		this.levelMultiplier = powerMultiplier;
		updateAllActions();
		updateHp();
	}

	/**Updates max hp with multiplier
	 */
	private void updateHp() {
		float pct = ((float) hp)/((float) maxhp); // get current heal %
		maxhp = (int) (basehp * levelMultiplier);
		hp = (int) Math.ceil(pct*hp); // keep health % the same

	}

	/**Update power of all actions, remultiply base power by power
	 * multipliers
	 * 
	 */
	private void updateAllActions() {
		for (Action a : getActionBar()) {
			a.setBuffPwMultiplier(1);
			a.updatePower();
			a.update();
		}
	}

	/**Increases current level by 1 and updates
	 * everything that needs it
	 * 
	 */
	public void levelUp() {
		level++;
		setPowerMultiplier(1f + (level - 1f)*0.1f);
		unlockSkillsForLevel(level);
		Util.log(name + " leveled up, level: " + level + " pmult: " + levelMultiplier);
	}

	private void unlockSkillsForLevel(int level) {
		if (actionUnlockedPerLevel.containsKey(level)) {
			Action a = actionUnlockedPerLevel.get(level);
			if (a != null) {
				addAction(a);
				Util.log(this.getName() + " unlocks a new skill: " + a.getName());
			}
		}
	}

	public String toString() {
		return getFullDescription();
	}

	/**
	 * @return the actionsInventory
	 */
	public Array<Action> getActionsInventory() {
		return actionsInventory;
	}

	/**Remove the action with this id from the action bar
	 * @param selected
	 */
	public void removeActionFromBar(int id) {
		actionBar.removeIndex(id);
	}

	/**Adds this action from the inventory to the action bar
	 * @param id
	 */
	public void addActionFromInv(int id) {
		if (actionBar.size >= maxActionBarSize) return;
		Action actionToAdd = actionsInventory.get(id);
		if (getIdOfAction(actionToAdd) != 0) return;
		actionBar.add(actionToAdd);
	}

	public void setAiSkillList(Array<Integer> x) {
		aiSkillList.addAll(x);
	}

	/**adds an action to be unlocked this level
	 * 
	 * @param a
	 */
	public Action addActionForLevel(int level, Action a) {
		actionUnlockedPerLevel.put(level, a);
		return a;
	}

	/**Gets the action to be unlocked on this level
	 * @param level
	 * @return
	 */
	public Action getActionForLevel(int level) {
		return actionUnlockedPerLevel.get(level);
	}

	/**Sets scale
	 * @param scale
	 */
	public void setScale(float scale) {
		animRenderer.setScale(scale);
		//		restartAnims();
	}

	private void restartAnims() {
		//		for (Action a : actionBar) a.reset();
		for (Action a : actionBar) {
			a.getAnim().start(animRenderer);
		}
	}

	/**Get scale
	 * @return
	 */
	public float getScale() {
		return animRenderer.getScale();
	}

	/**Sets a color object to change the char image color with
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		animRenderer.setColor(color);
	}

	/**Set the color of the character
	 * @param r
	 * @param g
	 * @param b
	 * @param alpha
	 */
	public void setColor(float r, float g, float b, float alpha) {
		Color color = new Color(r, g, b, alpha);
		setColor(color);
	}
	
	/**Sets the type of a specific body part
	 * valid body parts are: hands
	 * 
	 * @param part
	 * @param name
	 */
	public void setBodyPart(String part, String name) {
		part = part.toLowerCase();
		if (part.equals("hands")) {
			skin.setHands(name);
		}
		if (part.equals("tail")) {
			skin.setTailtype(name);
		}
	}

	/**returns true if the char is flipped horizontally, false otherwise
	 * @return
	 */
	public boolean getFlipX() {
		return animRenderer.getFlipX();
	}

	/**returns -1 if the char is flipped horizontally, 1 otherwise
	 * @return
	 */
	public int getFlipXMultiplier() {
		if (getFlipX()) return -1;
		return 1;
	}

	/**Flip char image horizontally
	 * @param flip
	 */
	public void flipX(boolean flip) {
		animRenderer.flipX(flip);
	}

	/**Flip char image vertically
	 * @param flip
	 */
	public void flipY(boolean flip) {
		animRenderer.flipY(flip);
	}

	public float getSpikes() {
		return spikes;
	}

	public void setSpikes(float power) {
		this.spikes = power;
	}

	public void setPos(float x, float y) {
		setPos((int)x, (int)y);
	}

	/**Gets the anim data, which contains all the data about each animation
	 * this Char can use
	 * 
	 * @return
	 */
	public CharSkin getAnimData() {
		return skin;
	}

	/**Sets the animation to the one of the current active action
	 * @param animname
	 */
	public void setAnimToActiveAction() {
		if (getActiveAction() == null) {
			animRenderer.setAnimToDefault();return;
		}
		CharAnim anim = getActiveAction().getAnim();
		Effect effect = getActiveAction().getAnimEffect();
		animRenderer.setAnim(anim, effect);
		//		animRenderer.setAnim(anim.getName(), effect);
	}

	/**Sets the name of the current animation to play
	 * @param animname
	 */
	public void setAnim(String animname) {
		animRenderer.setAnim(animname);
	}

	/**Name of current animation being played
	 * @return
	 */
	public String getAnimName() {
		return animRenderer.getAnimName();
	}

	/**The name of the default animation, which is the animation
	 * the char will default to when nothing is specified
	 * @return
	 */
	public String getDefaultAnimName() {
		return animRenderer.getDefaultAnimName();
	}

	/**Returns true if the Char is currently using the default animation,
	 * false otherwise
	 * @return
	 */
	public boolean isUsingDefaultAnim() {
		if (getAnimName().equals(getDefaultAnimName())) return true;
		return false;
	}

	/**
	 * @return the buffPwMultiplier
	 */
	public float getBuffPwMultiplier() {
		return buffPwMultiplier;
	}

	/**
	 * @param buffPwMultiplier the buffPwMultiplier to set
	 */
	public void setBuffPwMultiplier(float buffPwMultiplier) {
		this.buffPwMultiplier = buffPwMultiplier;
		updateAllActions();
		updateHp();
	}

	public AnimRenderer getAnimRenderer() {
		return animRenderer;
	}

	public void editDefaultResists(String type, float value){
		defaultResists.put(type, value);
		actualResists.putAll(defaultResists);

	}

	public void editActualResists(String type, float value){
		actualResists.put(type, value);

	}


	public HashMap<String, Float> getResists(){
		return actualResists;
	}

	public Array<Buff> getBuffs() {
		return buffs;
	}

	/**Tells this character that he is supposed to get hit now
	 * So play Get Hit animation and do all the appropriate calculations
	 */
	public void setGetHit() {
		HitEffect e = new HitEffect(0.35f);
		setGetHit(e);
	}
	
	/**Tells this character to get and which effect to use in it
	 * @param e
	 */
	public void setGetHit(Effect e) {
		getAnimRenderer().addCharEffect(e);
		// only shows GetHit anim if not casting, so it never gets interrupted
		if (!isUsingDefaultAnim()) return; 
		getAnimRenderer().setAnim(new GetHit01());
	}
	
	public Char getClone() {
		try {
//			Constructor<? extends Char> constructor = this.getClass().getConstructor(String.class);
//			Object clone = constructor.newInstance(this.getName());
			Constructor<? extends Char> constructor = this.getClass().getConstructor();
			Object clone = constructor.newInstance();
			return (Char)clone;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean getControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

}
