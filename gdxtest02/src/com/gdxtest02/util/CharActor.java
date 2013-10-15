package com.gdxtest02.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.gdxtest02.AnimData;
import com.gdxtest02.Char;
import com.gdxtest02.CharAnim;

public class CharActor extends Image {
	private Char player;
	private static final float SIZEX = 256;
	private static final float SIZEY = 256;
	
	private boolean flipx;
	
	private CharAnim charAnim;

	public CharActor(Char player) {
		this.player = player;
		charAnim = new CharAnim(player.getAnimData());
		setTouchable(Touchable.disabled);
//		charAnim.setScale(getWidth()/SIZEX, getHeight()/SIZEY);
	}
	
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
//		if (getParent() != null) {
//			float x = getParent().getX();
//			float y = getParent().getY();
//			player.setPos(x, y);
//			player.setPos(getX(), getY());
//		}
//		player.setScale(getWidth()/SIZEX, getHeight()/SIZEY);
//		if (flipx != player.getFlipX()) player.flipX(true);
//		player.draw(batch, getX() + (getWidth())/2, getY());
		if (flipx != player.getFlipX()) charAnim.flipX(true);
		charAnim.draw(batch, getX() + (getWidth())/2, getY());
//		player.draw(batch, getX(), getY());
	}
	
	public void flipX(boolean b) {
		flipx = b;
		
	}
	
	public void setChar(Char c) {
		player = c;
		charAnim.setData(c.getAnimData());
		
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#setWidth(float)
	 */
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		if (charAnim != null) charAnim.setScale(getWidth()/SIZEX);
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#setHeight(float)
	 */
	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		if (charAnim != null) charAnim.setScale(getWidth()/SIZEX);
	}

}
