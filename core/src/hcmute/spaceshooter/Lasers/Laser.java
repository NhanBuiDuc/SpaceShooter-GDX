package hcmute.spaceshooter.Lasers;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 *  The abstract class of the laser implementing ILaser
 */
public abstract class Laser implements ILaser {

    // laser physical characteristics
    public String movementType;
    // level of the laser
    int level;
    // the damage of the bullets
    int damage;
    // type of the laser
    String typeName;
    // The movement speed of the laser
    float laserMovementSpeed; // world units per second
    //laser information
    // Lasers' width and height
    float laserWidth, laserHeight;
    // graphics
    // Texture to render the laser
    Texture laserTexture;
    // position and dimensions of the laser
    Rectangle laserBoundingBox;

    // Gdx's Animation object
    Animation<TextureRegion> laserAnimation;

    /**
     * The title width and height of the given Texture
     * starting from the top left corner going to the right
     * and ending at the bottom right corner.
     */
    int laserTitleWidth, laserTitleHeight;
    // Total time of the whole animation rendering.
    float totalLaserAnimationTime;
    // number of row of image in the texture
    int laserRowTextureCount;
    // number of column of image in the texture
    int laserColumnTextureCount;

    /**
     *  The number of texture region after splitting the texture,
     *  equals to the number of images from the whole Texture
     */
    int laserTextureNum;
    // A timer increased with each update method call
    float laserTimer = 0;

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
        this.laserBoundingBox = new Rectangle(xCentre - laserWidth/2, yBottom, laserWidth, laserHeight);
        this.laserWidth = laserWidth;
        this.laserHeight = laserHeight;
        this.laserMovementSpeed = laserMovementSpeed;
        this.laserTexture = laserTexture;
        this.damage = 1;
    }

    public Laser() {

    }
    /** Get the animation based on the texture
     *
     * @param texture animation texture
     * @param titleWidth the width for each image in the texture
     * @param titleHeight the height for each image in the texture
     * @param textureNum the number of image in the texture
     * @param rowTextureCount the rows of images in the texture
     * @param columnTextureCount the columns of images in the texture
     * @return
     */
    public Animation<TextureRegion> GetLaserAnimation(Texture texture, int titleWidth, int titleHeight,
                                                 int textureNum, int rowTextureCount, int columnTextureCount) {
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
        laserTimer = 0;

        // Frame duration = Desired Animation Time / number of images
        return new Animation<TextureRegion>(totalLaserAnimationTime / textureNum, textureRegion1D);
    }



    /**
     * Draw the laser animation
     *
     * @param batch Draws batched quads using indices.
     */

    public void drawLaser(Batch batch){
         batch.draw(laserAnimation.getKeyFrame(laserTimer),
                 laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
     }


    /** make the coordinates of the object go down the height of the screen
     * @param deltaTime The time in seconds since the last render.
     */

    @Override
    public void pushLaserUpward(float deltaTime, Batch batch) {
        //makeUpward(deltaTime);
        laserUpdate(deltaTime);
        drawLaser(batch);
        if(laserBoundingBox.y < 0){
            // texture.dispose();
        }
        else{
            if(isLaserAnimationFinished()){
                laserAnimation = GetLaserAnimation(laserTexture, laserTitleWidth, laserTitleHeight,
                        laserTextureNum, laserRowTextureCount, laserColumnTextureCount);
                // drawingRectangle.y = WORLD_HEIGHT;
            }
            if(laserBoundingBox.y > WORLD_HEIGHT){
//            drawingRectangle.y = WORLD_HEIGHT;
                // laserTexture.dispose();
            }
        }
    }

    /** make the coordinates of the object go Up the height of the screen
     * @param deltaTime The time in seconds since the last render.
     */

    public void makeUpward(float deltaTime){
        laserBoundingBox.y += laserMovementSpeed * deltaTime;
    }

    /** make the coordinates of the object go down the height of the screen
     * @param deltaTime The time in seconds since the last render.
     */

    public void makeDownward(float deltaTime){
        laserBoundingBox.y -= laserMovementSpeed * deltaTime;
    }

    /** Update the laser animation's rendering time by @param delaTime
     *
     * @param deltaTime The time in seconds since the last render.
     */

    public void laserUpdate(float deltaTime) {
        laserTimer += deltaTime;
    }

    /**
     * Whether the laser animation would be finished if played without looping, given the state time.
     */

    public boolean isLaserAnimationFinished() {
        return laserAnimation.isAnimationFinished(laserTimer);
    }


     //region Getter and Setter
    public abstract ILaser[] GetBullets();

    @Override

    public void setLevel(int level) {
        this.level = level;
    }

    public Rectangle getLaserBoundingBox() {
        return laserBoundingBox;
    }

    abstract public void setLaserBoundingBox(Rectangle laserBoundingBox);

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

     public int getLevel() {
         return level;
     }

     public int getDamage(){
        return damage;
     }

     public String getTypeName() {
         return typeName;
     }

     public void setTypeName(String typeName) {
         this.typeName = typeName;
     }

     @Override
     public String getMovementType() {
         return movementType;
     }

     public void setMovementType(String movementType) {
         this.movementType = movementType;
     }

//endregion Getter and Setter
}
