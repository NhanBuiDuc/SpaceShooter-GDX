package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface ILaser {

    void draw(Batch batch);

    void setLevel(int i);

    Laser[] GetBullets();

    String getTypeName();

    void setTypename(String red);
}
