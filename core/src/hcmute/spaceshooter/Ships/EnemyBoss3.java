package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Stack;

import hcmute.spaceshooter.Lasers.Boss2_LaserTypeA;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Lasers.ILaser;

public class EnemyBoss3 extends EnemyBossShip {
    public EnemyBoss3(){
        super();
        boundingBox = new Rectangle(WORLD_WIDTH / 12, WORLD_HEIGHT - 50, 60, 60);
        movementSpeed = 50;
        shield = 0;
        timeBetweenShots = 10f;
        shipTextureRegion = textureAtlas.findRegion("boss03");
        shieldTextureRegion = textureAtlas.findRegion("shield1");
        ableToFire = true;
        HP = 800;
        directionVector = new Vector2(0, -1);
        laserI = new Boss2_LaserTypeA(boundingBox);
        laserI.setLevel(1);
    }

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
