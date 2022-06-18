package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyLaserTypeC extends EnemyLaser {
    // level of the bullet
    int level = 1;
    // The array of this type's bullet
    EnemyLaserTypeC[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;

    /** Constructor
     *
     * @param shipBoundingBox the rectangle of the firing ship
     */
    public EnemyLaserTypeC(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = new Texture("bullet_enemy03.png");
        laserWidth = 5f;
        laserHeight = 9f;
        laserMovementSpeed = 55;
        typeName = "RED";
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
    public EnemyLaserTypeC[] GetBullets(){
        EnemyLaserTypeC[] lasers = new EnemyLaserTypeC[20];

        lasers[0] = new EnemyLaserTypeC(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(laserMovementSpeed);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.2f,
                lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.1f,
                lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


        lasers[1] = new EnemyLaserTypeC(shipBoundingBox);
        lasers[1].setLaserWidth(laserWidth);
        lasers[1].setLaserHeight(laserHeight);
        lasers[1].setLaserMovementSpeed(laserMovementSpeed);
        lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.55f,
                lasers[1].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.1f,
                lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));

        this.bullets = lasers;
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
    public void drawLasersWithAnimation(float deltaTime, Batch batch) {

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

    public EnemyLaserTypeC[] getBullets() {
        return bullets;
    }

    public void setBullets(EnemyLaserTypeC[] bullets) {
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
