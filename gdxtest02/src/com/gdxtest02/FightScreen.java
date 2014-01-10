package com.gdxtest02;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gdxtest02.chars.Char01;
import com.gdxtest02.chars.Char02;
import com.gdxtest02.gamestate.LevelState;

import static com.gdxtest02.gamestate.LevelState.*;

public class FightScreen implements Screen {


	public static final int CONTROL_AI = 0;
	public static final int CONTROL_HUMAN = 1;
	private static final float PAUSE_TIME = 4;
	
	

	final private GdxTest02 game;

	private FPSLogger fps;

	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	private Char p1;
	private Char p2;

	/**fightstate, is the game paused or running?
	 * "go" = fight can go on
	 * "paused" = fight is paused
	 */
	private String fightstate; 
	private int round = 1;
	private FightScreenUI ui;
	private Class<? extends LevelScreen> nextLevel;

	public int p1control;
	public int p2control;
	private float delta;
	private float gametime;
	private float pausetime;
	private String a1name;
	private String a2name;

	public FightScreen(GdxTest02 game) {
		this(game, new Char01("p1"), new Char02("p2"));
		
	}
	
	public FightScreen(final GdxTest02 game, Char player1, Char player2) {
		this(game, player1, player2,
				CONTROL_HUMAN, CONTROL_AI);
	}
	
	public FightScreen(final GdxTest02 game, Char player1, Char player2,
			int p1control, int p2control) {
		this.game = game;
		this.p1control = p1control;
		this.p2control = p2control;
		fps = new FPSLogger();

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);

//		p1 = new Char03("p1");
		p1 = player1;
		p1.setPos(20 + 128, 200);
		p1.setScale(0.7f);
		p1.resetStats();

//		p2 = new Char02("p2");
		p2 = player2;
		p2.setPos(800-20-256 + 128, 200);
		p2.setScale(0.7f);
		p2.flipX(true);
		p2.resetStats();
		
		p1.setTarget(p2);
		p2.setTarget(p1);
		
		ui = new FightScreenUI(game, this);
		ui.setupUi();
		
		logPlayerDescriptions();
		updateUi();
		
		fightstate = "go";
	}

	private void logPlayerDescriptions() {
		ui.logToConsole("p1: " + p1.getFullDescription());
		ui.logToConsole("p2: " + p2.getFullDescription());
	}

	/**Will change to this level after fight is over
	 * @param next_level
	 */
	public void setNextLevel(Class<? extends LevelScreen> next_level) {
		this.nextLevel = next_level;
	}
	
	protected void restart() {
		FightScreen newgame = new FightScreen(game, p1, p2, p1control, p2control);
		newgame.setNextLevel(nextLevel);
		game.setScreen(newgame);
//		dispose();
	}
	
	protected void back() {
		game.setScreen(new MainMenuScreen(game));
//		dispose();
	}

	@Override
	public void render(float delta) {
		this.delta = delta;
		updateLogic();

		// clear the screen with a dark blue color. The
		// arguments to glClearColor are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
//		game.font.draw(game.batch, "Test", 0, 480);

		// tell chars to draw themselves
		p1.draw(game.batch);
		p2.draw(game.batch);
		
		p1.drawParticles(game.batch);
		p2.drawParticles(game.batch);

		game.batch.end();

		// tell chars to draw their shapes (health bars)
		shapeRenderer.begin(ShapeType.Filled);
		p1.drawShapes(shapeRenderer);
		p2.drawShapes(shapeRenderer);
		shapeRenderer.end();

		if(!Gdx.input.isTouched()) ui.updateScroll(delta);
		ui.draw();

		//		fps.log();

	}

	private void updateLogic() {
		gametime += delta;
		
		if (fightstate.contains("anim")) {
			updateAnimTime();
			if (fightstate.contains("p1") && p1.isUsingDefaultAnim()) {
				// if p1 just ended it's attack anim and is back to default
				// then start p2 anim
//				log("p1 using default anim");
				setAnimStateP2();
			}
			
			if (fightstate.contains("p2") && p2.isUsingDefaultAnim()) {
				// if p2 just ended it's attack anim and is back to default
				// then end the anim state
//				log("p2 using default anim");
				endAnimState();
			}
		}
	}
	
	private void setAnimStateP1() {
		fightstate = "anim p1";
		p1.setAnimToActiveAction();
	}
	
	private void setAnimStateP2() {
		fightstate = "anim p2";
		p2.setAnimToActiveAction();
	}

	private void updateAnimTime() {
		pausetime -= delta;
		pausetime = Math.max(pausetime, 0);
		ui.setAnimTime((int)Math.ceil(pausetime));
		if (pausetime <= 0) {
			endAnimState();
		}
	}

	private void endAnimState() {
		pausetime = 0;
		ui.setAnimTime(0);
		if (isFightOver()) {
			endFight();
		}
		else {
			fightstate = "go";
		}
	}


	/**calculate damages
	 * 
	 * Actions might be null!
	 */
	public void go() {
		if (fightstate.equals("paused")) return;
		if (fightstate.contains("anim")) {
			endAnimState();
			return;
		}
		
		//preparing
		if (p1control == CONTROL_AI){
			p1.setActiveActionId(p1.getAiSkill(round));	
		}
		if (p2control == CONTROL_AI){
			p2.setActiveActionId(p2.getAiSkill(round));	
		}
		
		int actionidp1 = p1.getActiveActionId();
		int actionidp2 = p2.getActiveActionId();
		
		Action actionp1 = p1.getActiveAction();
		Action actionp2 = p2.getActiveAction();
		
		if (!areActionsLegal(actionp1, actionp2)) return;
		
		setAnimStateP1();
		pausetime = PAUSE_TIME;
		
		
		// now we know everything is ok, so start actually doing stuff
		
		ui.logToConsole("Round" + round++ + ", " + "p1 uses: " + actionidp1 + ": " + a1name +
				", p2 uses: " + actionidp2 + ": " + a2name + ". Fight!");
		
		// each player uses their skill, this won't do actual damage, but record how much dmg they want to do this round
		
		if (actionp1 != null) actionp1.act(p1, p2);
		if (actionp2 != null) actionp2.act(p2, p1);
		
		p1.updateCooldowns();p2.updateCooldowns();
		// actually applies the damage done this round by all players
		p1.applyDmg(); p2.applyDmg();
		p1.applyBuffs(); p2.applyBuffs(); 

//		endFight();
		
		updateUi();
	}

	private boolean areActionsLegal(Action actionp1, Action actionp2) {
		a1name = "null";
		a2name = "null";
		if (actionp1 != null) {
			a1name = actionp1.getName();
			if (!actionp1.isLegal()) {
				ui.logToConsole("Cannot go because p1 action, " + a1name + " is illegal");
				return false;
			} 
		}
		if (actionp2 != null) {
			a2name = actionp2.getName();
			if (!actionp2.isLegal()) {
				ui.logToConsole("Cannot go because p2 action, " + a2name + " is illegal");
				return false;
			}
		}
		return true;
	}

	private void endFight() {
		if (isFightOver()) {
			fightstate = "paused";
			Char winner = null;
			if (p1.getHp() == 0) {
				if (p2.getHp() > 0) {
					winner = p2;
				}
			}
			if (p2.getHp() == 0) {
				if (p1.getHp() > 0) {
					winner = p1;
				}
			}
			
			fightOver(winner);
		}
	}

	private boolean isFightOver() {
		return p1.getHp() == 0 || p2.getHp() == 0;
	}

	private void fightOver(Char winner) {
		if (winner == null) ui.logToConsole("Fight over. Draw!");
		else ui.logToConsole("Fight over. " + winner.getName() + " wins.");
		
		if (nextLevel != null) {
			LevelState levelState = game.getGameState().getLevel();
			if (winner == p1) {
				game.getGameState().incCurenemy();
				levelState.setFightState(WIN);
			}
			else {
				levelState.setFightState(LOSE);
			}
			
			LevelScreen clone = null;
			try {
				Constructor<? extends LevelScreen> constructor = nextLevel.getConstructor(GdxTest02.class);
				clone = (LevelScreen)constructor.newInstance(game);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			game.setScreen(clone);
			this.dispose();
		}
	}

	/**do AI stuff here
	 * @return
	 */
//	private int getAiSkill() {
//		return 1;
//	}

	/**Update UI elements
	 * 
	 */
	private void updateUi() {
		logPlayerStats(p1);
		logPlayerStats(p2);
		updateButtonsText();
		ui.resetActiveActions();
	}

	/**Update buttons' text
	 * 
	 */
	private void updateButtonsText() {
		for (int player = 1; player <= 2; player++) {
			for (int action = 1; action <= 4; action++) {
				ui.updateActionText(player, action);
			}
		}
	}


	private void logPlayerStats(Char c) {
		ui.logToConsole("Player " + c.getName() + ": " + c.getHp() + "/" + c.getMaxhp() + "hp. Buffs: " + c.printBuffs());
	}

	/**Logs text to Gdx.app.log()
	 * @param text
	 */
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

	@Override
	public void resize(int width, int height) {
		ui.resize(width, height);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
//		p1.dispose();
//		p2.dispose();
	}

	public Char getP1() {
		return p1;
	}
	
	public Char getP2() {
		return p2;
	}


	/**Returns the Char object for player 1 or 2
	 * @param gameScreenUI TODO
	 * @param player 1 or 2
	 * @return Char p1 or p2
	 */
	public Char getPlayer(int player) {
		if (player == 1) return p1;
		if (player == 2) return p2;
		return null;
	}


}