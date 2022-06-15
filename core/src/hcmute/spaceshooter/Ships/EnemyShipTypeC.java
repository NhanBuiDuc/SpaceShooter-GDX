package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import hcmute.spaceshooter.Lasers.EnemyLaserTypeB;
import hcmute.spaceshooter.Lasers.EnemyLaserTypeC;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.SpaceShooterGame;

public class EnemyShipTypeC extends EnemyShip {
    public EnemyShipTypeC(){
        super();
        boundingBox = new Rectangle(SpaceShooterGame.random.nextFloat() * (WORLD_WIDTH -10), WORLD_HEIGHT - 5, 15, 15);
        movementSpeed = 50;
        shield = 0;
        timeBetweenShots = 1f;
        shipTextureRegion = textureAtlas.findRegion("enemy_ship03");
        shieldTextureRegion = textureAtlas.findRegion("shield1");
        ableToFire = true;
        HP = 5;
        directionVector = new Vector2(0, -1);
        laserI = new EnemyLaserTypeC(boundingBox);
        laserI.setLevel(1);
    }

    @Override
    public void setLaserI(ILaser laserI) {

    }
}
