package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public interface ILaser {
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
    ILaser[] GetBullets();

    /**
     *
     * @param typeName set the type name of the laser
     */
    void setTypename(String typeName);
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

    /** Push the laser upward while rendering animation and check if it is hiting the top of the screen
     *
     * @param deltaTime The time in seconds since the last render.
     * @param batch Draws batched quads using indices.
     */
    void pushLaserUpward(float deltaTime,Batch batch);

}
