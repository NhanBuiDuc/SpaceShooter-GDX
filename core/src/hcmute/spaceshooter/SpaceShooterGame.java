package hcmute.spaceshooter;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class SpaceShooterGame extends Game {

	//GameScreen1 gameScreen1;

	public static Random random = new Random();
	public SpaceShooterGame() {

	}

	@Override
	public void create () {
		//gameScreen1 = new GameScreen1();
		//setScreen(gameScreen1);
		setScreen(new SplashScreen());
	}

	@Override
	public void resize(int width, int height) {
		//gameScreen1.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		//gameScreen1.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
