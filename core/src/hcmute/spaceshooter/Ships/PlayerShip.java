package hcmute.spaceshooter.Ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.LinkedList;
import java.util.ListIterator;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Lasers.LaserTypeA;
import hcmute.spaceshooter.Lasers.LaserTypeB;
import hcmute.spaceshooter.Lasers.LaserTypeC;
import hcmute.spaceshooter.Lasers.LaserTypeD;
import hcmute.spaceshooter.Lasers.LaserTypeE;

public class PlayerShip extends Ship {
    // the level of player ship
    int level;
    // the max level the player can get with corresponding laser level
    int maxLevel = 5;
    // List of player fired Lasers
    LinkedList<ILaser> laserList = new LinkedList<>();

    // The maximum time for the player ship to get invincible
    float invincibleTime = 2f;
    // The timer to check the time the player getting invincible
    float invincibleTimer = 0;
    // true if the system start counting invincible time, false if not.
    boolean startCounterInvincibleTime = false;
    // true if the player is in invincible time, false if not.
    boolean isInvincible = false;

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
     * @param HP: Health point of the ship
     **/
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
        laserI.setTypename("ORANGE");
    }

    /** return true if the invincible time is over
     *
     * @return true if over, false if not
     */
    public boolean isFinishInvincible() {
        if(invincibleTimer - invincibleTime >= 0){

            return true;
        }
        else {

            return false;
        }
    }

    /** Set the invincible
     *
     * @param invincible true to make the player's ship invincible
     */
    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    /** Count the invincible time
     *
     * @param deltaTime The time in seconds since the last render.
     */
    public void countInvincibleTime(float deltaTime){
        if(startCounterInvincibleTime == true){
            this.invincibleTimer += deltaTime;
        }

    }

    /**
     *  Get the lasers for the player
     */
    public void GetLasers() {
        if(canFireLaser()){
            ILaser[] lasers = this.laserI.GetBullets();
            for(int i = 0; i < lasers.length ; i++){
                if(lasers[i] != null){
                    laserList.add(lasers[i]);
                }
            }
            timeSinceLastShot = 0;
        }
    }

    /** Move the player's bullets and remove them if move out the screen
     *
     * @param deltaTime  The time in seconds since the last render.
     * @param batch Draws batched quads using indices.
     * @param WORLD_HEIGHT the screen height
     */
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
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 15f  * deltaTime);
                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                        }
                        else if(laser.getMovementType() == "DIAGONAL_RIGHT_30"){
                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 15f * deltaTime);
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
    /** Check if the ship is destroyed or not
     *
     * @param laserDamage getting the laser damage of the laser
     * @return true if is destroyed, false if not
     */
    @Override
    public boolean hitAndCheckDestroyed(int laserDamage) {
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

    /** Upgrade the player's level
     *
     * @param dropDownAnimation the upgrade item
     */
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
            if( dropDownAnimation.getTypeName().equals("ORANGE") &&
            dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeA(this.boundingBox);
                if(level > 1)
                    laserI.setLevel(--level);
            }
            else if( dropDownAnimation.getTypeName().equals("BLUE") &&
                    dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeB(this.boundingBox);
                if(level > 1)
                    laserI.setLevel(--level);
            }
            else if( dropDownAnimation.getTypeName().equals("YELLOW") &&
                    dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeC(this.boundingBox);
                if(level > 1)
                    laserI.setLevel(--level);
            }
            else if( dropDownAnimation.getTypeName().equals("ARCH") &&
                    dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeD(this.boundingBox);
                if(level > 1)
                    laserI.setLevel(--level);
            }
            else if( dropDownAnimation.getTypeName().equals("GREEN") &&
                    dropDownAnimation.getTypeName() != this.laserI.getTypeName()){
                laserI = (ILaser) new LaserTypeE(this.boundingBox);
                if(level > 1)
                    laserI.setLevel(--level);
            }
        }
    }

    //region Getter and Setter
    @Override
    public void setLaserI(ILaser laserI) {
        this.laserI = laserI;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LinkedList<ILaser> getLaserList() {
        return laserList;
    }

    public void setInvincibleTimer(float invincibleTimer) {
        this.invincibleTimer = invincibleTimer;
    }
    public boolean isInvincible() {
        return isInvincible;
    }

    public void setStartCounterInvincibleTime(boolean startCounterInvincibleTime) {
        this.startCounterInvincibleTime = startCounterInvincibleTime;
    }

    //endregion Getter and Setter
}
