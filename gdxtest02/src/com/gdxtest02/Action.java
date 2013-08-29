package com.gdxtest02;

public interface Action {
	
	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self, Char target);
	
	public String getType();
	
	public void setType(String type);

}
