package com.gdxtest02.actions;

import com.gdxtest02.Action;
import com.gdxtest02.Char;
import com.gdxtest02.anims.Punch01;

public class DispelTargetByType extends Action implements Cloneable {
	
	public void ini() {
		setName("Dispel Target");
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
				setDescription("remove effects of the following type: " + sec + "from the target");
				/**if there is only one resist type
				 * "resist by" is written instead of
				 *  "resists by"
				 */
			}
			else{			
				sec = sec+", " + s;
				setDescription("remove effects of the following types: " + sec + "from the target");
			}
			
		}
	}

	@Override
	protected void go(Char self, Char target) {
	target.removeBuffType(this.getTypeList());
	}
	
	
	public DispelTargetByType() {
		super();
	}
	
	public DispelTargetByType( int cooldown) {
		super(cooldown);
	}
	
}
