package com.gdxtest02.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		charAnim.setScale(getWidth()/SIZEX, getHeight()/SIZEY);
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

}
