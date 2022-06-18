package hcmute.spaceshooter.Lasers;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.boss1_LaserTypeA_Texture;
import static hcmute.spaceshooter.GlobalVariables.boss2_LaserTypeA_Texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

/**
 *  The concrete class for the enemy boss laser
 */
public class Boss1_LaserTypeA extends EnemyLaser {
    // level of the bullet
    int level = 1;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;

    // Constructor
    public Boss1_LaserTypeA(Rectangle shipBoundingBox) {
        // the ship's drawing rectangle
        this.shipBoundingBox = shipBoundingBox;
        // the laser's texture
        laserTexture = boss1_LaserTypeA_Texture;
        // the laser width
        laserWidth = 20f;
        // the laser height
        laserHeight = WORLD_HEIGHT;
        // the laser's type name
        typeName = "Boss1_TypeA";
        // laser movement speed
        laserMovementSpeed = 50;
        /**
         * The title width and height of the given Texture
         * starting from the top left corner going to the right
         * and ending at the bottom right corner.
         */
        laserTitleWidth = 32;
        laserTitleHeight = 138;
        // Total time of the whole animation rendering.
        totalLaserAnimationTime = 1f;
        // number of row of image in the texture
        laserRowTextureCount = 1;
        // number of column of image in the texture
        laserColumnTextureCount = 16;
        // the timer to track the animation time
        laserTimer = 0;
        /**
         *  The number of texture region after splitting the texture,
         *  equals to the number of images from the whole Texture
         */
        laserTextureNum = laserRowTextureCount * laserColumnTextureCount;
        // Gdx's Animation object
        laserAnimation = GetLaserAnimation(laserTexture, laserTitleWidth, laserTitleHeight,
                laserTextureNum, laserRowTextureCount, laserColumnTextureCount);
    }

    /**
     *
     * @return true if the animation is finished, false if not.
     */
    public boolean isFinished() {
        return laserAnimation.isAnimationFinished(laserTimer);
    }

    /**
     * Upgrade the level of the laser
     */
    public void Upgrade(){
        this.level++;
    }

    /**
     *
     * @return the array of this type
     */
    public Boss1_LaserTypeA[] GetBullets(){
        Boss1_LaserTypeA[] lasers = new Boss1_LaserTypeA[20];

        lasers[0] = new Boss1_LaserTypeA(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(45);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().width * 0.25f,
                lasers[0].getShipBoundingBox().getY() - lasers[0].getShipBoundingBox().height * 3f,
                lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));
        return lasers;
    }

    /** draw the animation of the lasers
     * @param deltaTime The time in seconds since the last render.
     */
    public void drawLasersWithAnimation(float deltaTime, Batch batch){
        if(isFinished() == false){
            update(deltaTime);
            drawLaser(batch);
        }
    }

    /**
     *
     * @return true if it is spreading, false if it is not
     */
    @Override
    public boolean isSpreading() {
        return false;
    }

    /** Update the animation's rendering time by @param delaTime
     *
     * @param deltaTime The time in seconds since the last render.
     */
    public void update(float deltaTime) {
        laserTimer += deltaTime;
    }

    /**
     * Draw the laser animation
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void drawLaser(Batch batch) {
        super.drawLaser(batch);
    }

    //region Getter and Setter
    @Override
    public String getMovementType() {
        return movementType;
    }
    public int getLevel() {
        return level;
    }

    @Override
    public int getDamage() {
        return 1;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public Rectangle getShipBoundingBox() {
        return shipBoundingBox;
    }

    public void setShipBoundingBox(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
    }

    public Texture getLaserTexture() {
        return laserTexture;
    }

    public void setLaserTexture(Texture laserTexture) {
        this.laserTexture = laserTexture;
    }

    //endregion Getter and Setter
}
