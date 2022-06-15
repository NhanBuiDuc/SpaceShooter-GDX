package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyLaserTypeA extends Laser {

    int level = 1;
    EnemyLaserTypeA[] bullets;
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

    public EnemyLaserTypeA(float xCentre, float yBottom, float laserWidth, float laserHeight, float laserMovementSpeed, Texture laserTexture) {
        super(xCentre, yBottom, laserWidth, laserHeight, laserMovementSpeed, laserTexture);
        typeName = "BLUE";
    }

    public EnemyLaserTypeA(){

    }


    public EnemyLaserTypeA(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = new Texture("bullet_enemy02.png");
        laserWidth = 5f;
        laserHeight = 5f;
        typeName = "RED";
    }


    public void Upgrade(){
        this.level++;
    }

    public EnemyLaserTypeA[] GetBullets(){
        EnemyLaserTypeA[] lasers = new EnemyLaserTypeA[20];

//            lasers[0] = new Laser_TypeA(shipBoundingBox.x + shipBoundingBox.width * 0.18f, shipBoundingBox.y - laserHeight,
//                    laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
//            lasers[1] = new Laser_TypeA(shipBoundingBox.x + shipBoundingBox.width * 0.82f, shipBoundingBox.y - laserHeight,
//                    laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);

        lasers[0] = new EnemyLaserTypeA(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(45);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                lasers[0].getShipBoundingBox().getY(),
                lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


        lasers[1] = new EnemyLaserTypeA(shipBoundingBox);
        lasers[1].setLaserWidth(laserWidth);
        lasers[1].setLaserHeight(laserHeight);
        lasers[1].setLaserMovementSpeed(45);
        lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.93f,
                lasers[1].getShipBoundingBox().getY(),
                lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


        this.bullets = lasers;
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

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public EnemyLaserTypeA[] getBullets() {
        return bullets;
    }

    public void setBullets(EnemyLaserTypeA[] bullets) {
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
