package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.UpgradeTypeA;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.LaserTypeA;

public class PlayerShip extends Ship {
    int level;
    int maxLevel = 3;
    // List of player fired Lasers
    LinkedList<Laser> laserList = new LinkedList<>();
    public PlayerShip(float xCentre, float yCentre,
                      float width, float height,
                      float movementSpeed, int shield, float timeBetweenShots,
                      TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, Boolean ableToFire, int HP) {
        super   (xCentre, yCentre,
                width, height,
                movementSpeed, shield, timeBetweenShots,
                shipTextureRegion, shieldTextureRegion, ableToFire, HP);

        level = 1;
        laserI = new LaserTypeA(boundingBox);
        laserI.setLevel(level);
        laserI.setTypename("RED");
    }

    public void GetLasers() {
        if(canFireLaser()){
            Laser[] lasers = this.laserI.GetBullets();
            for(int i = 0; i < lasers.length ; i++){
                if(lasers[i] != null){
                    laserList.add(lasers[i]);
                }
            }
            timeSinceLastShot = 0;
        }
    }

    public void DrawAndRemoveBullets(float deltaTime, Batch batch, int WORLD_HEIGHT){

        if(!laserList.isEmpty()){
            ListIterator<Laser> iterator = laserList.listIterator();
            while (iterator.hasNext()) {
                Laser laser = iterator.next();
                if(laser != null){
                    if (laser.getBoundingBox().getY() + laser.getBoundingBox().getHeight() > WORLD_HEIGHT) {
                        iterator.remove();
                    }
                    else{

                        laser.getBoundingBox().setY(laser.getBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        laser.draw(batch);
                    }
                }
            }
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

    public void upgrade(IDropDownAnimation dropDownAnimation){
        if(dropDownAnimation.getTypeName().equals(this.laserI.getTypeName())){
            if(level < maxLevel){
                level++;
                laserI.setLevel(level);
            }

        }
        else
        {
            if( dropDownAnimation.getTypeName().equals("RED") &&
            dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeA();
            }
        }
    }

    //region Getter and Setter

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LinkedList<Laser> getLaserList() {
        return laserList;
    }

    public void setLaserList(LinkedList<Laser> laserList) {
        this.laserList = laserList;
    }


    //endregion Getter and Setter
}
