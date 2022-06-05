package hcmute.spaceshooter;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

abstract public class Ship {

    // ship characteristic
    float movementSpeed; // world units per second
    int shield;

    // position & dimension
    Rectangle boundingBox;

    // laser information
    float laserWidth, laserHeight;
    float laserMovementSpeed;
    float timeBetweenShots;
    float timeSinceLastShot = 0;

    // graphics
    TextureRegion shipTextureRegion, shieldTextureRegion, laserTextureRegion;

    public Ship(float xCentre, float yCentre,
                float width, float height,
                float movementSpeed, int shield,
                float laserWidth, float laserHeight, float laserMovementSpeed,
                float timeBetweenShots,
                TextureRegion shipTextureRegion, TextureRegion shieldTextureRegion, TextureRegion laserTextureRegion) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.boundingBox = new Rectangle(xCentre - width/2, yCentre - height/2, width, height);
        this.laserMovementSpeed = laserMovementSpeed;
        this.timeBetweenShots = timeBetweenShots;
        this.shipTextureRegion = shipTextureRegion;
        this.shieldTextureRegion = shieldTextureRegion;
        this.laserTextureRegion = laserTextureRegion;
    }
    public void update(float deltaTime){
        timeSinceLastShot = timeSinceLastShot + deltaTime;

    }
    public boolean canFireLaser(){
        boolean result = timeSinceLastShot - timeBetweenShots >= 0;
        return result;
    }

    public abstract Laser[] fireLasers();

    public boolean intersects(Rectangle otherRectangle){
        return this.boundingBox.overlaps(otherRectangle);
    }
    public void draw(Batch batch){
        batch.draw(shipTextureRegion, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        if(shield > 0) {
            batch.draw(shieldTextureRegion,  boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }

    }

    public abstract boolean hitAndCheckDestroyed(Laser laser);

    public void translate(float xChange, float yChange){
        boundingBox.setPosition(boundingBox.x + xChange, boundingBox.y + yChange);
    }
}
