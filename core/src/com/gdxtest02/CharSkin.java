package com.gdxtest02;

import com.badlogic.gdx.graphics.Color;

public class CharSkin {
	private Color baseColor;
	private String hands;
	private String tailtype;
	private float tailscaley = 1f;
	private float tailscalex = 1f;
	
	public CharSkin() {
		
	}

	/**
	 * @return the baseColor
	 */
	public Color getBaseColor() {
		return baseColor;
	}

	/**
	 * @param baseColor the baseColor to set
	 */
	public void setBaseColor(Color baseColor) {
		this.baseColor = baseColor;
	}

	/**
	 * @return the hands
	 */
	public String getHands() {
		return hands;
	}

	/**
	 * @param hands the hands to set
	 */
	public void setHands(String hands) {
		this.hands = hands;
	}

	/**
	 * @return the tailtype
	 */
	public String getTailtype() {
		return tailtype;
	}

	/**
	 * @param tailtype the tailtype to set
	 */
	public void setTailtype(String tailtype) {
		this.tailtype = tailtype;
	}

	/**
	 * @return the tailscalex
	 */
	public float getTailscalex() {
		return tailscalex;
	}

	/**
	 * @param tailscalex the tailscalex to set
	 * @param tailscaley the tailscaley to set
	 */
	public void setTailscale(float tailscalex, float tailscaley) {
		this.tailscalex = tailscalex;
		this.tailscaley = tailscaley;
	}

	/**
	 * @return the tailscaley
	 */
	public float getTailscaley() {
		return tailscaley;
	}

}
