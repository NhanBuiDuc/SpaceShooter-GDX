package hcmute.spaceshooter.Lasers;


import static hcmute.spaceshooter.GlobalVariables.boss2_LaserTypeC_Texture;
import static hcmute.spaceshooter.GlobalVariables.boss3_LaserTypeC_Texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Boss3_LaserTypeC extends EnemyLaser {
    // the array of bullets of this type
    Boss3_LaserTypeC[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;
    // Movement speed of the object
    public int movementSpeed;
    // Total time of the whole animation rendering.
    public float totalAnimationTime;
    // number of row of image in the texture
    public int rowTextureCount;
    // number of column of image in the texture
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
    // check if the bullet is spreading
    boolean isSpreading = false;

    // Constructor
    public Boss3_LaserTypeC(Rectangle shipBoundingBox) {
        // the ship's drawing rectangle
        this.shipBoundingBox = shipBoundingBox;
        // the laser's texture
        laserTexture = boss3_LaserTypeC_Texture;
        // the laser width
        laserWidth = 5f;
        // the laser height
        laserHeight = 5f;
        // laser movement speed
        movementSpeed = 30;
        // Total time of the whole animation rendering.
        totalAnimationTime = 0.5f;

        /**
         * The title width and height of the given Texture
         * starting from the top left corner going to the right
         * and ending at the bottom right corner.
         */
        laserTitleWidth = 32;
        laserTitleHeight = 138;
        // Total time of the whole animation rendering.
        totalLaserAnimationTime = 1f;
        // Total time of the whole animation rendering.
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
    public Boss3_LaserTypeC[] GetBullets() {
        Boss3_LaserTypeC[] lasers = new Boss3_LaserTypeC[7];
        if (level == 1) {

            lasers[0] = new Boss3_LaserTypeC(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(15);
            lasers[0].setMovementType("UP_LEFT");
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.4f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().height * 0.3f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new Boss3_LaserTypeC(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(15);
            lasers[1].setMovementType("UP_RIGHT");
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.4f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().height * 0.3f,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));

            lasers[2] = new Boss3_LaserTypeC(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(15);
            lasers[2].setMovementType("RIGHT");
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.4f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().height * 0.3f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));


            lasers[3] = new Boss3_LaserTypeC(shipBoundingBox);
            lasers[3].setLaserWidth(laserWidth);
            lasers[3].setLaserHeight(laserHeight);
            lasers[3].setLaserMovementSpeed(15);
            lasers[3].setMovementType("DOWN_RIGHT");
            lasers[3].setLaserBoundingBox(new Rectangle(lasers[3].getShipBoundingBox().getX() + lasers[3].getShipBoundingBox().getWidth() * 0.4f,
                    lasers[3].getShipBoundingBox().getY() + lasers[3].getShipBoundingBox().height * 0.3f,
                    lasers[3].getLaserWidth(), lasers[3].getLaserHeight()));

            lasers[4] = new Boss3_LaserTypeC(shipBoundingBox);
            lasers[4].setLaserWidth(laserWidth);
            lasers[4].setLaserHeight(laserHeight);
            lasers[4].setLaserMovementSpeed(15);
            lasers[4].setMovementType("DOWN_LEFT");
            lasers[4].setLaserBoundingBox(new Rectangle(lasers[4].getShipBoundingBox().getX() + lasers[4].getShipBoundingBox().getWidth() * 0.4f,
                    lasers[4].getShipBoundingBox().getY() + lasers[4].getShipBoundingBox().height * 0.3f,
                    lasers[4].getLaserWidth(), lasers[4].getLaserHeight()));

            lasers[5] = new Boss3_LaserTypeC(shipBoundingBox);
            lasers[5].setLaserWidth(laserWidth);
            lasers[5].setLaserHeight(laserHeight);
            lasers[5].setLaserMovementSpeed(15);
            lasers[5].setMovementType("LEFT");
            lasers[5].setLaserBoundingBox(new Rectangle(lasers[5].getShipBoundingBox().getX() + lasers[5].getShipBoundingBox().getWidth() * 0.4f,
                    lasers[5].getShipBoundingBox().getY() + lasers[5].getShipBoundingBox().height * 0.3f,
                    lasers[5].getLaserWidth(), lasers[5].getLaserHeight()));

            this.bullets = lasers;

        }
        return lasers;
    }
    /**
     * Draw the laser animation
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
        return isSpreading;
    }

    public void setSpreading(boolean spreading) {
        isSpreading = spreading;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public Boss3_LaserTypeC[] getBullets() {
        return bullets;
    }

    public void setBullets(Boss3_LaserTypeC[] bullets) {
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
