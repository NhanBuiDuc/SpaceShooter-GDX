package hcmute.spaceshooter.Lasers;


import static hcmute.spaceshooter.GlobalVariables.boss1_LaserTypeB_Texture;
import static hcmute.spaceshooter.GlobalVariables.boss2_LaserTypeB_Texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *  The concrete class for the enemy boss laser
 */
public class Boss1_LaserTypeB extends EnemyLaser {
    // The array of this type's bullet
    Boss1_LaserTypeB[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;
    // Movement speed of the object
    public int movementSpeed;
    // Total time of the whole animation rendering.
    public float totalAnimationTime;
    // number of rows and columns of images in the texture
    public int rowTextureCount;
    public int columnTextureCount;
    /**
     * The title width and height of the given Texture
     * starting from the top left corner going to the right
     * and ending at the bottom right corner.
     */
    public int titleWidth;
    public int titleHeight;

    // A timer increased with each update method call
    public float timer = 0;

    // Constructor
    public Boss1_LaserTypeB(Rectangle shipBoundingBox) {
        // the ship's drawing rectangle
        this.shipBoundingBox = shipBoundingBox;
        // the laser's texture
        laserTexture = boss1_LaserTypeB_Texture;
        // the laser width
        laserWidth = 10f;
        // the laser height
        laserHeight = 10f;
        // laser movement speed
        movementSpeed = 30;
        // Total time of the whole animation rendering.
        totalAnimationTime = 0.5f;
        // the laser's type name
        typeName = "Boss1_TypeB";
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
    public Boss1_LaserTypeB[] GetBullets() {
        Boss1_LaserTypeB[] lasers = new Boss1_LaserTypeB[2];
        if(level == 1){

            lasers[0] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.04f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().height * 0.1f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(45);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.7f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().height * 0.1f,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));
            this.bullets = lasers;
        }
        else if (level == 2) {

            lasers[0] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[0].getShipBoundingBox().getY(),
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(45);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[1].getShipBoundingBox().getY(),
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));
            this.bullets = lasers;
        }
        return lasers;
    }

    /** Draw the laser with static animation
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void drawLaser(Batch batch) {
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
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

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isSpreading() {
        return false;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public Boss1_LaserTypeB[] getBullets() {
        return bullets;
    }

    public void setBullets(Boss1_LaserTypeB[] bullets) {
        this.bullets = bullets;
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
