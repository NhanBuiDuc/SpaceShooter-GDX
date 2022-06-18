package hcmute.spaceshooter.Lasers;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.meteorTexture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class EnemyLaserTypeA extends EnemyLaser {
    // Gdx's Animation object
    public Animation<TextureRegion> animation ;
    // level of the bullet
    int level = 1;
    // The array of this type's bullet
    EnemyLaserTypeA[] bullets;
    // the ship's drawing rectangle
    Rectangle shipBoundingBox;
    // Movement speed of the object
    public int movementSpeed;
    // Total time of the whole animation rendering.
    public float totalAnimationTime;
    // number of rows and columns of images in the texture
    public int rowTextureCount;
    public int columnTextureCount;
    /**
     * The title width and height of the given Texture
     * starting from the top left corner going to the right
     * and ending at the bottom right corner.
     */
    public int titleWidth;
    public int titleHeight;
    /**
     *  The number of texture region after splitting the texture,
     *  equals to the number of images from the whole Texture
     */
    public int textureNum = rowTextureCount * columnTextureCount;
    // A timer increased with each update method call
    public float timer = 0;

    /** Constructor
     *
     * @param shipBoundingBox the rectangle of the firing ship
     */
    public EnemyLaserTypeA(Rectangle shipBoundingBox) {
        // the ship's drawing rectangle
        this.shipBoundingBox = shipBoundingBox;
        // the laser's texture
        laserTexture = new Texture("bullet_enemy02.png");
        // the laser width
        laserWidth = 5f;
        // the laser height
        laserHeight = WORLD_HEIGHT;
        // the laser's type name
        typeName = "Boss_Laser_1_Type_A";
        // laser movement speed
        movementSpeed = 30;
        // Total time of the whole animation rendering.
        totalAnimationTime = 5f;
        // the laser's type name
        typeName = "FIRE_METEOR";
        /**
         * The title width and height of the given Texture
         * starting from the top left corner going to the right
         * and ending at the bottom right corner.
         */
        titleWidth = 32;
        titleHeight = 138;
        // number of row of image in the texture
        rowTextureCount = 1;
        // number of column of image in the texture
        columnTextureCount = 4;
        /**
         *  The number of texture region after splitting the texture,
         *  equals to the number of images from the whole Texture
         */
        textureNum = rowTextureCount * columnTextureCount;

        try{
            animation = GetAnimation(laserTexture);
        }
        catch (Exception e){
            animation = null;
        }
    }

    /**
     * Upgrade the level of the laser
     */
    public void Upgrade(){
        this.level++;
    }

    /**
     * Split texture to 1D Texture Region Array and return the Animation
     * @return Animation with the Texture transformed to type TextureRegion
     */
    public Animation<TextureRegion> GetAnimation(Texture texture) {
        // split texture
        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, titleWidth, titleHeight);

        // convert to 1D array
        TextureRegion[] textureRegion1D = new TextureRegion[textureNum];
        int index = 0;
        for(int i = 0; i < rowTextureCount; i++){
            for(int j = 0; j < columnTextureCount; j++){
                textureRegion1D[index] = textureRegion2D[i][j];
                index++;
            }
        }
        timer = 0;

        // Frame duration = Desired Animation Time / number of images
        return animation = new Animation<TextureRegion>(totalAnimationTime / textureNum, textureRegion1D);
    }
    /**
     * Draw the animation
     *
     * @param batch Draws batched quads using indices.
     */

    public void draw(Batch batch) {
        batch.draw(animation.getKeyFrame(timer),
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
    }
    /**
     *
     * @return the array of this type
     */
    public EnemyLaserTypeA[] GetBullets(){
        EnemyLaserTypeA[] lasers = new EnemyLaserTypeA[20];

        lasers[0] = new EnemyLaserTypeA(shipBoundingBox);
        lasers[0].setLaserWidth(laserWidth);
        lasers[0].setLaserHeight(laserHeight);
        lasers[0].setLaserMovementSpeed(45);
        lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.5f,
                lasers[0].getShipBoundingBox().getY(),
                lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


        lasers[1] = new EnemyLaserTypeA(shipBoundingBox);
        lasers[1].setLaserWidth(laserWidth);
        lasers[1].setLaserHeight(laserHeight);
        lasers[1].setLaserMovementSpeed(45);
        lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 1.1f,
                lasers[1].getShipBoundingBox().getY(),
                lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


        this.bullets = lasers;
        return lasers;
    }


    /**
     * Draw the laser animation
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void drawLaser(Batch batch) {
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
    }

    //region Getter and Setter

    @Override
    public String getMovementType() {
        return movementType;
    }
    public int getLevel() {
        return level;
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void drawLasersWithAnimation(float deltaTime, Batch batch) {

    }

    @Override
    public boolean isSpreading() {
        return false;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public EnemyLaserTypeA[] getBullets() {
        return bullets;
    }

    public void setBullets(EnemyLaserTypeA[] bullets) {
        this.bullets = bullets;
    }

    public Rectangle getShipBoundingBox() {
        return shipBoundingBox;
    }

    public void setShipBoundingBox(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
    }

    public Texture getLaserTexture() {
        return laserTexture;
    }

    public void setLaserTexture(Texture laserTexture) {
        this.laserTexture = laserTexture;
    }

    //endregion Getter and Setter
}
