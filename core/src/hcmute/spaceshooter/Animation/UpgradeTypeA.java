package hcmute.spaceshooter.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class UpgradeTypeA extends DropDownAnimation {

    public UpgradeTypeA(int WORLD_WIDTH, int WORLD_HEIGHT) {
        drawingRectangle = new Rectangle(WORLD_WIDTH / 2, WORLD_HEIGHT, 20, 20);
        movementSpeed = 20;
        totalAnimationTime = 1.5f;
        texture = new Texture("red_present.png");
        titleWidth = 64;
        titleHeight = 64;

        rowTextureCount = 4;
        columnTextureCount = 4;
        textureNum = rowTextureCount * columnTextureCount;
        try{
            animation = GetAnimation();
        }
        catch (Exception e){
            animation = null;
        }
    }

    /**
     * Draw the animation
     *
     * @param batch Draws batched quads using indices.
     */
    @Override
    public void draw(Batch batch) {
        batch.draw(texture,
                drawingRectangle.x, drawingRectangle.y, drawingRectangle.width, drawingRectangle.height);
    }

    /**
     * make the coordinates of the object go down the height of the screen
     *
     * @param deltaTime The time in seconds since the last render.
     * @param batch
     */
    @Override
    public void dropDownward(float deltaTime, SpriteBatch batch) {
        makeDownward(deltaTime);
        update(deltaTime);
        draw(batch);
        if(drawingRectangle.y < -50){
            texture.dispose();
        }
    }
}
