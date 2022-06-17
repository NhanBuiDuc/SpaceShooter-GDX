package hcmute.spaceshooter;

import com.badlogic.gdx.Game;

import java.util.Random;

public class SpaceShooterGame extends Game {

	GameScreen1 gameScreen1;
	public static Random random = new Random();
	public SpaceShooterGame() {
	}

	@Override
	public void create () {
		gameScreen1 = new GameScreen1();
		setScreen(gameScreen1);
	}

	@Override
	public void resize(int width, int height) {
		gameScreen1.resize(width, height);
	}

	@Override
	public void dispose() {
		super.dispose();
		gameScreen1.dispose();
	}

	@Override
	public void render() {
		super.render();
	}
}
