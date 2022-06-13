package hcmute.spaceshooter.Animation;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class FireMeteor extends DropDownAnimation {
    String typeName;
    Boolean taken = false;

    public FireMeteor() {
        drawingRectangle = new Rectangle(WORLD_WIDTH / 2, WORLD_HEIGHT, 10, 10);
        movementSpeed = 20;
        totalAnimationTime = 2f;
        typeName = "RED";
        texture = new Texture("meteor.png");
        titleWidth = 64;
        titleHeight = 64;

        rowTextureCount = 6;
        columnTextureCount = 10;
        textureNum = rowTextureCount * columnTextureCount;
        try{
            animation = GetAnimation(texture);
        }
        catch (Exception e){
            animation = null;
        }
    }

    /**
     * Split texture to 1D Texture Region Array and return the Animation
     *
     * @return Animation with the Texture transformed to type TextureRegion
     */
    @Override
    public Animation<TextureRegion> GetAnimation(Texture texture) {
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
    @Override
    public void draw(Batch batch) {
        batch.draw(texture,
                drawingRectangle.x, drawingRectangle.y, drawingRectangle.width, drawingRectangle.height);
        batch.draw(animation.getKeyFrame(timer),
                drawingRectangle.x - 4, drawingRectangle.y, drawingRectangle.width + 10, drawingRectangle.height + 10);
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
