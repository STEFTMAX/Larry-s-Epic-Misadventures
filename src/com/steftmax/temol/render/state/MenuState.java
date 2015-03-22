package com.steftmax.temol.render.state;

import java.io.IOException;

import org.lwjgl.opengl.Display;

import com.steftmax.temol.Game;
import com.steftmax.temol.audio.Music;
import com.steftmax.temol.audio.OpenALSystem;
import com.steftmax.temol.audio.Sound;
import com.steftmax.temol.graphics.Camera;
import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.StaticCamera;
import com.steftmax.temol.graphics.sprite.Sprite;
import com.steftmax.temol.graphics.sprite.Texture;
import com.steftmax.temol.graphics.sprite.TextureRegion;
import com.steftmax.temol.math.Vector2;
import com.steftmax.temol.render.input.MouseInput;
import com.steftmax.temol.resource.MenuResources;

/**
 * @author pieter3457
 *
 */
public class MenuState extends State implements Button.Listener {

	private final Button play, settings;
	private final MenuResources resources = new MenuResources();
	private Camera cam;
	private Sprite background;
	Music music;
	Sound sound;

	private enum Screen {
		MENU, SETTINGS;
	}

	private Screen screen = Screen.MENU;
	private boolean switchToPlay, switchToMapEditor;

	private final static int PLAYBUTTON_X = 240, PLAYBUTTON_Y = 80,
			SETTINGSBUTTON_X = 340, SETTINGSBUTTON_Y = 80;
	OpenALSystem system;
	public MenuState(Game game) {
		super(game);

		final MouseInput mi = game.getMouseInput();

		mi.unGrab();

		cam = new StaticCamera(2f, 0, 0);
		mi.setCamera(cam);

		resources.load();
		
		system = new OpenALSystem();
		music = new Music("music/menu.ogg");
		system.setMusic(music);
		music.setLooping(true);
		try {
			sound = new Sound(system, "sfx/handgunreload1.wav");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(new Vector2(1f,0f).dotProduct(new Vector2(1f,1f)));

		// font = new BitmapFont("1234567890.,!?;:ABCDEFGHIJKLMNOPQRSTUVWXYZ",
		// resources.getSpriteSheet("font/font1.png").getFrames(),
		// new Color(.5F, .7F, .3F, 1F));

		background = new Sprite(resources.getTexture("gfx/menu.png"), 0, 0);

		final Texture sheet = resources.getTexture("gfx/sheet_buttons.png");
		final int width = 256, height = 64;

		play = new Button(this, mi, PLAYBUTTON_X, PLAYBUTTON_Y,
				new TextureRegion(sheet, 0, 2 * height, width, height),
				new TextureRegion(sheet, 0, height, width, height),
				new TextureRegion(sheet, 0, 0, width, height));

		settings = new Button(this, mi, SETTINGSBUTTON_X, SETTINGSBUTTON_Y,
				new TextureRegion(sheet, width, 2 * height, width, height),
				new TextureRegion(sheet, width, height, width, height),
				new TextureRegion(sheet, width, 0, width, height));

		play.setDimensions(64, 16);
		settings.setDimensions(64, 16);
		
		Display.setVSyncEnabled(true);
	}

	long time;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.update.Updatable#update(long)
	 */
	@Override
	public void update(long delta) {
		if (switchToPlay) {
			game.changeState(this, GameState.class);
		}
		if (switchToMapEditor) {
			game.changeState(this, MapEditorState.class);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.draw.Drawable#draw(com.steftmax
	 * .larrys_epic_misadventures.draw.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {

		cam.beginFocus();
		batch.begin();

		batch.draw(background);
		switch (screen) {
		case MENU:
			play.draw(batch);
			settings.draw(batch);
			break;
		case SETTINGS:
			break;

		}

		batch.end();
		cam.endFocus();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.larrys_epic_misadventures.state.State#deleteResources()
	 */
	@Override
	public void deleteResources() {
		resources.unload();
		system.dispose();
	}
	
	@Override
	public void onPress(Button b) {}
	@Override
	public void onRelease(Button b) {}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.steftmax.larrys_epic_misadventures.render.state.Button.Listener#onPressed
	 * (com.steftmax.larrys_epic_misadventures.render.state.Button)
	 */
	@Override
	public void onPressed(Button b) {

		if (b == play) {
			switchToPlay = true;
		}

		if (b == settings) {
			switchToMapEditor = true;
		}

	}

}
