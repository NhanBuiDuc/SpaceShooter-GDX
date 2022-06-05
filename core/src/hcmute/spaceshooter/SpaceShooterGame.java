package hcmute.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SpaceShooterGame extends Game {

	GameScreen gameScreen;
	public static Random random = new Random();
	public SpaceShooterGame() {
	}

	@Override
	public void create () {
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void resize(int width, int height) {
		gameScreen.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		gameScreen.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
