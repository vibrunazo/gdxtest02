package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Char {
	private String name;
	private int hp;
	private int maxhp;
	private Rectangle box;
	private Texture tex;
	private String texname;

	public Char(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
