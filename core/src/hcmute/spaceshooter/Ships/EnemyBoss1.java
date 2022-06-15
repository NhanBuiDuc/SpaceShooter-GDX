package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.SpaceShooterGame;

public class EnemyBoss1 extends EnemyShip{

    public EnemyBoss1(){
        super();
        boundingBox = new Rectangle(WORLD_WIDTH / 2, WORLD_HEIGHT + 10, 10, 10);
        movementSpeed = 50;
        shield = 1;
        timeBetweenShots = 1f;
        shipTextureRegion = textureAtlas.findRegion("enemy_ship01");
        shieldTextureRegion = textureAtlas.findRegion("shield1");
        ableToFire = true;
        HP = 5;
        directionVector = new Vector2(0, -1);
        laserI = new EnemyLaserTypeA(boundingBox);
        laserI.setLevel(1);
    }
}
