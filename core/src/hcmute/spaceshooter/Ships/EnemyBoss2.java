package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

import hcmute.spaceshooter.Lasers.Boss1_LaserTypeA;
import hcmute.spaceshooter.Lasers.Boss2_LaserTypeA;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Lasers.ILaser;
/**
 *  The concrete class for the boss ship
 */
public class EnemyBoss2 extends EnemyBossShip {
    public EnemyBoss2(){
        super();
        // the ship's rectangle
        boundingBox = new Rectangle(WORLD_WIDTH / 12, WORLD_HEIGHT - 55, 60, 60);
        // the boss's movement speed
        movementSpeed = 50;
        // the boss's shield
        shield = 0;
        // the boss's attack speed
        timeBetweenShots = 5f;
        // the boss's texture
        shipTextureRegion = textureAtlas.findRegion("boss02");
        // the boss's shield texture
        shieldTextureRegion = textureAtlas.findRegion("shield1");
        // check if the boss able to shoot
        ableToFire = true;
        // the Health point of the boss
        HP = 800;
        // the direction vector of the boss
        directionVector = new Vector2(0, -1);
        // the laser of the boss
        laserI = new Boss2_LaserTypeA(boundingBox);
        // the level of the laser
        laserI.setLevel(1);
    }
    /** Get the lasers of type B
     *
     * @param deltaTime Update the status of the Ship respect to the deltaTime
     * @return the list of laser of type B
     */
    public Stack<IEnemyLaser> FireTypeB(float deltaTime) {
        Stack<IEnemyLaser> laserStack = new Stack<>();
        if(canFireLaser()) {
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
    /** Get the lasers
     *
     * @return the list of laser
     */
    @Override
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

        return laserStack;
    }

    @Override
    public void setLaserI(ILaser laserI) {
        this.laserI = (IEnemyLaser) laserI;
    }

    public void setLaserI(IEnemyLaser laserI) {
        this.laserI = laserI;
    }
}