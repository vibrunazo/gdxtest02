/**
 * 
 */
package com.gdxtest02;

import com.gdxtest02.util.Util;

/**An Action that puts a Buff
 * 
 *
 */
public class PutBuffAction extends Action {
	protected Buff buffdummy;
	private Effect buffeffect;
	
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
	
	public PutBuffAction(float value, int cooldown, int duration, float value02) {
		super(value, cooldown, duration, value02);
	}

	/**Gets a copy of the Buff this action should cast, ready to use
	 * @return a copy of this action's buff
	 */
	protected Buff getNewBuffInstance() {
		buffdummy.setRandomOffset();
		buffdummy.resetEffectPosition();
		return Util.copy(buffdummy);
	}

	/**Sets which Buff this action will cast
	 * @param buff the buff to set
	 */
	public void setBuff(Buff buff) {
		this.buffdummy = buff;
		if (buffeffect != null)	buff.setEffect(buffeffect);
	}
	
	/**Sets the effect this Buff will use
	 * @param buffeffect
	 * @return
	 */
	public PutBuffAction setBuffEffect(Effect effect) {
		buffeffect = effect;
		return this;
		
	}

	/**
	 * @return the buffdummy
	 */
	public Buff getBuffdummy() {
		return buffdummy;
	}

}
