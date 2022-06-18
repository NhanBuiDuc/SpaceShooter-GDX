package hcmute.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;

public class SpaceShooterGame extends Game {
	public static Random random = new Random();
	public SpaceShooterGame() {
	}

	@Override
	public void create () {
		setScreen(new VictoryScreen());
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
