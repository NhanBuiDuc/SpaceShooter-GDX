package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

import hcmute.spaceshooter.Lasers.Boss1_LaserTypeB;
import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.SpaceShooterGame;

public abstract class EnemyBossShip extends EnemyShip{
    // Direction vector for moving enemy randomly
    Vector2 directionVector;
    // time since the last direction change
    float timeSinceLastDirectionChange = 0;
    // the frequency for the enemy to change direction
    float directionChangeFrequency = 0.75f;
    // Enemy's laser
    IEnemyLaser laserI;

    public EnemyBossShip(){
        directionVector = new Vector2(0, -1);
        laserI = new EnemyLaserTypeA(boundingBox);
        laserI.setLevel(1);
    }

    /** check if the boss is dead
     *
     * @return true if the boss is dead, false if the boss is not dead
     */
    public boolean IsDead(){
        return HP < 1;
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
        boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y);
    }
    /** Update the change direction time
     *
     * @param deltaTime
     */
    public void MoveRandomly(float deltaTime){
        timeSinceLastDirectionChange += deltaTime;
        if(timeSinceLastDirectionChange > directionChangeFrequency){
            randomizeDirectionVector();
            timeSinceLastDirectionChange -= directionChangeFrequency;
        }
    }
    /** Get the lasers
     *
     * @return the list of laser
     */
    public Stack<IEnemyLaser> GetLasers() {
        if (canFireLaser()) {
            // Enemy lasers
            Stack<IEnemyLaser> laserStack = new Stack<>();
            if (canFireLaser()) {
                IEnemyLaser[] lasers = this.laserI.GetBullets();
                for (int i = 0; i < lasers.length; i++) {
                    if (lasers[i] != null) {
                        laserStack.push(lasers[i]);
                    }

                }
            }
            timeSinceLastShot = 0;
            return laserStack;
        }
        return null;
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

    //region Getter and Setter

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    public void setLaserI(IEnemyLaser laserI) {

    }

    public abstract Stack<IEnemyLaser> FireTypeB(float deltaTime);

    //endregion Getter and Setter
}
