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

public abstract class EnemyShip extends Ship{

    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.75f;
    IEnemyLaser laserI;
    boolean isInHorde = false;
    public EnemyShip(){

    }

    public void randomizeDirectionVector(){
        double bearing = SpaceShooterGame.random.nextDouble() * 6.283185; // 0 to 2 * pi
        directionVector.x = (float) Math.sin(bearing);
        directionVector.y = (float) Math.cos(bearing);

    }

    public void update(float deltaTime) {
        timeSinceLastShot = timeSinceLastShot + deltaTime;

    }

    @Override
    public void translate(float xChange, float yChange) {
        if(isInHorde == false){
            boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y + yChange);
        }
        else {
            boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y);
        }
    }

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
