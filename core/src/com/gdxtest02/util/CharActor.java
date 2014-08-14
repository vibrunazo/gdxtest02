package com.gdxtest02.util;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gdxtest02.CharSkin;
import com.gdxtest02.Char;
import com.gdxtest02.AnimRenderer;

public class CharActor extends Image {
	private Char player;
	private static final float SIZEX = 256;
	private static final float SIZEY = 256;
	
	private boolean flipx;
	
	private AnimRenderer charRenderer;

	public CharActor(Char player) {
		this.player = player;
		charRenderer = new AnimRenderer(player.getAnimData());
		setTouchable(Touchable.disabled);
	}
	
	public void draw(PolygonSpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		if (flipx != player.getFlipX()) charRenderer.flipX(true);
		charRenderer.draw(batch, getX() + (getWidth())/2, getY());
	}
	
	public void flipX(boolean b) {
		flipx = b;
		
	}
	
	public void setChar(Char c) {
		player = c;
		charRenderer.setData(c.getAnimData());
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#setWidth(float)
	 */
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		if (charRenderer != null) charRenderer.setScale(getWidth()/SIZEX);
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#setHeight(float)
	 */
	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		if (charRenderer != null) charRenderer.setScale(getWidth()/SIZEX);
	}
	
	public void updateScale() {
		charRenderer.setScale(getWidth()/SIZEX);
	}
	
	public void setSize(float size) {
		charRenderer.setScale(size/SIZEX);
	}

}
