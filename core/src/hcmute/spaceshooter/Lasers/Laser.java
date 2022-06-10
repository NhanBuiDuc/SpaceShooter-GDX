package hcmute.spaceshooter.Lasers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract public class Laser {

    // position and dimensions of the laser
    Rectangle boundingBox;

    //laser physical characteristics

    // The movement speed of the laser
    float laserMovementSpeed; // world units per second

    // graphics
    // Texture to render the laser
    Texture laserTexture;

    // laser information
    // Lasers' width and height
    float laserWidth, laserHeight;

    /**
     * Constructor of the Laser Type.
     * @param xCentre : The horizontal center-coordinate of the ship
     * @param yBottom : The vertical center-coordinate of the ship
     * @param laserWidth :The width of the laser
     * @param laserHeight :The height of the laser
     * @param laserMovementSpeed :The movement speed of the laser
     * @param laserTexture :The texture for rendering the laser
     **/
    public Laser(float xCentre, float yBottom,
                 float laserWidth, float laserHeight, float laserMovementSpeed, Texture laserTexture) {
        this.boundingBox = new Rectangle(xCentre - laserWidth/2, yBottom, laserWidth, laserHeight);
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.laserTexture = laserTexture;
    }

    public Laser() {

    }

    public void draw(Batch batch){
        batch.draw(laserTexture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }
    public abstract Laser[] GetBullets();
    public abstract void setLevel(int i);

    //region Getter and Setter
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    abstract public void setBoundingBox(Rectangle boundingBox);

    public float getLaserMovementSpeed() {
        return laserMovementSpeed;
    }

    public void setLaserMovementSpeed(float laserMovementSpeed) {
        this.laserMovementSpeed = laserMovementSpeed;
    }

    public float getLaserWidth() {
        return laserWidth;
    }

    public void setLaserWidth(float laserWidth) {
        this.laserWidth = laserWidth;
    }

    public float getLaserHeight() {
        return laserHeight;
    }

    public void setLaserHeight(float laserHeight) {
        this.laserHeight = laserHeight;
    }

    public Texture getLaserTexture() {
        return laserTexture;
    }

    public void setLaserTexture(Texture laserTexture) {
        this.laserTexture = laserTexture;
    }


//endregion Getter and Setter
}
