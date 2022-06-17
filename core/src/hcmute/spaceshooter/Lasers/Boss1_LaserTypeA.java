package hcmute.spaceshooter.Lasers;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.boss1_LaserTypeA_Texture;
import static hcmute.spaceshooter.GlobalVariables.boss2_LaserTypeA_Texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Boss1_LaserTypeA extends EnemyLaser {

    int level = 1;
    Rectangle shipBoundingBox;
    public Boss1_LaserTypeA(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = boss1_LaserTypeA_Texture;
        laserWidth = 20f;
        laserHeight = WORLD_HEIGHT;
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
        laserRowTextureCount = 1;
        laserColumnTextureCount = 8;
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

    public boolean isFinished() {
        return laserAnimation.isAnimationFinished(laserTimer);
    }

    public void Upgrade(){
        this.level++;
    }


    public Boss1_LaserTypeA[] GetBullets(){
        Boss1_LaserTypeA[] lasers = new Boss1_LaserTypeA[20];

        lasers[0] = new Boss1_LaserTypeA(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(45);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().width * 0.36f,
                lasers[0].getShipBoundingBox().getY() - lasers[0].getShipBoundingBox().height * 3f,
                lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


//        lasers[1] = new Boss1_LaserTypeA(shipBoundingBox);
//        lasers[1].setLaserWidth(laserWidth);
//        lasers[1].setLaserHeight(laserHeight);
//        lasers[1].setLaserMovementSpeed(45);
//        lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + 40f,
//                lasers[1].getShipBoundingBox().getY() - 100f,
//                lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));

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


    @Override
    public void setTypename(String red) {

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

    @Override
    public String getMovementType() {
        return movementType;
    }

    //region Getter and Setter

    public int getLevel() {
        return level;
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
