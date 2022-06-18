package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import hcmute.spaceshooter.ResourceManager;
import hcmute.spaceshooter.Lasers.ILaser;

/**
 * Abstract class for all the ship objects
 */
abstract public class Ship {

    // Resource Manager
    public ResourceManager rm;
    // hp of the ship
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

    /** check the time since the last shot and the attack speed of the ship
     * @return if the time since the last shot is greater, return true, else return false
     */
    public boolean canFireLaser(){
        boolean result = timeSinceLastShot - timeBetweenShots >= 0;
        return result;
    }

    /** check if this ship's rectangle intersects another
     *
     * @param otherRectangle the other rectangle this ship need to check
     * @return true if intersect, false if not
     */
    public boolean intersects(Rectangle otherRectangle){
        return this.boundingBox.overlaps(otherRectangle);
    }

    /** draw the ship and its shield, also make sound effect for the laser after shooting.
     *
     * @param batch Draws batched quads using indices.
     */
    public void drawShip(Batch batch){
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion,  boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }

    }

    /** abstract method for other subclasses, check if the ship is destroyed or not
     *
     * @param laserDamage getting the laser damage of the laser
     * @return true if is destroyed, false if not
     */
    public abstract boolean hitAndCheckDestroyed(int laserDamage);


    /** Change the coordinate of the ship
     *
     * @param xChange the change in horizontal axis
     * @param yChange the change in vertical axis
     */
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

    public abstract void setLaserI(ILaser laserI);

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
