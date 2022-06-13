package hcmute.spaceshooter.Ships;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.explosionTexture;
import static hcmute.spaceshooter.GlobalVariables.explosiveDrone;
import static hcmute.spaceshooter.GlobalVariables.finalDroneExplosionTextureRegion;
import static hcmute.spaceshooter.GlobalVariables.redPowerUpTexture;
import hcmute.spaceshooter.Animation.DropDownAnimation;
import hcmute.spaceshooter.Animation.IDropDownAnimation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import hcmute.spaceshooter.Animation.DropDownAnimation;
import hcmute.spaceshooter.SpaceShooterGame;

public class ExplosiveDrone extends DropDownAnimation {
    String typeName;
    Boolean taken = false;
    TextureRegion finalExplosionTexture = finalDroneExplosionTextureRegion;
    float explosionTimer = 0;
    float explosionAnimationTime = 0.5f;
    boolean stopRendering = false;
    public ExplosiveDrone(){
        drawingRectangle = new Rectangle(WORLD_WIDTH / 2, WORLD_HEIGHT, 10, 10);
        movementSpeed = 10;
        totalAnimationTime = 10f;
        typeName = "EXPLOSIVE DRONE";
        texture = explosiveDrone;
        titleWidth = 64;
        titleHeight = 64;

        rowTextureCount = 5;
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
     * make the coordinates of the object go down the height of the screen
     *
     * @param deltaTime The time in seconds since the last render.
     * @param batch
     */
    @Override
    public void dropDownward(float deltaTime, SpriteBatch batch) {
        if(stopRendering != true){
            makeDownward(deltaTime);
            update(deltaTime);
            draw(batch);
            if(isFinished()){
                if(isExplosionFinished() == false){
                    drawFinalExplosion(batch);
                    updateExplosionTimer(deltaTime);
                }
                else {

                }
                // drawingRectangle.y = WORLD_HEIGHT;
            }
        }

    }
    public boolean isExplosionFinished() {
        if(explosionTimer < explosionAnimationTime)
        return false;
        else {
            stopRendering = true;
            return true;
        }
    }
    //    private void randomizeDirectionVector(){
//        double bearing = SpaceShooterGame.random.nextDouble() * 6.283185; // 0 to 2 * pi
//        directionVector.x = (float) Math.sin(bearing);
//        directionVector.y = (float) Math.cos(bearing);
//    }
//
//    public void MoveRandomly(float deltaTime){
//        timeSinceLastDirectionChange += deltaTime;
//        if(timeSinceLastDirectionChange > directionChangeFrequency){
//            randomizeDirectionVector();
//            timeSinceLastDirectionChange -= directionChangeFrequency;
//        }
//    }
    public void updateExplosionTimer(float deltaTime) {
        explosionTimer += deltaTime;
    }

    public void drawFinalExplosion(Batch batch) {
        batch.draw(finalExplosionTexture,
                drawingRectangle.x, drawingRectangle.y, drawingRectangle.width, drawingRectangle.height);
        this.drawingRectangle.width += 0.1;
        this.drawingRectangle.height += 0.1;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getTaken() {
        return taken;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }

    @Override
    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    public TextureRegion getFinalExplosionTexture() {
        return finalExplosionTexture;
    }

    public void setFinalExplosionTexture(TextureRegion finalExplosionTexture) {
        this.finalExplosionTexture = finalExplosionTexture;
    }

    public float getExplosionTimer() {
        return explosionTimer;
    }

    public void setExplosionTimer(float explosionTimer) {
        this.explosionTimer = explosionTimer;
    }

    public float getExplosionAnimationTime() {
        return explosionAnimationTime;
    }

    public void setExplosionAnimationTime(float explosionAnimationTime) {
        this.explosionAnimationTime = explosionAnimationTime;
    }

    public boolean isStopRendering() {
        return stopRendering;
    }

    public void setStopRendering(boolean stopRendering) {
        this.stopRendering = stopRendering;
    }
}
