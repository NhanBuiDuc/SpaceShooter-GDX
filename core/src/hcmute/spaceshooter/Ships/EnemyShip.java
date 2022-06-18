package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.SpaceShooterGame;

/**
 *  Abstract class for the enemy ship
 */
public abstract class EnemyShip extends Ship{
    // Direction vector for moving enemy randomly
    Vector2 directionVector;
    // time since the last direction change
    float timeSinceLastDirectionChange = 0;
    // the frequency for the enemy to change direction
    float directionChangeFrequency = 0.75f;

    // Enemy's laser
    IEnemyLaser laserI;

    // check if the ship is in horde
    boolean isInHorde = false;

    public EnemyShip(){

    }

    /**
     * Random the vector
     */
    public void randomizeDirectionVector(){
        double bearing = SpaceShooterGame.random.nextDouble() * 6.283185; // 0 to 2 * pi
        directionVector.x = (float) Math.sin(bearing);
        directionVector.y = (float) Math.cos(bearing);

    }

    /** Update the shooting time
     *
     * @param deltaTime: Update the status of the Ship respect to the deltaTime
     */
    public void update(float deltaTime) {
        timeSinceLastShot = timeSinceLastShot + deltaTime;

    }

    /** Change the coordinate of the ship
     *
     * @param xChange the change in horizontal axis
     * @param yChange the change in vertical axis
     */
    @Override
    public void translate(float xChange, float yChange) {
        if(isInHorde == false){
            boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y + yChange);
        }
        else {
            boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y);
        }
    }

    /** Update the change direction time
     *
     * @param deltaTime
     */
    public void MoveRandomly(float deltaTime){
        if(isInHorde == false){
            timeSinceLastDirectionChange += deltaTime;
            if(timeSinceLastDirectionChange > directionChangeFrequency){
                randomizeDirectionVector();
                timeSinceLastDirectionChange -= directionChangeFrequency;
            }
        }
        else {
            timeSinceLastDirectionChange += deltaTime;
            if(timeSinceLastDirectionChange > directionChangeFrequency){
                timeSinceLastDirectionChange -= directionChangeFrequency;
            }
        }
    }

    /** Get the lasers
     *
     * @return the list of laser
     */
    public Stack<IEnemyLaser> GetLasers() {
        // Enemy lasers
        Stack<IEnemyLaser> laserStack = new Stack<>();
        if(canFireLaser()){
            IEnemyLaser[] lasers = this.laserI.GetBullets();
            for(int i = 0; i < lasers.length ; i++){
                if(lasers[i] != null){
                    laserStack.push(lasers[i]);
                }

            }
        }
        timeSinceLastShot = 0;
        return laserStack;
    }

    /** Draw the ship
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void drawShip(Batch batch) {
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion, boundingBox.x, boundingBox.y - boundingBox.height * 0.20f, boundingBox.width, boundingBox.height);
        }
    }

    /** Check if the ship is destroyed or not
     *
     * @param laserDamage getting the laser damage of the laser
     * @return true if destroyed
     */
    @Override
    public boolean hitAndCheckDestroyed(int laserDamage) {
        if(shield > 0){
            shield --;
            return false;
        }
        else{
            if(HP > 0){
                HP -= laserDamage;
                return false;
            }
            return true;
        }
    }

    //region Getter and Setter


    public Vector2 getDirectionVector() {
        return directionVector;
    }

    public boolean isInHorde() {
        return isInHorde;
    }

    public void setInHorde(boolean inHorde) {
        isInHorde = inHorde;
    }

    public void setLaserI(IEnemyLaser laserI) {
        this.laserI = laserI;
    }
    //endregion Getter and Setter
}
