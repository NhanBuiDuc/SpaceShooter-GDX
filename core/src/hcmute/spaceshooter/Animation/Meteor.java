package hcmute.spaceshooter.Animation;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.meteorTexture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import hcmute.spaceshooter.Ships.IShip;
import hcmute.spaceshooter.Ships.Ship;

public class Meteor extends DropDownAnimation {
    String typeName;
    Boolean taken = false;
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
