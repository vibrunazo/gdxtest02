/**
 * 
 */
package com.gdxtest02;

/**An Action that puts a Buff
 * 
 * @author Vandré
 *
 */
public class PutBuffAction extends Action {
	protected Buff buff;
	
	public PutBuffAction setEffect(Effect effect) {
		if (buff == null) return this;
		buff.setEffect(effect);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Action#go(com.gdxtest02.Char, com.gdxtest02.Char)
	 */
	@Override
	protected void go(Char self, Char target) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Action#ini()
	 */
	@Override
	public void ini() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.gdxtest02.Action#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public PutBuffAction() {
		super();
	}
	
	public PutBuffAction(float value) {
		super(value);
	}
	
	public PutBuffAction(float value, int cooldown) {
		super(value, cooldown);
	}
	
	public PutBuffAction(float value, int cooldown, int duration) {
		super(value, cooldown, duration);
	}

}
