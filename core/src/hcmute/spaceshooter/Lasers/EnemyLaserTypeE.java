package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyLaserTypeE extends EnemyLaser {
    // level of the bullet
    int level = 1;
    // The array of this type's bullet
    EnemyLaserTypeE[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;

    /** Constructor
     * @param shipBoundingBox the rectangle of the firing ship
     */
    public EnemyLaserTypeE(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = new Texture("bullet_enemy05.png");
        laserWidth = 3f;
        laserHeight = 10f;
        laserMovementSpeed = 70;
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
    public EnemyLaserTypeE[] GetBullets(){
        EnemyLaserTypeE[] lasers = new EnemyLaserTypeE[20];

        lasers[0] = new EnemyLaserTypeE(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(laserMovementSpeed);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.44f,
                lasers[0].getShipBoundingBox().getY() - lasers[0].getShipBoundingBox().getHeight() * 0.35f,
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

    public EnemyLaserTypeE[] getBullets() {
        return bullets;
    }

    public void setBullets(EnemyLaserTypeE[] bullets) {
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
