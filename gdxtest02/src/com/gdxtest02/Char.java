package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.gdxtest02.actions.Dmg;

public class Char {
	private String name;
	private int hp;
	private int maxhp;
	private Rectangle box;
	private Texture tex;
	private String texname;
	private int posX, posY;
	/**Number of the current active action
	 * 
	 */
	private int activeAction;
	private Array<Action> actions;

	public Char(String name) {
		this.name = name;
		this.posX = 0;
		this.posY = 0;
		this.maxhp = 1000;
		this.hp = maxhp;
		this.activeAction = 0;
		actions = new Array<Action>();
		actions.add(new Dmg(100));
		actions.add(new Dmg());
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(tex, posX, posY);
	}
	
	/**Draw shapes, such as health bars
	 * @param shapeRenderer
	 */
	public void drawShapes(ShapeRenderer shapeRenderer) {
		int x = posX + 256/2 - 100; // bar start x position
		int y = posY + 256 + 10; // bar start y position
		int width = 200; // bar width
		int height = 10; // bar height
		// health normalized between 0 and 1
		float health = (float)hp / (float)maxhp;
		
		shapeRenderer.setColor(0.1f, 0.1f, 0f, 1);
		shapeRenderer.rect(x, y, width, height);
		
		shapeRenderer.setColor(1f, 1f * health, 0, 1);
		shapeRenderer.rect(x, y, width * health, height);
		
//		Gdx.app.log("moo", "char: " + name + " hp: " + hp + " maxhp: " + maxhp
//				+ " health: " + health);
		
	}
	
	/**Get the action of specific id, starting from 1
	 * @param id
	 */
	public Action getAction(int id) {
		if (id < 1) return null;
		return actions.get(id - 1);
	}
	
	/**Set the action of this id, starting from 1
	 * @param id
	 * @return
	 */
	public void setAction(int id, Action action) {
		if (id < 1) return;
		actions.set(id - 1, action);
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
		return posX;
	}
	public int getPosY() {
		return posY;
	}
	
	public void setPos(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	/**Incremeants hp by 'delta', returns final hp after change
	 * @param inc how much to change
	 * @return
	 */
	public int incHp(int delta) {
		if (delta < 0) {
			if (-delta < hp) {
				return hp += delta;
			}
			return hp = 0;
		}
		else if (delta > 0) {
			hp += delta;
			if (hp > maxhp) {
				return hp = maxhp;
			}
		}
		return hp;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
		// hp cannot be more than maxhp, fix that
		if (hp < maxhp) {
			hp = maxhp;
		}
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(String tex) {
		this.tex = new Texture(Gdx.files.internal(tex));
	}

	public Action getActiveAction() {
		return getAction(getActiveActionId());
	}
	
	public int getActiveActionId() {
		return activeAction;
	}

	public void setActiveAction(int activeAction) {
		this.activeAction = activeAction;
	}

}
