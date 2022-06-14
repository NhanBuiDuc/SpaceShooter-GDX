package hcmute.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.w3c.dom.Text;

public class GlobalVariables {
    // world parameters

    // The Width of the screen
    public static final int WORLD_WIDTH = 72;
    // The Height of the screen
    public static final int WORLD_HEIGHT = 128;

    public static final Texture fireLaserTexture = new Texture("hitting_effect.png");
    public static final Texture blueLaserTexture = new Texture("blue_fireball.png");
    public static final Texture purpleLaserTexture = new Texture("theVoid_fireball.png");
    public static final Texture explosionTexture = new Texture("hitting_effect.png");
    public static final Texture magicCircleTexture = new Texture("shuriken.png");
    public static final Texture meteorTexture = new Texture("meteor.png");
    public static final Texture redPowerUpTexture = new Texture("powerupRed_bolt.png");
    public static final Texture bluePowerUpTexture = new Texture("powerupGreen_bolt.png");
    public static final Texture explosiveDrone = new Texture("explosive_drone.png");
    public static final TextureRegion finalDroneExplosionTextureRegion = GetFinalExplosionAnimation(explosiveDrone);
    public static TextureRegion GetFinalExplosionAnimation(Texture texture) {
        TextureRegion textureRegion = new TextureRegion();
        // split texture
        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, 64, 64);

        textureRegion = textureRegion2D[4][2];
        return textureRegion;
    }
    /* Loads images from texture atlases created by TexturePacker.
    A TextureAtlas must be disposed to free up the resources consumed by the backing textures.*/
    public static final TextureAtlas textureAtlas = new TextureAtlas("images.atlas");
    public static final Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Sprites_bg_music.ogg"));
    public static final Music bossMusic = Gdx.audio.newMusic(Gdx.files.internal("Sprites_boss_music.ogg"));
    public static final Music explosionEffect = Gdx.audio.newMusic(Gdx.files.internal("Sprites_explosion.wav"));
    public static final Music laserEffect = Gdx.audio.newMusic(Gdx.files.internal("laser_effect1.mp3"));
}
