package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.ListIterator;

import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.LaserTypeA;
import hcmute.spaceshooter.SpaceShooterGame;

public class EnemyShip extends Ship {

    Vector2 directionVector;
    float timeSinceLastDirectionChange = 0;
    float directionChangeFrequency = 0.75f;


    public EnemyShip(float xCentre, float yCentre,
                     float width, float height,
                     float movementSpeed, int shield, float timeBetweenShots,
                     TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion) {
        super   (xCentre, yCentre,
                width, height,
                movementSpeed, shield, timeBetweenShots,
                shipTextureRegion, shieldTextureRegion);

        directionVector = new Vector2(0, -1);
        laserI = new LaserTypeA(boundingBox);
        laserI.setLevel(2);
    }

    public Vector2 getDirectionVector() {
        return directionVector;
    }

    private void randomizeDirectionVector(){
        double bearing = SpaceShooterGame.random.nextDouble() * 6.283185; // 0 to 2 * pi
        directionVector.x = (float) Math.sin(bearing);
        directionVector.y = (float) Math.cos(bearing);

    }

    @Override
    public void update(float deltaTime, Batch batch, int WORLD_HEIGHT) {
        timeSinceLastShot = timeSinceLastShot + deltaTime;

    }
    public void RemoveLasers(float deltaTime, Batch batch){
        if(!laserList.isEmpty()){
            ListIterator<Laser> iterator = laserList.listIterator();
            while (iterator.hasNext()) {

                Laser laser = iterator.next();
                if(laser != null){
                    laser.draw(batch);
                    laser.getBoundingBox().y -= laser.getLaserMovementSpeed() * deltaTime;
                    if (laser.getBoundingBox().y + laser.getBoundingBox().height < 0) {
                        iterator.remove();
                    }
                }
            }
        }
    }
    public void MoveRandomly(float deltaTime){
        timeSinceLastDirectionChange += deltaTime;
        if(timeSinceLastDirectionChange > directionChangeFrequency){
            randomizeDirectionVector();
            timeSinceLastDirectionChange -= directionChangeFrequency;
        }
    }
    @Override
    public void fireLasers(float deltaTime, Batch batch, int WORLD_HEIGHT) {
        // Enemy lasers
        if(canFireLaser()){
            Laser[] lasers = this.laserI.GetBullets();
            laserList.addAll(Arrays.asList(lasers));
        }

        timeSinceLastShot = 0;

    }

    @Override
    public void draw(Batch batch) {
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion, boundingBox.x, boundingBox.y - boundingBox.height * 0.20f, boundingBox.width, boundingBox.height);
        }
    }

    @Override
    public boolean hitAndCheckDestroyed(Laser laser) {
        if(shield > 0){
            shield --;
            return false;
        }
        return true;
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
    //endregion Getter and Setter
}
