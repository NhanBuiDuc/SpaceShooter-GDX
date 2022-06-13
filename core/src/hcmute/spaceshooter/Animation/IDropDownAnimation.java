package hcmute.spaceshooter.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public interface IDropDownAnimation {

    /** Update method increasing timer by deltaTime
     *
     * @param deltaTime The time in seconds since the last render.
     */
    void update(float deltaTime);

    /** Draw the animation
     *
     * @param batch Draws batched quads using indices.
     */
    void draw(Batch batch);

    /** Whether the animation would be finished if played without looping, given the state time.*/
    boolean isFinished();

    /**
     * Split texture to 1D Texture Region Array and return the Animation
     */
    Animation<TextureRegion> GetAnimation(Texture texture);

    Rectangle getDrawingRectangle();

    String getTypeName();

    void setTaken(Boolean taken);
}
