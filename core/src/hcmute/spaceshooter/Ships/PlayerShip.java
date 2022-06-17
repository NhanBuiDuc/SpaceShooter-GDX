package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ListIterator;

import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.LaserTypeA;

public class PlayerShip extends Ship {
    int lives;
    int level;
    public PlayerShip(float xCentre, float yCentre,
                      float width, float height,
                      float movementSpeed, int shield, float timeBetweenShots,
                      TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion) {
        super   (xCentre, yCentre,
                width, height,
                movementSpeed, shield, timeBetweenShots,
                shipTextureRegion, shieldTextureRegion);

        lives = 3;
        level = 3;
        laserI = new LaserTypeA(boundingBox);
        laserI.setLevel(level);
    }

    @Override
    public void fireLasers(float deltaTime, Batch batch, int WORLD_HEIGHT) {
        if(canFireLaser()){
            Laser[] lasers = this.laserI.GetBullets();
            for(Laser laser: lasers){
                this.laserList.add(laser);
            }
            timeSinceLastShot = 0;
        }
    }
    public void RemoveBullets(float deltaTime, Batch batch, int WORLD_HEIGHT){

        if(!laserList.isEmpty()){
            ListIterator<Laser> iterator = laserList.listIterator();
            while (iterator.hasNext()) {
                Laser laser = iterator.next();
                if(laser != null){
                    if (laser.getBoundingBox().getY() + laser.getBoundingBox().getHeight() > WORLD_HEIGHT) {
                        iterator.remove();
                    }
                    else{
                        laser.draw(batch);
                        laser.getBoundingBox().setY(laser.getBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                    }
                }

                else{

                }
            }
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    //endregion Getter and Setter
}
