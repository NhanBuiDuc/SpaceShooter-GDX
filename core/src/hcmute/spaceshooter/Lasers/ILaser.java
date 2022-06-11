package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public interface ILaser {

    void draw(Batch batch);

    void setLevel(int i);

    Laser[] GetBullets();

    String getTypeName();

    void setTypename(String red);

    Rectangle getBoundingBox();

    float getLaserMovementSpeed();

    String getMovementType();
}
