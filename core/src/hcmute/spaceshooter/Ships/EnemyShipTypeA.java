package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.SpaceShooterGame;

/**
 *  Concrete class for the enemy ship
 */
public class EnemyShipTypeA extends EnemyShip{

    public EnemyShipTypeA(){
        boundingBox = new Rectangle(SpaceShooterGame.random.nextFloat() * (WORLD_WIDTH -10), WORLD_HEIGHT - 5, 7, 7);
        movementSpeed = 30;
        shield = 0;
        timeBetweenShots = 5f;
        shipTextureRegion = textureAtlas.findRegion("enemy_ship04");
        shieldTextureRegion = textureAtlas.findRegion("shield1");
        ableToFire = false;
        HP = 5;
        directionVector = new Vector2(0, -1);
        IEnemyLaser laserI = new EnemyLaserTypeA(boundingBox);
        laserI.setLevel(1);
    }

    @Override
    public void setLaserI(ILaser laserI) {

    }
}
