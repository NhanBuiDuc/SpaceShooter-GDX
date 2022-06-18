package hcmute.spaceshooter.Animation;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.Text;

/**
 *  The concrete class for the explosion animation objects
 */
public class Explosion {
    private Animation<TextureRegion> explosionAnimation;
    private float explosionTimer;
    private Rectangle boundingBox;

    public Explosion(Texture texture, Rectangle boundingBox, float totalAnimationTime){
        this.boundingBox = boundingBox;
        // split texture
        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, 32, 32);

        // convert to 1D array
        TextureRegion[] textureRegion1D = new TextureRegion[8];
        int index = 0;
        for(int i = 0; i < 1; i++){
            for(int j = 0; j < 8; j++){
                textureRegion1D[index] = textureRegion2D[i][j];
                index++;
            }
        }
        explosionAnimation = new Animation<TextureRegion>(totalAnimationTime / 8, textureRegion1D);
        explosionTimer = 0;
    }
    /** Update the animation's rendering time by @param delaTime
     *
     * @param deltaTime The time in seconds since the last render.
     */
    public void update(float deltaTime){
        explosionTimer += deltaTime;
    }

    /**
     * Draw the animation
     *
     * @param batch Draws batched quads using indices.
     */
    public void draw(Batch batch){

        batch.draw(explosionAnimation.getKeyFrame(explosionTimer),
                boundingBox.x, boundingBox.y, boundingBox.width * 1.25f, boundingBox.height * 1.25f);
    }
    /**
     * Whether the animation would be finished if played without looping, given the state time.
     */
    public boolean isFinished(){
        return explosionAnimation.isAnimationFinished(explosionTimer);
    }

    //region Getter and Setter
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
    //endregion
}
