package com.gdxtest02;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**This is supposed to be ageneric ui class that is suppused to be good for every screen
 * Screen specific classes could derive from it
 * 
 * @author vib
 *
 */
public class UIBuilder {
	protected Skin skin;
	protected Stage stage;
	protected GdxTest02 game;
	
	public Skin getSkin() {
		return skin;
	}

	public Stage getStage() {
		return stage;
	}

	public UIBuilder(final GdxTest02 game) {
		this.game = game;
//		stage = new Stage(new ScreenViewport());
		stage = new Stage(new StretchViewport(GdxTest02.VIRTUAL_WIDTH, GdxTest02.VIRTUAL_HEIGHT));
		Gdx.input.setInputProcessor(stage);
//		stage.setViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, false);

		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		BitmapFont f = new BitmapFont();
		f.setScale(2);
		skin.add("default", f);
		
		BitmapFont f2 = new BitmapFont();
//		f2.setScale(2);
		skin.add("small", f2);

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		
		ListStyle listStyle = new ListStyle();
		listStyle.font = skin.getFont("small");
		listStyle.selection = skin.newDrawable("white", Color.DARK_GRAY);
//		listStyle.fontColorSelected = skin.newDrawable("white", Color.DARK_GRAY);
//		  skin.newDrawable("white", Color.DARK_GRAY);
		skin.add("default", listStyle);
		
		ScrollPaneStyle scrollStyle = new ScrollPaneStyle();
		scrollStyle.background = skin.newDrawable("white", Color.DARK_GRAY);
//		.font = skin.getFont("default");
//		scrollStyle.selection = skin.newDrawable("white", Color.DARK_GRAY);
//		listStyle.fontColorSelected = skin.newDrawable("white", Color.DARK_GRAY);
//		  skin.newDrawable("white", Color.DARK_GRAY);
		skin.add("default", scrollStyle);
		
		ImageButtonStyle imagebuttonStyle = new ImageButtonStyle();
//		imagebuttonStyle.
		skin.add("default", imagebuttonStyle);
		
		LabelStyle labelStyle = new LabelStyle();
		labelStyle.font = skin.getFont("default");
		skin.add("default", labelStyle);

		

		// Add an image actor. Have to set the size, else it would be the size of the drawable (which is the 1x1 texture).
//		table.add(new Image(skin.newDrawable("white", Color.RED))).size(64);
	}

	protected void dispose() {
		stage.dispose();
		skin.dispose();
	}

	public void resize(int width, int height) {
//		stage.setViewport(game.VIRTUAL_WIDTH, game.VIRTUAL_HEIGHT, false);
		stage.getViewport().update(width, height, true);
//		stage.getViewport().update(GdxTest02.VIRTUAL_WIDTH, GdxTest02.VIRTUAL_HEIGHT, true);
	}

	public void draw() {
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}
}
