package com.gdxtest02;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Values;
import com.gdxtest02.chars.TestChar01;
import com.gdxtest02.chars.TestChar02;
import com.gdxtest02.chars.TestChar03;
import com.gdxtest02.chars.TestChar04;
import com.gdxtest02.chars.TestChar05;
import com.gdxtest02.chars.TestChar06;
import com.gdxtest02.chars.TestChar07;
import com.gdxtest02.gamestate.GameState;
import com.gdxtest02.levels.Level02;
import com.gdxtest02.util.CharActor;
import com.gdxtest02.util.LinkedListener;
import com.gdxtest02.util.Util;

import static com.gdxtest02.CharBuilder.*;
import static com.gdxtest02.gamestate.GameState.*;

public class CharSelectScreenUI {

	private static final float TITLE_Y = 200;
	private static final float CHAR_Y = 50;
	private static final int CHARS_PER_ROW = 4;
	private static final int CHARBUTTON_WIDTH = 50;
	private static final int AIBUTTON_WIDTH = 50;
	protected static final int CONTROL_AI = FightScreen.CONTROL_AI;
	protected static final int CONTROL_HUMAN = FightScreen.CONTROL_HUMAN;
	private static final float FACETABLE_X = 50;
	private static final  float FACETABLE_Y = 300;
	private static final  float FACETABLE_WIDTH = 150;
	private int p1control;
	private int p2control;
	
	UIBuilder uibuilder;
	private TextButton gobutton;
	private TextButton backbutton;
	private CharSelectScreen screen;
	
	private float MENUY = -150;
	
	private Char p1;
	private Char p2;
	private Stage stage;
	private Skin skin;
	private Table chartable;
	private ClickListener gobuttonlistener;
	private ClickListener backlistener;
	private TextButton p1button;
	private int turn;
	private Label titlelabel;
	private TextButton p2button;
	private ClickListener balancelistener;
	private TextButton p1aibutton;
	private TextButton p2aibutton;
	private ClickListener ailistener;
	private int gamemode;
	private GameState gstate;
	private Array<Char> charlist;
	private ClickListener editlistener;
	private CharActor charactorp1;
	private CharActor charactorp2;

	void setupUi(final CharSelectScreen screen) {
		uibuilder = new UIBuilder(screen.game);
		stage = uibuilder.getStage();
		skin = uibuilder.getSkin();
		this.screen = screen;
		turn = 1;
		
		gstate = GameState.getInstance();
		gamemode = gstate.getGameMode();

		charlist = new Array<Char>();
		charlist.addAll(gstate.getChars());
		if (Util.getDebugMode() && gamemode != MODE_STORY) {
			// if not on story mode, add test chars
			charlist.addAll(gstate.getTestCharsInventory());
		}
		
		p1 = charlist.get(0);
		p2 = charlist.random();
		
		createListeners();
		
		createTitle();
		createMenuTable();
		createCharTable();
		createLeftTable();
		if (gamemode != MODE_STORY) createRightTable();
		
		
		
		setControl("p1", CONTROL_HUMAN);
		setControl("p2", CONTROL_AI);
		
		
	}

	private void createTitle() {
		titlelabel = new Label("", skin);
		titlelabel.setY(TITLE_Y);
		titlelabel.setAlignment(Align.center, Align.center);
		titlelabel.setFillParent(true);
		stage.addActor(titlelabel);
		
		updateTitle();
	}

	private void updateTitle() {
		titlelabel.setText("Choose player " + turn);
	}

	private void createLeftTable() {
		Table lefttable = new Table();
		lefttable.setPosition(FACETABLE_X + FACETABLE_WIDTH/2, FACETABLE_Y);
		stage.addActor(lefttable);		
		
		p1button = new TextButton("p1", skin);
		p1button.setDisabled(true);
		lefttable.add(p1button).width(FACETABLE_WIDTH).height(FACETABLE_WIDTH*1.2f);
		
		charactorp1 = new CharActor(p1); 
		p1button.add(charactorp1);
		charactorp1.setSize(FACETABLE_WIDTH);
		
		if (gamemode != MODE_STORY) {
			p1aibutton = new TextButton("AI", skin); 
			p1aibutton.setName("p1");
			p1aibutton.addListener(ailistener);
			lefttable.row();
			lefttable.add(p1aibutton).width(AIBUTTON_WIDTH).height(AIBUTTON_WIDTH);
		}
		TextButton editbutton = new TextButton("edit", skin); 
		editbutton.setDisabled(true);
		editbutton.setName("p1");
		editbutton.addListener(editlistener);
		lefttable.row();
		lefttable.add(editbutton).width(AIBUTTON_WIDTH).height(AIBUTTON_WIDTH);
		
	}
	
	private void createRightTable() {
		Table righttable = new Table();
		righttable.setPosition(800 - FACETABLE_X - FACETABLE_WIDTH/2, FACETABLE_Y);
		stage.addActor(righttable);		
		
		p2button = new TextButton("p2", skin);
		p2button.setDisabled(true);
		righttable.add(p2button).width(FACETABLE_WIDTH).height(FACETABLE_WIDTH);
		
		charactorp2 = new CharActor(p2); 
		p2button.add(charactorp2);
		charactorp2.setSize(FACETABLE_WIDTH);
		charactorp2.flipX(true);
		
		p2aibutton = new TextButton("AI", skin); 
		p2aibutton.setName("p2");
		p2aibutton.addListener(ailistener);
		righttable.row();
		righttable.add(p2aibutton).width(AIBUTTON_WIDTH).height(AIBUTTON_WIDTH);
		
		TextButton editbutton = new TextButton("edit", skin); 
		editbutton.setDisabled(true);
		editbutton.setName("p2");
		editbutton.addListener(editlistener);
		righttable.row();
		righttable.add(editbutton).width(AIBUTTON_WIDTH).height(AIBUTTON_WIDTH);
	}

	private void createCharTable() {
		chartable = new Table();
		chartable.setFillParent(true);
		chartable.setY(CHAR_Y);
		stage.addActor(chartable);
		Array<Char> list;
//		list = unlockedchars.values().toArray();
		list = charlist;
		int i = 1;
		int n = 0;
		for (Char c : list) {
			if (i > CHARS_PER_ROW) {
				chartable.row();
				i = 1;
			}
			addCharButton(c, n);
			i++;n++;
		}
		
	}

	private void addCharButton(Char c, int id) {
		TextButton cbutton;
		if (c.getName() != null && c.getName().length() > 3) {
			cbutton = new TextButton(c.getName().substring(0, 3), skin);
		}
		else {
			cbutton = new TextButton(c.getName(), skin);
		}
		chartable.add(cbutton).width(CHARBUTTON_WIDTH).height(CHARBUTTON_WIDTH);
		cbutton.setName(""+id);
		cbutton.addListener(getCharListener().setOwner(cbutton));
		cbutton.setDisabled(true);
		CharActor charactor = new CharActor(c); 
		cbutton.add(charactor);
		charactor.setSize(CHARBUTTON_WIDTH*.8f);
	}

	private void createMenuTable() {
		Table menutable = new Table();
		menutable.setFillParent(true);
		stage.addActor(menutable);
	
		gobutton = new TextButton("Fight!", skin);
		menutable.add(gobutton).width(300).height(50);
		gobutton.addListener(gobuttonlistener);
		gobutton.setDisabled(true);
		menutable.row();
		
		TextButton balancebutton = new TextButton("Balance test", skin);
		menutable.add(balancebutton).width(300).height(50);
		balancebutton.addListener(balancelistener);
		balancebutton.setDisabled(true);
		menutable.row();
		
		backbutton = new TextButton("Back to Menu", skin);
		menutable.add(backbutton).width(300).height(50);
		menutable.setY(MENUY);
		backbutton.addListener(backlistener);
		backbutton.setDisabled(true);
		

	}

	private void createListeners() {
		gobuttonlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				if (gamemode == MODE_STORY) {
					screen.game.getGameState().setPlayer(getP1());
					screen.game.setScreen(new LevelSelectScreen(screen.game));
				}
				else {
					screen.game.setScreen(new FightScreen(screen.game, getP1(), getP2(), p1control, p2control));
				}
				screen.dispose();
			}
		};
		
		balancelistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				Char c = getCurrentPlayer();
				if (c != null) {
//					c.getBalance().testModel1();
//					c.getBalance().testModel2();
					c.getBalance().testModel3();
				}
			}
		};
		
		ailistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				Actor actor = event.getTarget(); // the label that was clicked
				TextButton b = (TextButton)actor.getParent(); // the button holding that label
				String name = b.getName();
//				log("clicked ai, name: " + name);
				toggleControl(name);
//				log("p1control: " + p1control + " p2control: " + p2control);
				
			}
		};
		
		editlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				Actor actor = event.getTarget(); // the label that was clicked
				TextButton b = (TextButton)actor.getParent(); // the button holding that label
				String name = b.getName();
				Char c = getPlayerByName(name);
				screen.game.setScreen(new CharEditScreen(screen.game, c));
			}
		};
		
		backlistener = new ClickListener() {
			public void clicked(InputEvent event, float x, float y)  {
				screen.game.setScreen(new MainMenuScreen(screen.game));
				screen.dispose();
			}
		};
	}
	
	public LinkedListener getCharListener() {
		LinkedListener linkedlistener = new LinkedListener() {
			
			public void clicked(InputEvent event, float x, float y)  {
				String name = owner.getName();
				log("clicked on: " + event.getTarget());
				Char c = getCharFromId(name);
				
				setCurrentPlayer(c);
				TextButton a = (TextButton) getCurrentFace();
				a.setText(name);
				getCurrentCharFace().setChar(c);
				toggleTurn();
				
				log("click char " + name + " : " + c.getFullDescription());
				
			}
		};
		return linkedlistener;
	}
	
	protected Char getPlayerByName(String name) {
		if (name.equals("p1")) return getP1();
		if (name.equals("p2")) return getP2();
		return null;
	}

	private Char getP2() {
		if (gamemode == MODE_STORY) return p2;
//		return Util.copy(p2);
		return p2.getClone();
//		return p2;
	}

	private Char getP1() {
		if (gamemode == MODE_STORY) return p1;
//		return Util.copy(p1);
		return p1.getClone();
//		return p1;
	}

	/**Gets the character with the coresponding id on the char array
	 * @param name
	 * @return
	 */
	protected Char getCharFromId(String name) {
		int id = Integer.parseInt(name);
		return charlist.get(id);
	}

	/**Sets the control of this player (ai or player)
	 * string must be p1 or p2
	 * 
	 * @param player
	 * @param control CONTROL_PLAYER or CONTROL_AI
	 */
	private void setControl(String player, int control) {
		if (player.equals("p1")) {
			p1control = control;
			TextButton b = p1aibutton;
			checkAiButton(b, control);
		}
		if (player.equals("p2")) {
			p2control = control;
			TextButton b = p2aibutton;
			checkAiButton(b, control);
		}
	}
	
	/**Toggles control between ai and player for this playername
	 * @param player
	 */
	private void toggleControl(String playername) {
		if (playername.equals("p1")) {
			if (p1control == CONTROL_HUMAN) setControl(playername, CONTROL_AI);
			else setControl(playername, CONTROL_HUMAN);
		}
		if (playername.equals("p2")) {
			if (p2control == CONTROL_HUMAN) setControl(playername, CONTROL_AI);
			else setControl(playername, CONTROL_HUMAN);
		}
	}
	
	/**Checks or uncheck the ai button if the control is AI or Player
	 * @param button
	 */
	private void checkAiButton(TextButton button, int control) {
		if (button == null) return;
		if (control == CONTROL_AI) {
			button.setChecked(true);
		}
		if (control == CONTROL_HUMAN) {
			button.setChecked(false);
		}
	}

	public void draw() {
		uibuilder.draw();
	}

	public void resize(int width, int height) {
		uibuilder.resize(width, height);
	}

	public void dispose() {
		uibuilder.dispose();
	}
	
	/**Returns the Char object for player 1 or 2
	 * @param gameScreenUI TODO
	 * @param player 1 or 2
	 * @return Char p1 or p2
	 */
	private Char getPlayer(int player) {
		if (player == 1) return getP1();
		if (player == 2) return getP2();
		return null;
	}
	
	private Char getCurrentPlayer() {
		return getPlayer(turn);
	}
	
	private void setPlayer(int player, Char c) {
		if (player == 1) p1 = c;
		if (player == 2) p2 = c;
	}
	private void setCurrentPlayer(Char c) {
		setPlayer(turn, c);
	}
	
	
	
	/**Get the UI widget that shows the face of the current player
	 * @return
	 */
	private Actor getCurrentFace() {
		if (turn == 1 || gamemode == MODE_STORY) return p1button;
		else return p2button;
	}
	
	private CharActor getCurrentCharFace() {
		if (turn == 1 || gamemode == MODE_STORY) return charactorp1;
		else return charactorp2;
	}
	
	/**Toggles between 1 and 2 for who is the current player to choose
	 * 
	 */
	private void toggleTurn() {
		if (turn == 1 && gamemode != MODE_STORY) turn = 2;
		else turn = 1; 
		updateTitle();
	}
	
	private void log(String text) {
		Gdx.app.log("gdxtest", text);
	}

}
 