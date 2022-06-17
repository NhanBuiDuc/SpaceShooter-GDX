package hcmute.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SpaceShooterGame extends Game {

	// The Width of the screen
	public static int WORLD_WIDTH = 72;
	// The Height of the screen
	public static int WORLD_HEIGHT = 128;
	public static Random random = new Random();
	public SpaceShooterGame() {
	}

	@Override
	public void create () {
		setScreen(new MenuScreen());
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
