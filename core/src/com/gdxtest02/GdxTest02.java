package com.gdxtest02;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryo.Kryo;
import com.gdxtest02.chars.TestChar01;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.util.Util;

public class GdxTest02 extends Game {
	public static int VIRTUAL_WIDTH = 800;
	public static int VIRTUAL_HEIGHT = 480;
	
	/**Set this to true to enable debug Chars and options
	 */
	public static final boolean DEBUG_MODE = true;
	
	public PolygonSpriteBatch batch;
	public BitmapFont font;
	private GameState gamestate;
	private static GdxTest02 game;

	private static Kryo kryo;
	

	/**
	 * @return the kryo
	 */
	public static Kryo getKryo() {
		return kryo;
	}

	public void create() {
		gamestate = new GameState(this);
		kryo = new Kryo();
//		gamestate.setPlayer(new Char01("p1"));
		
		batch = new PolygonSpriteBatch();
		
		Util.ini();
		//Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		game = this;
		
	}
	
	public static GdxTest02 getInstance() {
		return game;
	}

	public void render() {
		super.render(); //important!
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	/**
	 * @return the game state
	 */
	public GameState getGameState() {
		return gamestate;
	}

}