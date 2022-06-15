package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyLaserTypeB extends EnemyLaser {

    int level = 1;
    EnemyLaserTypeB[] bullets;
    Rectangle shipBoundingBox;

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

    public EnemyLaserTypeB(float xCentre, float yBottom, float laserWidth, float laserHeight, float laserMovementSpeed, Texture laserTexture) {
        super(xCentre, yBottom, laserWidth, laserHeight, laserMovementSpeed, laserTexture);
        typeName = "BLUE";
    }

    public EnemyLaserTypeB(){

    }


    public EnemyLaserTypeB(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = new Texture("bullet_enemy01.png");
        laserWidth = 5f;
        laserHeight = 5f;
        typeName = "RED";
    }


    public void Upgrade(){
        this.level++;
    }

    public EnemyLaserTypeB[] GetBullets(){
        EnemyLaserTypeB[] lasers = new EnemyLaserTypeB[20];
        if(level == 1)
        {
//            lasers[0] = new Laser_TypeA(shipBoundingBox.x + shipBoundingBox.width * 0.18f, shipBoundingBox.y - laserHeight,
//                    laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
//            lasers[1] = new Laser_TypeA(shipBoundingBox.x + shipBoundingBox.width * 0.82f, shipBoundingBox.y - laserHeight,
//                    laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);

            lasers[0] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(45);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[1].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


            this.bullets = lasers;
            return lasers;
        }
        if(level == 2){
            lasers[0] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(45);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.50f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().getHeight() * 1,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


            lasers[2] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(45);
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));

            this.bullets = lasers;
            return lasers;
        }
        if(level == 3){
            lasers[0] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(50);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(50);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.50f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().getHeight() * 1,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


            lasers[2] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(50);
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));

            lasers[3] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[3].setLaserWidth(laserWidth);
            lasers[3].setLaserHeight(laserHeight);
            lasers[3].setLaserMovementSpeed(50);
            lasers[3].setLaserBoundingBox(new Rectangle(lasers[3].getShipBoundingBox().getX() + lasers[3].getShipBoundingBox().getWidth() * 0.22f,
                    lasers[3].getShipBoundingBox().getY() + lasers[3].getShipBoundingBox().getHeight() * 0.7f,
                    lasers[3].getLaserWidth(), lasers[1].getLaserHeight()));


            lasers[4] = new EnemyLaserTypeB(shipBoundingBox);
            lasers[4].setLaserWidth(laserWidth);
            lasers[4].setLaserHeight(laserHeight);
            lasers[4].setLaserMovementSpeed(50);
            lasers[4].setLaserBoundingBox(new Rectangle(lasers[4].getShipBoundingBox().getX() + lasers[4].getShipBoundingBox().getWidth() * 0.77f,
                    lasers[4].getShipBoundingBox().getY() + lasers[4].getShipBoundingBox().getHeight() * 0.7f,
                    lasers[4].getLaserWidth(), lasers[4].getLaserHeight()));

            this.bullets = lasers;
            return lasers;
        }
        return lasers;
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
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
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
    public void increaseShootingDuration(float elapsedTime) {

    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public EnemyLaserTypeB[] getBullets() {
        return bullets;
    }

    public void setBullets(EnemyLaserTypeB[] bullets) {
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
