package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Punch01;

public class DispelSelfByType extends Action implements Cloneable {
	
	public void ini() {
		setName("Dispel Self");
		setAnim(new Punch01());
		
	}
	public void update() {
		String sec = null;
		/** Secondary string used to keep 
		 * string s values
		 */
		for (int x = 0; x < getTypeList().size; x++){
			String s = getTypeList().get(x);
			if( sec == null){
				sec = s;	
				setDescription("remove effects of the following type: " + sec );
				/**if there is only one resist type
				 * "resist by" is written instead of
				 *  "resists by"
				 */
			}
			else{			
				sec = sec+", " + s;
				setDescription("remove effects of the following types: " + sec);
			}
			
		}
	}

	@Override
	protected void go(Char self, Char target) {
	self.removeBuffType(this.getTypeList());
	}
	
	
	public DispelSelfByType() {
		super();
	}
	
	public DispelSelfByType(int cooldown) {
		super(cooldown);
	}
	
}
