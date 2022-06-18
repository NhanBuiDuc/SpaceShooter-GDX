package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyLaserTypeD extends EnemyLaser  {
    // level of the bullet
    int level = 1;
    // The array of this type's bullet
    EnemyLaserTypeD[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;
    /** Constructor
     *
     * @param shipBoundingBox the rectangle of the firing ship
     */
    public EnemyLaserTypeD(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = new Texture("bullet_enemy04.png");
        laserWidth = 6f;
        laserHeight = 8f;
        laserMovementSpeed = 60;
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
    public EnemyLaserTypeD[] GetBullets(){
        EnemyLaserTypeD[] lasers = new EnemyLaserTypeD[20];

        lasers[0] = new EnemyLaserTypeD(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(laserMovementSpeed);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.3f,
                lasers[0].getShipBoundingBox().getY() - lasers[0].getShipBoundingBox().getHeight() * 0.4f,
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

    public EnemyLaserTypeD[] getBullets() {
        return bullets;
    }

    public void setBullets(EnemyLaserTypeD[] bullets) {
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
