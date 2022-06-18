package hcmute.spaceshooter.Animation;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.meteorTexture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *  The concrete class for the meteor animation objects
 */
public class Meteor extends DropDownAnimation {
    String typeName;
    Boolean taken = false;
    Boolean isDestroyed = false;
    Integer HP = 5;
    public Meteor() {
        drawingRectangle = new Rectangle(WORLD_WIDTH / 2, WORLD_HEIGHT, 10, 10);
        movementSpeed = 30;
        totalAnimationTime = 5f;
        typeName = "FIRE_METEOR";
        texture = meteorTexture;
        titleWidth = 128;
        titleHeight = 128;

        rowTextureCount = 8;
        columnTextureCount = 8;
        textureNum = rowTextureCount * columnTextureCount;
        try{
            animation = GetAnimation(texture);
        }
        catch (Exception e){
            animation = null;
        }
    }

    /**
     * make the coordinates of the object go down the height of the screen
     *
     * @param deltaTime The time in seconds since the last render.
     * @param batch
     */
    @Override
    public void dropDownward(float deltaTime, SpriteBatch batch) {
        makeDiagonal(deltaTime);
        update(deltaTime);
        draw(batch);
        if(drawingRectangle.y < 0){
            // texture.dispose();
        }
        else{
            if(isFinished()){
                animation = GetAnimation(texture);
                // drawingRectangle.y = WORLD_HEIGHT;
            }
            if(drawingRectangle.y < -50){
//            drawingRectangle.y = WORLD_HEIGHT;
                texture.dispose();
            }
        }
    }

    /** make the coordinates of the object go down the height of the screen
     * @param deltaTime The time in seconds since the last render.
     */
    public void makeDiagonal(float deltaTime){
        drawingRectangle.y -= movementSpeed * deltaTime;
        drawingRectangle.x -= movementSpeed * 0.2 * deltaTime;
    }

    /**
     *
     * @param otherRectangle check if this object hit by another object
     * @return true if is getting hit, false if not.
     */
    public boolean intersects(Rectangle otherRectangle){
        return this.drawingRectangle.overlaps(otherRectangle);
    }

    /** This check if the object is getting destroyed, if not, decreases its HP
     *
     * @return true if is getting destroyed, false if not.
     */
    public boolean hitAndCheckDestroyed() {
        if(HP > 0){
            HP--;
            return false;
        }
        return true;
    }

    //region Getter and Setter

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

    public Integer getHP() {
        return HP;
    }

    public void setHP(Integer HP) {
        this.HP = HP;
    }

    public Boolean getDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(Boolean destroyed) {
        isDestroyed = destroyed;
    }

    //endRegion

}


