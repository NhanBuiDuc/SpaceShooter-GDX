package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import hcmute.spaceshooter.SoundEffect.LaserSoundEffect;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.Lasers.Laser;

/**
 * Abstract class for all the ship objects
 */
abstract public class Ship {

    int HP;

    // ship characteristic
    // world units per second
    float movementSpeed;
    // Number of shield left
    public int shield;

    // Laser
    ILaser laserI;

    // position & dimension illustrating the shape of the ship, also its size
    Rectangle boundingBox;

    // The amount of time the ship has to wait before shooting a new laser
    float timeBetweenShots;

    // The time from the last shot
    float timeSinceLastShot = 0;

    // graphics
    TextureRegion shipTextureRegion, shieldTextureRegion;

    //
    boolean ableToFire;
    /**
     * Constructor of the Ship Type.
     * @param xCentre : The horizontal center-coordinate of the ship
     * @param yCentre : The vertical center-coordinate of the ship
     * @param width : Width of the ship
     * @param height : Height of the ship
     * @param movementSpeed : Movement speed of the Ship
     * @param shield : The number of shields, neglects the damage upon getting hit
     * @param timeBetweenShots :The amount of time the ship have to wait before shooting a new laser
     * @param shieldTextureRegion :The texture for rendering the shield
     * @param shipTextureRegion :The texture for rendering the ship
     * @param ableToFire: Determine if the ship is able to fire or not
     **/
    public Ship(float xCentre, float yCentre,
                float width, float height,
                float movementSpeed, int shield,
                float timeBetweenShots,
                TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, Boolean ableToFire, int HP) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;
        this.boundingBox = new Rectangle(xCentre - width/2, yCentre - height/2, width, height);
        this.timeBetweenShots = timeBetweenShots;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.ableToFire = ableToFire;
        this.HP = HP;
    }
    public Ship(){

    }

    /**
     * @param deltaTime: Update the status of the Ship respect to the deltaTime
     */
    public void update(float deltaTime){
        timeSinceLastShot = timeSinceLastShot + deltaTime;
    }
    public boolean canFireLaser(){
        boolean result = timeSinceLastShot - timeBetweenShots >= 0;
        return result;
    }

    public boolean intersects(Rectangle otherRectangle){
        return this.boundingBox.overlaps(otherRectangle);
    }
    public void draw(Batch batch){
        LaserSoundEffect.laserSound();
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion,  boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }

    }

    public abstract boolean hitAndCheckDestroyed();

    public void translate(float xChange, float yChange){
        boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y + yChange);
    }

    //region Getter and Setter
    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getShield() {
        return shield;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    public float getTimeBetweenShots() {
        return timeBetweenShots;
    }

    public void setTimeBetweenShots(float timeBetweenShots) {
        this.timeBetweenShots = timeBetweenShots;
    }

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(float timeSinceLastShot) {
        this.timeSinceLastShot = timeSinceLastShot;
    }

    public TextureRegion getShipTextureRegion() {
        return shipTextureRegion;
    }

    public void setShipTextureRegion(TextureRegion shipTextureRegion) {
        this.shipTextureRegion = shipTextureRegion;
    }

    public TextureRegion getShieldTextureRegion() {
        return shieldTextureRegion;
    }

    public void setShieldTextureRegion(TextureRegion shieldTextureRegion) {
        this.shieldTextureRegion = shieldTextureRegion;
    }

    public ILaser getLaserI() {
        return laserI;
    }

    public void setLaserI(ILaser laserI) {
        this.laserI = laserI;
    }

    public void setLaserI(Laser laserI) {
        this.laserI = laserI;
    }



    public boolean isAbleToFire() {
        return ableToFire;
    }

    public void setAbleToFire(boolean ableToFire) {
        this.ableToFire = ableToFire;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    //endregion Getter and Setter
}
