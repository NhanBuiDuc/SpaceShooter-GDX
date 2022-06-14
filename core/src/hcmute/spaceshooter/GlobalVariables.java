package hcmute.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GlobalVariables {
    // world parameters

    // The Width of the screen
    public static final int WORLD_WIDTH = 72;
    // The Height of the screen
    public static final int WORLD_HEIGHT = 128;

    /* Backgrounds Texture Array . Each item defines a rectangular area of a texture.
    The coordinate system used has its origin in the upper left corner with the x-axis pointing to the right and the y axis pointing downwards.*/
    public static final Texture background = new Texture("Space Background.png");
    public static final Texture redLaserBulletTexture = new Texture("bullet_red_laser.png");
    public static final Texture blueBulletTexture = new Texture("bullet_blue.png");
    public static final Texture yellowBulletTexture = new Texture("bullet_yellow.png");
    public static final Texture archBulletTexture = new Texture("bullet_arch.png");
    public static final Texture greenBulletTexture = new Texture("bullet_green.png");
    public static final Texture explosionTexture = new Texture("hitting_effect.png");
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
}
