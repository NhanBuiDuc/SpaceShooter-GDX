package hcmute.spaceshooter.Lasers;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Boss1_LaserTypeB extends EnemyLaser {
    // Gdx's Animation object
    public Animation<TextureRegion> animation ;
    int phase = 1;
    Boss1_LaserTypeB[] bullets;
    Rectangle shipBoundingBox;
    // Movement speed of the object
    public int movementSpeed;
    // Total time of the whole animation rendering.
    public float totalAnimationTime;

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
    public float firstShootTimer;

    /**
     * Constructor of the Laser Type.
     *
     * @param xCentre            : The horizontal center-coordinate of the ship
     * @param yBottom            : The vertical center-coordinate of the ship
     * @param laserWidth         :The width of the laser
     * @param laserHeight        :The height of the laser
     * @param laserMovementSpeed :The movement speed of the laser
     * @param laserTexture      :The texture for rendering the laser
     **/

    public Boss1_LaserTypeB(float xCentre, float yBottom, float laserWidth, float laserHeight, float laserMovementSpeed, Texture laserTexture) {
        super(xCentre, yBottom, laserWidth, laserHeight, laserMovementSpeed, laserTexture);
        typeName = "BLUE";
    }

    public Boss1_LaserTypeB(){

    }


    public Boss1_LaserTypeB(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        laserTexture = textureAtlas.findRegion("bullet_enemy01").getTexture();
        laserWidth = 10f;
        laserHeight = WORLD_HEIGHT;
        typeName = "Boss_Laser_1_Type_A";
        movementSpeed = 30;
        totalAnimationTime = 0.5f;
        typeName = "FIRE_METEOR";
        titleWidth = 32;
        titleHeight = 138;

        rowTextureCount = 1;
        columnTextureCount = 16;

        textureNum = rowTextureCount * columnTextureCount;
        try{
            animation = GetAnimation(laserTexture);
        }
        catch (Exception e){
            animation = null;
        }
    }


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
     * Draw the laser animation
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void drawLaser(Batch batch) {
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
    }

    public Boss1_LaserTypeB[] GetBullets(float deltaTime) {
        Boss1_LaserTypeB[] lasers = new Boss1_LaserTypeB[6];
        if(phase == 1){

            lasers[0] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(10f);
            lasers[0].setLaserHeight(10f);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[0].getShipBoundingBox().getY(),
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));
            this.bullets = lasers;
        }
        else if (phase == 2) {

            lasers[0] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(45);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[0].getShipBoundingBox().getY(),
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(45);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[1].getShipBoundingBox().getY(),
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));
            lasers[2] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(45);
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.07f,
                    lasers[2].getShipBoundingBox().getY(),
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));


            lasers[3] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[3].setLaserWidth(laserWidth);
            lasers[3].setLaserHeight(laserHeight);
            lasers[3].setLaserMovementSpeed(45);
            lasers[3].setLaserBoundingBox(new Rectangle(lasers[3].getShipBoundingBox().getX() + lasers[3].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[3].getShipBoundingBox().getY(),
                    lasers[3].getLaserWidth(), lasers[3].getLaserHeight()));
            lasers[4] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[4].setLaserWidth(laserWidth);
            lasers[4].setLaserHeight(laserHeight);
            lasers[4].setLaserMovementSpeed(45);
            lasers[4].setLaserBoundingBox(new Rectangle(lasers[4].getShipBoundingBox().getX() + lasers[4].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[4].getShipBoundingBox().getY(),
                    lasers[4].getLaserWidth(), lasers[4].getLaserHeight()));
            lasers[5] = new Boss1_LaserTypeB(shipBoundingBox);
            lasers[5].setLaserWidth(laserWidth);
            lasers[5].setLaserHeight(laserHeight);
            lasers[5].setLaserMovementSpeed(45);
            lasers[5].setLaserBoundingBox(new Rectangle(lasers[5].getShipBoundingBox().getX() + lasers[5].getShipBoundingBox().getWidth() * 0.93f,
                    lasers[5].getShipBoundingBox().getY(),
                    lasers[5].getLaserWidth(), lasers[5].getLaserHeight()));

            this.bullets = lasers;
        }
        return lasers;
    }


    public boolean isFinished() {
        return animation.isAnimationFinished(timer);
    }


    @Override
    public void setTypename(String red) {

    }


    @Override
    public IEnemyLaser[] GetBullets() {
        return new IEnemyLaser[0];
    }


    @Override
    public String getMovementType() {
        return movementType;
    }

    //region Getter and Setter

    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public Boss1_LaserTypeB[] getBullets() {
        return bullets;
    }

    public void setBullets(Boss1_LaserTypeB[] bullets) {
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
