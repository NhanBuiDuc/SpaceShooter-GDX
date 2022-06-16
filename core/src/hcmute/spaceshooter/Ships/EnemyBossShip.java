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

    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.75f;
    EnemyBossShip clonedEnemyShip;
    IEnemyLaser laserI;
    float startingShootingTimer = 0;
    float shootingDuration;
    public EnemyBossShip(float xCentre, float yCentre,
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
    public EnemyBossShip(){
        directionVector = new Vector2(0, -1);
        laserI = new EnemyLaserTypeA(boundingBox);
        laserI.setLevel(1);
    }

    public boolean isPhase2(){

        if(shootingDuration - startingShootingTimer >= 20){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean IsDead(){
        return HP < 1;
    }

    public float getStartingShootingTimer() {
        return startingShootingTimer;
    }

    public void setStartingShootingTimer(float startingShootingTimer) {
        if(this.startingShootingTimer == 0){
            this.startingShootingTimer = startingShootingTimer;
        }

    }

    public float getShootingDuration() {
        return shootingDuration;
    }

    public void setShootingDuration(float shootingDuration) {
        this.shootingDuration += shootingDuration;
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

    @Override
    public void drawShip(Batch batch) {
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

    public EnemyBossShip getClonedEnemyShip() {
        return clonedEnemyShip;
    }

    public void setClonedEnemyShip(EnemyBossShip clonedEnemyShip) {
        this.clonedEnemyShip = clonedEnemyShip;
    }

    public void setLaserI(IEnemyLaser laserI) {

    }

    public abstract Stack<IEnemyLaser> FireTypeB(float deltaTime);

    //endregion Getter and Setter
}
