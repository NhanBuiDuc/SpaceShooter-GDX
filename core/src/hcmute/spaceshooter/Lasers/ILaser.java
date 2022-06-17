package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public interface ILaser {

    void drawLaser(Batch batch);

    void setLevel(int i);

    ILaser[] GetBullets();

    String getTypeName();

    void setTypename(String red);

    Rectangle getLaserBoundingBox();

    float getLaserMovementSpeed();

    String getMovementType();

    void setLaserMovementSpeed(float v);

    int getLevel();

    int getDamage();

    void drawExplosion(Batch batch);

    void pushLaserUpward(float deltaTime,Batch batch);

    void increaseShootingDuration(float elapsedTime);
}
