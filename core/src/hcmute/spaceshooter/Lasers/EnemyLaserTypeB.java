package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
/**
 *  The concrete class for the enemy boss laser
 */
public class EnemyLaserTypeB extends EnemyLaser {
    // level of the bullet
    int level = 1;
    // The array of this type's bullet
    EnemyLaserTypeB[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;

    /** Constructor
     *
     * @param shipBoundingBox the rectangle of the firing ship
     */
    public EnemyLaserTypeB(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = new Texture("bullet_enemy01.png");
        laserWidth = 5f;
        laserHeight = 5f;
        laserMovementSpeed = 40;
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
    public EnemyLaserTypeB[] GetBullets(){
        EnemyLaserTypeB[] lasers = new EnemyLaserTypeB[20];

        lasers[0] = new EnemyLaserTypeB(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(laserMovementSpeed);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.5f,
                lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.1f,
                lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));

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
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
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
