package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;
import java.util.ListIterator;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.LaserTypeA;
import hcmute.spaceshooter.Lasers.LaserTypeB;
import hcmute.spaceshooter.Lasers.LaserTypeC;
import hcmute.spaceshooter.Lasers.LaserTypeD;
import hcmute.spaceshooter.Lasers.LaserTypeE;

public class PlayerShip extends Ship {
    int level;
    int maxLevel = 5;
    // List of player fired Lasers
    LinkedList<ILaser> laserList = new LinkedList<>();
    public PlayerShip(float xCentre, float yCentre,
                      float width, float height,
                      float movementSpeed, int shield, float timeBetweenShots,
                      TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, Boolean ableToFire, int HP) {
        super   (xCentre, yCentre,
                width, height,
                movementSpeed, shield, timeBetweenShots,
                shipTextureRegion, shieldTextureRegion, ableToFire, HP);

        level = 1;
        laserI = new LaserTypeE(boundingBox);
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
            ListIterator<ILaser> iterator = laserList.listIterator();
            while (iterator.hasNext()) {
                ILaser laser = iterator.next();
                if(laser != null){
                    if (laser.getLaserBoundingBox().getY() + laser.getLaserBoundingBox().getHeight() > WORLD_HEIGHT) {
                        iterator.remove();
                    }

                    else{
                        if(laser.getMovementType() == "DIAGONAL_LEFT_15"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 30f  * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else if(laser.getMovementType() == "DIAGONAL_RIGHT_15"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 30f * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else if(laser.getMovementType() == "DIAGONAL_LEFT_30"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 10f  * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else if(laser.getMovementType() == "DIAGONAL_RIGHT_30"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 10f * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else if(laser.getMovementType() == "DIAGONAL_LEFT_60"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 2.5f  * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else if(laser.getMovementType() == "DIAGONAL_RIGHT_60"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 2.5f * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else {
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }


                        laser.pushLaserUpward(deltaTime, batch);
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
                // laserI.setLaserMovementSpeed(laserI.getLaserMovementSpeed() + 5f);
                //timeBetweenShots -= 0.05f;
            }

        }
        else
        {
            if( dropDownAnimation.getTypeName().equals("RED") &&
            dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeA();
            }
            if( dropDownAnimation.getTypeName().equals("BLUE") &&
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

    public LinkedList<ILaser> getLaserList() {
        return laserList;
    }

    public void setLaserList(LinkedList<ILaser> laserList) {
        this.laserList = laserList;
    }


    //endregion Getter and Setter
}
