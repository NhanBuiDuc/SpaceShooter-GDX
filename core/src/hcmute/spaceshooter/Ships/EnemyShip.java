package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.SpaceShooterGame;

public abstract class EnemyShip extends Ship{

    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.75f;
    EnemyShip clonedEnemyShip;

    public EnemyShip(float xCentre, float yCentre,
                     float width, float height,
                     float movementSpeed, int shield, float timeBetweenShots,
                     TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, Boolean ableToFire, int HP) {
        super   (xCentre, yCentre,
                width, height,
                movementSpeed, shield, timeBetweenShots,
                shipTextureRegion, shieldTextureRegion, ableToFire, HP);

        directionVector = new Vector2(0, -1);
        laserI = new EnemyLaserTypeA(boundingBox);
        laserI.setLevel(1);

        // Clone

    }
    public EnemyShip(){

    }

    private void randomizeDirectionVector(){
        double bearing = SpaceShooterGame.random.nextDouble() * 6.283185; // 0 to 2 * pi
        directionVector.x = (float) Math.sin(bearing);
        directionVector.y = (float) Math.cos(bearing);
    }

    public void update(float deltaTime) {
        timeSinceLastShot = timeSinceLastShot + deltaTime;

    }

    public void MoveRandomly(float deltaTime){
        timeSinceLastDirectionChange += deltaTime;
        if(timeSinceLastDirectionChange > directionChangeFrequency){
            randomizeDirectionVector();
            timeSinceLastDirectionChange -= directionChangeFrequency;
        }
    }

    public Stack<Laser> GetLasers() {
        // Enemy lasers
        Stack<Laser> laserStack = new Stack<>();
        if(canFireLaser()){
            Laser[] lasers = this.laserI.GetBullets();
            for(int i = 0; i < lasers.length ; i++){
                if(lasers[i] != null){
                    laserStack.push(lasers[i]);
                }

            }
        }
        timeSinceLastShot = 0;
        return laserStack;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion, boundingBox.x, boundingBox.y - boundingBox.height * 0.20f, boundingBox.width, boundingBox.height);
        }
    }

    @Override
    public boolean hitAndCheckDestroyed() {
        if(shield > 0){
            shield --;
            return false;
        }
        else{
            if(HP > 0){
                HP--;
                return false;
            }
            return true;
        }
    }

    //region Getter and Setter
    public void setDirectionVector(Vector2 directionVector) {
        this.directionVector = directionVector;
    }

    public float getTimeSinceLastDirectionChange() {
        return timeSinceLastDirectionChange;
    }

    public void setTimeSinceLastDirectionChange(float timeSinceLastDirectionChange) {
        this.timeSinceLastDirectionChange = timeSinceLastDirectionChange;
    }

    public float getDirectionChangeFrequency() {
        return directionChangeFrequency;
    }

    public void setDirectionChangeFrequency(float directionChangeFrequency) {
        this.directionChangeFrequency = directionChangeFrequency;
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    public EnemyShip getClonedEnemyShip() {
        return clonedEnemyShip;
    }

    public void setClonedEnemyShip(EnemyShip clonedEnemyShip) {
        this.clonedEnemyShip = clonedEnemyShip;
    }
    //endregion Getter and Setter
}
