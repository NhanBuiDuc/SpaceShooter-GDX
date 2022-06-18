package hcmute.spaceshooter.Animation;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.bluePowerUpTexture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
/**
 *  The concrete class for the drop-down upgrading type B animation objects
 */
public class UpgradeTypeB extends DropDownAnimation {
    String typeName;
    Boolean taken = false;
    public UpgradeTypeB() {
        drawingRectangle = new Rectangle(WORLD_WIDTH / 2, WORLD_HEIGHT, 5, 5);
        movementSpeed = 20;
        totalAnimationTime = 1.5f;
        typeName = "BLUE";
        texture = bluePowerUpTexture;
        titleWidth = 64;
        titleHeight = 64;

        rowTextureCount = 4;
        columnTextureCount = 4;
        textureNum = rowTextureCount * columnTextureCount;
        try{
            animation = GetAnimation(texture);
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
//            drawingRectangle.y = WORLD_HEIGHT;
            texture.dispose();
        }
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getTaken() {
        return taken;
    }


}
