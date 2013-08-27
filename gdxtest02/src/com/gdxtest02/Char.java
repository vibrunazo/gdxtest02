package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Char {
	private String name;
	private int hp;
	private int maxhp;
	private Rectangle box;
	private Texture tex;
	private String texname;
	private int x, y;

	public Char(String name) {
		this.name = name;
		this.x = 0;
		this.y = 0;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(tex, x, y);

	}
	
	public void drawShapes(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(1, 1, 0, 1);
		shapeRenderer.rect(x + 256/2 - 100, y + 256 + 10, 200, 10);
		
	}
	
	public void dispose() {
		tex.dispose();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getPosX() {
		return x;
	}
	public int getPosY() {
		return y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(String tex) {
		this.tex = new Texture(Gdx.files.internal(tex));
	}

}
