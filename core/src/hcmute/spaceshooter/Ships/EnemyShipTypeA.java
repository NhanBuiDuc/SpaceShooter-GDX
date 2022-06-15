package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;

import hcmute.spaceshooter.Lasers.EnemyLaserTypeA;
import hcmute.spaceshooter.SpaceShooterGame;

public class EnemyShipTypeA extends EnemyShip{

    public EnemyShipTypeA(){
        boundingBox = new Rectangle(SpaceShooterGame.random.nextFloat() * (WORLD_WIDTH -10), WORLD_HEIGHT - 5, 7, 7);
        movementSpeed = 70;
        shield = 0;
        timeBetweenShots = 10.f;
        shipTextureRegion = textureAtlas.findRegion("enemy_ship03");
        shieldTextureRegion = textureAtlas.findRegion("shield1");
        ableToFire = false;
        HP = 5;
        directionVector = new Vector2(0, -1);
        laserI = new EnemyLaserTypeA(boundingBox);
        laserI.setLevel(1);
    }
}
