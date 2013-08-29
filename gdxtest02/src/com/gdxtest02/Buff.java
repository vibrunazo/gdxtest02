package com.gdxtest02;


public interface Buff {
	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self);
	
	public String getName();
	public void setName(String name);
	
	/**Duration is how long the buff lasts, in seconds or turns
	 * -1 means it's infinite
	 */
	public int getDuration();
	public void setDuration(int duration);
	public void incDuration(int delta);
}
