package com.gdxtest02;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public abstract class Action {
	protected String name = "Action";
	protected float power = 100;
	protected float power02 = 50;
	protected int cooldown = 0;
	private int curcooldown = 0;
	protected int duration = 5;
	//private String type = "normal";
	private boolean reflect = false;
	private String description = "This is a skill";
	private float avgdps;
	private CharAnim anim;
	protected Char owner;
	private float basepower;
	private Effect animeffect;
	private float buffpwmultiplier = 1;
	private Array<String> acttypelist = new Array<String>();
	
	public Action() {
//		ini();
	}

	public Action(float value) {
		power = (float) /*Math.ceil*/(value);
		basepower = power;
//		ini();	
	}
	
	public Action(float value, int cooldown) {
		power = (float) /*Math.ceil*/(value);
		basepower = power;
		this.cooldown = cooldown;
//		ini();
	}
	
	public Action(int cooldown) {
		this.cooldown = cooldown;
//		ini();
	}
	
	public Action(float value, int cooldown, int duration) {
		power = (float) /*Math.ceil*/(value);
		basepower = power;
		this.cooldown = cooldown;
		this.duration = duration;
//		ini();
	}
	
	public Action(float value, int cooldown, int duration, float value2) {
		power = (float) /*Math.ceil*/(value);
		basepower = power;
		this.cooldown = cooldown;
		this.duration = duration;
//		ini();
	}
	
	/**Updates the power of this action based on the level multiplier and buff multiplier
	 * 
	 */
	public void updatePower() {
		if (owner == null) return;
		power = (float) /*Math.ceil*/(basepower) * owner.getPowerMultiplier() * getBuffPwMultiplier();
		
	}
	
	/**Sets the owner of this action, will be used to calculate level and
	 * power multiplier
	 * @param character
	 */
	public Action setOwner(Char character) {
		owner = character;
		ini();
		return this;
	}

	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self, Char target) {
//		act();
		if (curcooldown > 0) return;
		if (getCurcooldown() > 0) return;
		go(self, target);
		if (cooldown > 0) {
			curcooldown = cooldown + 1;
		}
	}

	/**Updates the current cooldown, reduce it by 1
	 * 
	 */
	public void updateCooldown() {
		if (curcooldown > 0) curcooldown -= 1;
	}
	
	/**Reset all stats to initial values
	 * 
	 */
	public void reset() {
		curcooldown = 0;
	}
	
	/**The actual action class needs to implement this method to
	 * do its thing
	 * @param self the Char doing the action
	 * @param target the Char being targetted
	 */
	abstract protected void go(Char self, Char target);
	
	/**Do whatever initialization here, only called once
	 * 
	 */
	abstract public void ini();
	
	/**Do whatever updates here, called every time the action needs
	 * to be updated
	 * call this to update description
	 * 
	 */
	abstract public void update();

	/**Gets the action name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**Sets the name, returns self
	 * @param name
	 * @return
	 */
	public Action setName(String name) {
		this.name = name;
		return this;
	}
	
	/**Gets the action description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**Sets the description, returns self
	 * @param name
	 * @return
	 */
	public Action setDescription(String description) {
		this.description = description;
		return this;
	}
	
	/**Gets the tooltip description, includes formal description,
	 * power, cost, cooldown
	 * @return
	 */
	public String getTooltip() {
		return getName() + " - " + getDescription() + " " + getCooldown() + "sec cooldown.";
	}

	/**Gets the maximum cooldown
	 * @return
	 */
	public int getCooldown() {
		return cooldown;
	}
	/**Sets the maximum cooldown, returns self
	 * @param cooldown
	 * @return
	 */
	public Action setCooldown(int cooldown) {
		this.cooldown = cooldown;
		return this;
	}
	
	/**Get duration
	 * @return
	 */
	public int getDuration() {
		return duration;
	}
	/**Sets duration, returns self
	 * @param duration
	 * @return
	 */
	public Action setDuration(int duration) {
		this.duration = duration;
		return this;
	}

	/**Returns the current cooldown
	 * @return
	 */
	public int getCurcooldown() {
		return curcooldown;
	}

	/**Sets the current cooldown, returns self
	 * @param curcooldown
	 * @return
	 */
	public Action setCurcooldown(int curcooldown) {
		this.curcooldown = curcooldown;
		return this;
	}
	
	public void incCurcooldown(int delta) {
		this.curcooldown += delta;
	}
	
	/**Returns if this action is legal or not right
	 * Checks cooldown, mana, requirements etc
	 */
	public boolean isLegal() {
//		log("islegal name: " + name + " cooldown: " + cooldown + " curcooldown: " + curcooldown);
		if (curcooldown == 0) return true;
		return false;
	}
	
	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

	/**
	 * @return the power
	 */
	public float getPower() {
		return power;
	}
	
	public float getPower02() {
		return power02;
	}


	/**
	 * @param power the power to set
	 */
	public void setPower(float power) {
		this.power = power;
	}
	
	public void setPower02(float power02) {
		this.power02 = power02;
	}
	
	public void setAvgDps(float avg) {
		avgdps =  avg;
	}
	
	public float getAvgDps() {
		return avgdps;
	}
	
	/**How many damage this skill does after X rounds
	 * @param rounds
	 * @return
	 */
	public float getDmgAfterRounds(int rounds) {
		return power;
	}
	
	/**How much heal this skill does after X rounds
	 * @param rounds
	 * @return
	 */
	public float getHealAfterRounds(int rounds) {
		return 0;
	}
	
	/**How much damage this skill can do this round
	 * @param round
	 * @param maxrounds
	 * @return
	 */
	public float getDmgThisRound(int round, int maxrounds) {
		return getDmgAfterRounds(maxrounds - round + 1);
	}
	
	/**How much heal this skill can do this round
	 * @param round
	 * @param maxrounds
	 * @return
	 */
	public float getHealThisRound(int round, int maxrounds) {
		return getHealAfterRounds(maxrounds - round + 1);
	}

	/**Gets the name of the animation for this action
	 * @return
	 */
	public String getAnimName() {
		return anim.getName();
	}

	/**Sets the name of the animation this action should show when active
	 * @param anim
	 */
	public Action setAnim(CharAnim anim) {
		this.anim = anim;
		return this;
	}
	
	/**Sets the name of the animation this action should show when active
	 * @param anim
	 */
	public Action setAnim(CharAnim anim, Effect effect) {
		this.anim = anim;
		this.animeffect = effect;
		return this;
	}
	
	/**
	 * @return if action can be reflected
	 */
	public boolean getReflect() {
		return reflect;
	}

	/**Sets if the action can be reflected or not
	 * @param reflect
	 */
	public Action setReflect(boolean reflect) {
		this.reflect = reflect;
		return this;
	}

	/**
	 * @return the animeffect
	 */
	public Effect getAnimEffect() {
		return animeffect;
	}

	/**
	 * @param animeffect the animeffect to set
	 */
	public Action setAnimEffect(Effect animeffect) {
		this.animeffect = animeffect;
		return this;
	}

	/**Set the anim from the name of the animation
	 * @param name
	 * @return
	 */
//	public Action setAnim(String name) {	
//		AnimRenderer renderer = owner.getAnimRenderer();
//		this.anim = renderer.getAnimByName(name);
//		return this;
//	}

	public CharAnim getAnim() {
		return anim;
	}

	/**
	 * @return the buffPwMultiplier
	 */
	public float getBuffPwMultiplier() {
		return buffpwmultiplier;
	}

	/**
	 * @param buffPwMultiplier the buffPwMultiplier to set
	 */
	public void setBuffPwMultiplier(float buffPwMultiplier) {
		buffpwmultiplier = buffPwMultiplier;
	}
	
	/**Gets the action type
	 * @return
	 */
//	public String getType() {
//		return type;
//	}
	/**Sets the type
	 * @param strings
	 * @return
	 */
//	public Action setType(String type) {
//		this.type = type;
//		return this;
//	}
	
	public void addType(String[] strings){
		if(acttypelist == null)
			acttypelist = new Array<String>();
		acttypelist.addAll(strings);
	}
	
	public Array<String> getTypeList(){
		if(acttypelist == null){
			acttypelist = new Array<String>();
		}
		return acttypelist;
	}
	
}
