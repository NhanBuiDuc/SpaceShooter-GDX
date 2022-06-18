package hcmute.spaceshooter.Lasers;


import static hcmute.spaceshooter.GlobalVariables.boss2_LaserTypeB_Texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class Boss3_LaserTypeB extends EnemyLaser {
    // Gdx's Animation object
    //public Animation<TextureRegion> animation ;
    int phase = 1;
    Boss1_LaserTypeB[] bullets;
    Rectangle shipBoundingBox;
    // Movement speed of the object
    public int movementSpeed;
    // Total time of the whole animation rendering.
    public float totalAnimationTime;

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
    public float firstShootTimer;
    /**
     * Constructor of the Laser Type.
     *
     * @param xCentre            : The horizontal center-coordinate of the ship
     * @param yBottom            : The vertical center-coordinate of the ship
     * @param laserWidth         :The width of the laser
     * @param laserHeight        :The height of the laser
     * @param laserMovementSpeed :The movement speed of the laser
     * @param laserTexture      :The texture for rendering the laser
     **/

    public Boss3_LaserTypeB(float xCentre, float yBottom, float laserWidth, float laserHeight, float laserMovementSpeed, Texture laserTexture) {
        super(xCentre, yBottom, laserWidth, laserHeight, laserMovementSpeed, laserTexture);
        typeName = "BLUE";
    }

    public Boss3_LaserTypeB(){

    }


    public Boss3_LaserTypeB(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = boss2_LaserTypeB_Texture;
        laserWidth = 10f;
        laserHeight = 10f;
        movementSpeed = 50;
        totalAnimationTime = 0.5f;
        typeName = "Boss2_TypeB";
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
        laserColumnTextureCount = 16;
        laserTimer = 0;
        /**
         *  The number of texture region after splitting the texture,
         *  equals to the number of images from the whole Texture
         */
        laserTextureNum = laserRowTextureCount * laserColumnTextureCount;
        // Gdx's Animation object
//        laserAnimation = GetLaserAnimation(laserTexture, laserTitleWidth, laserTitleHeight,
//                laserTextureNum, laserRowTextureCount, laserColumnTextureCount);
    }


    public void Upgrade(){
        this.level++;
    }

    public Boss1_LaserTypeB[] GetBullets() {
        Boss1_LaserTypeB[] lasers = new Boss1_LaserTypeB[2];
        if(level == 1){

            lasers[0] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.25f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().height * 0.1f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(45);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.6f,
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

    @Override
    public void drawLaser(Batch batch) {
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
    }

    @Override
    public void setTypename(String red) {

    }

    @Override
    public String getMovementType() {
        return movementType;
    }

    //region Getter and Setter

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

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
//endregion Getter and Setter
}
