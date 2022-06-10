package hcmute.spaceshooter.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class DropDownAnimation implements IAnimation {

    // Gdx's Animation object
    Animation<TextureRegion> animation ;

    // A timer increased with each update method call
    float timer = 0;

    // To specified the coordinate to draw into
    Rectangle drawingRectangle = new Rectangle();
    /**
     * Initialize these fields in subclass
     */
    //region
    // Movement speed of the object
    int movementSpeed;

    // Total time of the whole animation rendering.
    float totalAnimationTime;

    // Drawing Animation drawn by Texture from assets
    Texture texture;

    /**
     * The title width and height of the given Texture
     * starting from the top left corner going to the right
     * and ending at the bottom right corner.
     */
    int titleWidth;
    int titleHeight;

    int rowTextureCount;
    int columnTextureCount;

    /**
     *  The number of texture region after splitting the texture,
     *  equals to the number of images from the whole Texture
     */
    int textureNum = rowTextureCount * columnTextureCount;

    //endregion


    /**
     * Split texture to 1D Texture Region Array and return the Animation
     * @return Animation with the Texture transformed to type TextureRegion
     */
    @Override
    public Animation<TextureRegion> GetAnimation() {
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

    /** make the coordinates of the object go down the height of the screen
     * @param deltaTime The time in seconds since the last render.
     */
    public void dropDownward(float deltaTime, SpriteBatch batch){
        makeDownward(deltaTime);
        update(deltaTime);
        draw(batch);
        if(drawingRectangle.y < 0){
            texture.dispose();
        }
        else{
            if(isFinished()){
                animation = GetAnimation();
            }
        }
    }

    /** make the coordinates of the object go down the height of the screen
     * @param deltaTime The time in seconds since the last render.
     */
    public void makeDownward(float deltaTime){
        drawingRectangle.y -= movementSpeed * deltaTime;
    }

    /** Update the animation's rendering time by @param delaTime
     *
     * @param deltaTime The time in seconds since the last render.
     */
    @Override
    public void update(float deltaTime) {
        timer += deltaTime;
    }

    /**
     * Draw the animation
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void draw(Batch batch) {
        batch.draw(animation.getKeyFrame(timer),
                drawingRectangle.x, drawingRectangle.y, drawingRectangle.width, drawingRectangle.height);
    }

    /**
     * Whether the animation would be finished if played without looping, given the state time.
     */
    @Override
    public boolean isFinished() {
        return animation.isAnimationFinished(timer);
    }

    //region Getter and Setter
    public float getTotalAnimationTime() {
        return totalAnimationTime;
    }

    public void setTotalAnimationTime(float totalAnimationTime) {
        this.totalAnimationTime = totalAnimationTime;
    }
    //endregion
}
