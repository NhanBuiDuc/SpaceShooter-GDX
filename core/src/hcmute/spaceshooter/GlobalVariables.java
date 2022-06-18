package hcmute.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import org.w3c.dom.Text;

public class GlobalVariables {
    // world parameters

    // The Width of the screen
    public static final int WORLD_WIDTH = 72;
    // The Height of the screen
    public static final int WORLD_HEIGHT = 128;

    /* Loads images from texture atlases created by TexturePacker.
    A TextureAtlas must be disposed to free up the resources consumed by the backing textures.*/
    public static final TextureAtlas textureAtlas = new TextureAtlas("images.atlas");
    /* Backgrounds Texture Array . Each item defines a rectangular area of a texture.
    The coordinate system used has its origin in the upper left corner with the x-axis pointing to the right and the y axis pointing downwards.*/
    public static final Texture background1 = new Texture("Space Background.png");
    public static final Texture background2 = new Texture("Space Background01.png");
    public static final Texture background3 = new Texture("Space Background02.png");
    public static final Texture orangeLaserBulletTexture = new Texture("bullet_orange_laser.png");
    public static final Texture blueBulletTexture = new Texture("bullet_blue.png");
    public static final Texture yellowBulletTexture = new Texture("bullet_yellow.png");
    public static final Texture archBulletTexture = new Texture("bullet_arch.png");
    public static final Texture greenBulletTexture = new Texture("bullet_green.png");
    public static final Texture explosionTexture = new Texture("hitting_effect.png");
    public static final Texture meteorTexture = new Texture("meteor.png");
    public static final Texture orangePowerUpTexture = new Texture("powerupOrange_bolt.png");
    public static final Texture bluePowerUpTexture = new Texture("powerupBlue_bolt.png");
    public static final Texture yellowPowerUpTexture = new Texture("powerupYellow_bolt.png");
    public static final Texture archPowerUpTexture = new Texture("powerupArch_bolt.png");
    public static final Texture greenPowerUpTexture = new Texture("powerupGreen_bolt.png");
    public static final Texture explosiveDrone = new Texture("explosive_drone_sprite.png");
    //Boss1 laser types
    public static final Texture boss1_LaserTypeA_Texture = new Texture("boss01_bullet01_sprite.png");
    public static final Texture boss1_LaserTypeB_Texture = new Texture("boss01_bullet02.png");
    public static final Texture boss1_LaserTypeC_Texture = new Texture("boss01_bullet03.png");
    //Boss2 laser types
    public static final Texture boss2_LaserTypeA_Texture = new Texture("boss02_bullet01_sprite.png");
    public static final Texture boss2_LaserTypeB_Texture = new Texture("boss02_bullet02.png");
    public static final Texture boss2_LaserTypeC_Texture = new Texture("boss02_bullet03.png");
    //Boss2 laser types
    public static final Texture boss3_LaserTypeA_Texture = new Texture("boss03_bullet01_sprite.png");
    public static final Texture boss3_LaserTypeB_Texture = new Texture("boss03_bullet02.png");
    public static final Texture boss3_LaserTypeC_Texture = new Texture("boss03_bullet03.png");

    public static final Texture boss_Warning_Texture = new Texture("boss_warning.png");

    public static final TextureRegion finalDroneExplosionTextureRegion = GetFinalExplosionAnimation(explosiveDrone);
    public static TextureRegion GetFinalExplosionAnimation(Texture texture) {
        TextureRegion textureRegion = new TextureRegion();
        // split texture
        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, 64, 64);

        textureRegion = textureRegion2D[4][2];
        return textureRegion;
    }
    static final int GAME_RUNNING = 1;
    static final int GAME_PAUSED = 2;
    //Create pause menu
    public static Texture pauseMenuTexture = new Texture("pause_menu.png");
    //Create pause button
    public static Texture pauseButtonTexture = new Texture("pause_button.png");
    //public static final TextureRegion pauseButton = new TextureRegion(pauseTexture, 6, 6, 21, 22);
}
