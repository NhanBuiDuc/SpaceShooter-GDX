package hcmute.spaceshooter.Animation;

import static hcmute.spaceshooter.GlobalVariables.backgroundMusic;
import static hcmute.spaceshooter.GlobalVariables.explosionEffect;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.Text;

public class Explosion {
    private Animation<TextureRegion> explosionAnimation;
    private float explosionTimer;
    private Rectangle boundingBox;

    public Explosion(Texture texture, Rectangle boundingBox, float totalAnimationTime){
        this.boundingBox = boundingBox;
        // split texture
        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, 256, 256);

        // convert to 1D array
        TextureRegion[] textureRegion1D = new TextureRegion[48];
        int index = 0;
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 8; j++){
                textureRegion1D[index] = textureRegion2D[i][j];
                index++;
            }
        }
        explosionAnimation = new Animation<TextureRegion>(totalAnimationTime / 48, textureRegion1D);
        explosionTimer = 0;
    }
    public void update(float deltaTime){
        explosionTimer += deltaTime;
    }

    public void draw(Batch batch){

        batch.draw(explosionAnimation.getKeyFrame(explosionTimer),
                boundingBox.x, boundingBox.y, boundingBox.width * 2, boundingBox.height * 2);
    }

    public boolean isFinished(){
        return explosionAnimation.isAnimationFinished(explosionTimer);
    }

    public Animation<TextureRegion> getExplosionAnimation() {
        return explosionAnimation;
    }

    public void setExplosionAnimation(Animation<TextureRegion> explosionAnimation) {
        this.explosionAnimation = explosionAnimation;
    }

    public float getExplosionTimer() {
        return explosionTimer;
    }

    public void setExplosionTimer(float explosionTimer) {
        this.explosionTimer = explosionTimer;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
}
