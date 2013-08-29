package com.gdxtest02;

public class Action {
	private String type;
	
	public Action() {
		this.type = "dmg";
	}
	
	/**Do whatever is it that you do
	 * @param self yourself, the char doing the action
	 * @param target your target
	 */
	public void act(Char self, Char target) {
		target.incHp(-10);
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
