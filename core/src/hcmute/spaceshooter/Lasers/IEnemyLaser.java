package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

/**
 *  The interface for the enemies' laser
 */
public interface IEnemyLaser{
    /**
     * Draw the laser animation
     *
     * @param batch Draws batched quads using indices.
     */
    void drawLaser(Batch batch);

    /** Set the level of the laser
     *
     * @param i the level
     */
    void setLevel(int i);

    /** Return the array of the type of laser
     *
     * @return array of the type IEnemylaser
     */
    IEnemyLaser [] GetBullets();

    /**
     *
     * @return the type of the laser
     */
    String getTypeName();

    /**
     *
     * @return the laser rectangle
     */
    Rectangle getLaserBoundingBox();

    /**
     *
     * return laser's movement speed
     */
    float getLaserMovementSpeed();

    /**
     *
     * @return laser's movement type
     */
    String getMovementType();

    /** Set laser movement speed
     *
     * @param v the laser movement speed
     */
    void setLaserMovementSpeed(float v);

    /**
     *
     * @return get the level of the laser
     */
    int getLevel();

    /**
     *
     * @return the damage of the laser
     */
    int getDamage();

    /**
     *
     * @return true if the animation is finished, false if not finished
     */
    boolean isFinished();

    /** Draw the laser with animaiton
     *
     * @param deltaTime The time in seconds since the last render.
     * @param batch Draws batched quads using indices.
     */
    void drawLasersWithAnimation(float deltaTime, Batch batch);

    /**
     *
     * @return true if the laser is in spreading state, false if not.
     */
    boolean isSpreading();
}
