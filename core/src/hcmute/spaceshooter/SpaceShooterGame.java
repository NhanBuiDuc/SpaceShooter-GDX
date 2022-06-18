package hcmute.spaceshooter;

import com.badlogic.gdx.Game;

import java.util.Random;

/**
 * The main game entry
 */
public class SpaceShooterGame extends Game {
	public ResourceManager rm;
	public static Random random = new Random();
	public SpaceShooterGame() {
	}
	@Override
	public void create () {
		rm=new ResourceManager();
		//load campaigns
		rm.loadCampaigns();
		this.setScreen(new SplashScreen());
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
