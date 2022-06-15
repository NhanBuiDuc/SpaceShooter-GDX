package hcmute.spaceshooter.Lasers;

import static hcmute.spaceshooter.GlobalVariables.yellowBulletTexture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import hcmute.spaceshooter.GlobalVariables;

public class LaserTypeC extends Laser {

    int level = 1;
    LaserTypeC[] bullets;
    Rectangle shipBoundingBox;

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

    public LaserTypeC(float xCentre, float yBottom, float laserWidth, float laserHeight, float laserMovementSpeed, Texture laserTexture) {
        super(xCentre, yBottom, laserWidth, laserHeight, laserMovementSpeed, laserTexture);
        typeName = "YELLOW";
    }

    public LaserTypeC(){

    }


    public LaserTypeC(Rectangle shipBoundingBox) {
        this.shipBoundingBox = shipBoundingBox;
        typeName = "YELLOW";
        laserTexture = yellowBulletTexture;
        explosionTexture = GlobalVariables.explosionTexture;
        // laser width and height
        laserWidth = 1f;
        laserHeight = 7f;
        // laser movement speed
        laserMovementSpeed = 130;
        // position and dimensions of the laser
        // laserBoundingBox;
        // position and dimensions of the explosion
        explosionWidth = 30f;
        explosionHeight = 30f;

        /**
         * The title width and height of the given Texture
         * starting from the top left corner going to the right
         * and ending at the bottom right corner.
         */
        laserTitleWidth = 182;
        laserTitleHeight = 182;
        explosionTitleWidth = 192;
        explosionTitleHeight = 192;
        // Total time of the whole animation rendering.
        totalLaserAnimationTime = 1f;
        totalExplosionAnimationTime = 0.5f;
        laserRowTextureCount = 4;
        laserColumnTextureCount = 4;
        explosionRowTextureCount = 8;
        explosionColumnTextureCount = 5;
        /**
         *  The number of texture region after splitting the texture,
         *  equals to the number of images from the whole Texture
         */
        laserTextureNum = laserRowTextureCount * laserColumnTextureCount;
        explosionTextureNum = explosionRowTextureCount * explosionColumnTextureCount;
        // A timer increased with each update method call
        // Gdx's Animation object
        laserAnimation = GetLaserAnimation(laserTexture, laserTitleWidth, laserTitleHeight,
        laserTextureNum, laserRowTextureCount, laserColumnTextureCount);
        explosionAnimation = GetExplosionAnimation(explosionTexture, explosionTitleWidth, explosionTitleHeight,
                explosionTextureNum, explosionRowTextureCount, explosionColumnTextureCount);
    }


    public void Upgrade(){
        this.level++;
    }

    public LaserTypeC[] GetBullets(){
        LaserTypeC[] lasers = new LaserTypeC[20];
        if(level == 1)
        {
//            lasers[0] = new Laser_TypeA(shipBoundingBox.x + shipBoundingBox.width * 0.18f, shipBoundingBox.y - laserHeight,
//                    laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);
//            lasers[1] = new Laser_TypeA(shipBoundingBox.x + shipBoundingBox.width * 0.82f, shipBoundingBox.y - laserHeight,
//                    laserWidth, laserHeight, laserMovementSpeed, laserTextureRegion);

            lasers[0] = new LaserTypeC(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(laserMovementSpeed);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.025f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new LaserTypeC(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(laserMovementSpeed);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.85f,
                    lasers[1].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


            this.bullets = lasers;
        }
        if(level == 2){
            lasers[0] = new LaserTypeC(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(laserMovementSpeed);
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.025f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new LaserTypeC(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(laserMovementSpeed);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.45f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().getHeight() * 1,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


            lasers[2] = new LaserTypeC(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(laserMovementSpeed);
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.85f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));

            this.bullets = lasers;
        }
        if(level == 3){
            lasers[0] = new LaserTypeC(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(laserMovementSpeed);
            lasers[0].setMovementType("DIAGONAL_LEFT_30");
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.025f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));


            lasers[1] = new LaserTypeC(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(laserMovementSpeed);
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.45f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().getHeight() * 1,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));


            lasers[2] = new LaserTypeC(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(laserMovementSpeed);
            lasers[2].setMovementType("DIAGONAL_RIGHT_30");
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.85f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));

            this.bullets = lasers;
        }
        if(level == 4){
            // Ngoài cùng trái
            lasers[0] = new LaserTypeC(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(laserMovementSpeed);
            lasers[0].setMovementType("DIAGONAL_LEFT_30");
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.02f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.5f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));
            // Giữa
            lasers[1] = new LaserTypeC(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(laserMovementSpeed);

            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.45f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().getHeight() * 1,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));

            // Ngoài Cùng Phải
            lasers[2] = new LaserTypeC(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(laserMovementSpeed);
            lasers[2].setMovementType("DIAGONAL_RIGHT_30");
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.9f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().getHeight() * 0.5f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));

            // Thứ 2 từ trái qua
            lasers[3] = new LaserTypeC(shipBoundingBox);
            lasers[3].setLaserWidth(laserWidth);
            lasers[3].setLaserHeight(laserHeight);
            lasers[3].setLaserMovementSpeed(laserMovementSpeed);
            lasers[3].setMovementType("DIAGONAL_LEFT_60");
            lasers[3].setLaserBoundingBox(new Rectangle(lasers[3].getShipBoundingBox().getX() + lasers[3].getShipBoundingBox().getWidth() * 0.025f,
                    lasers[3].getShipBoundingBox().getY() + lasers[3].getShipBoundingBox().getHeight() * 0.7f,
                    lasers[3].getLaserWidth(), lasers[1].getLaserHeight()));

            // Thứ 2 từ phải qua
            lasers[4] = new LaserTypeC(shipBoundingBox);
            lasers[4].setLaserWidth(laserWidth);
            lasers[4].setLaserHeight(laserHeight);
            lasers[4].setLaserMovementSpeed(laserMovementSpeed);
            lasers[4].setMovementType("DIAGONAL_RIGHT_60");
            lasers[4].setLaserBoundingBox(new Rectangle(lasers[4].getShipBoundingBox().getX() + lasers[4].getShipBoundingBox().getWidth() * 0.85f,
                    lasers[4].getShipBoundingBox().getY() + lasers[4].getShipBoundingBox().getHeight() * 0.7f,
                    lasers[4].getLaserWidth(), lasers[4].getLaserHeight()));

            this.bullets = lasers;
        }
        if(level == 5){
            // Ngoài cùng trái
            lasers[0] = new LaserTypeC(shipBoundingBox);
            lasers[0].setLaserWidth(laserWidth);
            lasers[0].setLaserHeight(laserHeight);
            lasers[0].setLaserMovementSpeed(laserMovementSpeed);
            lasers[0].setMovementType("DIAGONAL_LEFT_15");
            lasers[0].setLaserBoundingBox(new Rectangle(lasers[0].getShipBoundingBox().getX() + lasers[0].getShipBoundingBox().getWidth() * 0.015f,
                    lasers[0].getShipBoundingBox().getY() + lasers[0].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[0].getLaserWidth(), lasers[0].getLaserHeight()));
            // Giữa trái
            lasers[1] = new LaserTypeC(shipBoundingBox);
            lasers[1].setLaserWidth(laserWidth);
            lasers[1].setLaserHeight(laserHeight);
            lasers[1].setLaserMovementSpeed(laserMovementSpeed);
            // lasers[1].setMovementType("DIAGONAL_LEFT_60");
            lasers[1].setLaserBoundingBox(new Rectangle(lasers[1].getShipBoundingBox().getX() + lasers[1].getShipBoundingBox().getWidth() * 0.43f,
                    lasers[1].getShipBoundingBox().getY() + lasers[1].getShipBoundingBox().getHeight() * 1,
                    lasers[1].getLaserWidth(), lasers[1].getLaserHeight()));

            // Ngoài Cùng Phải
            lasers[2] = new LaserTypeC(shipBoundingBox);
            lasers[2].setLaserWidth(laserWidth);
            lasers[2].setLaserHeight(laserHeight);
            lasers[2].setLaserMovementSpeed(laserMovementSpeed);
            lasers[2].setMovementType("DIAGONAL_RIGHT_15");
            lasers[2].setLaserBoundingBox(new Rectangle(lasers[2].getShipBoundingBox().getX() + lasers[2].getShipBoundingBox().getWidth() * 0.92f,
                    lasers[2].getShipBoundingBox().getY() + lasers[2].getShipBoundingBox().getHeight() * 0.55f,
                    lasers[2].getLaserWidth(), lasers[2].getLaserHeight()));

            // Thứ 2 từ trái qua
            lasers[3] = new LaserTypeC(shipBoundingBox);
            lasers[3].setLaserWidth(laserWidth);
            lasers[3].setLaserHeight(laserHeight);
            lasers[3].setLaserMovementSpeed(laserMovementSpeed);
            lasers[3].setMovementType("DIAGONAL_LEFT_30");

            lasers[3].setLaserBoundingBox(new Rectangle(lasers[3].getShipBoundingBox().getX() + lasers[3].getShipBoundingBox().getWidth() * 0.22f,
                    lasers[3].getShipBoundingBox().getY() + lasers[3].getShipBoundingBox().getHeight() * 0.8f,
                    lasers[3].getLaserWidth(), lasers[3].getLaserHeight()));

            // Thứ 2 từ phải qua
            lasers[4] = new LaserTypeC(shipBoundingBox);
            lasers[4].setLaserWidth(laserWidth);
            lasers[4].setLaserHeight(laserHeight);
            lasers[4].setLaserMovementSpeed(laserMovementSpeed);
            lasers[4].setMovementType("DIAGONAL_RIGHT_30");
            lasers[4].setLaserBoundingBox(new Rectangle(lasers[4].getShipBoundingBox().getX() + lasers[4].getShipBoundingBox().getWidth() * 0.7f,
                    lasers[4].getShipBoundingBox().getY() + lasers[4].getShipBoundingBox().getHeight() * 0.8f,
                    lasers[4].getLaserWidth(), lasers[4].getLaserHeight()));
            // Thứ 3 từ trái qua
            lasers[5] = new LaserTypeC(shipBoundingBox);
            lasers[5].setLaserWidth(laserWidth);
            lasers[5].setLaserHeight(laserHeight);
            lasers[5].setLaserMovementSpeed(laserMovementSpeed);
            lasers[5].setMovementType("DIAGONAL_LEFT_30");
            lasers[5].setLaserBoundingBox(new Rectangle(lasers[5].getShipBoundingBox().getX() + lasers[5].getShipBoundingBox().getWidth() * 0.43f,
                    lasers[3].getShipBoundingBox().getY() + lasers[5].getShipBoundingBox().getHeight() * 0.9f,
                    lasers[3].getLaserWidth(), lasers[5].getLaserHeight()));

            // Thứ 3 từ phải qua
            lasers[6] = new LaserTypeC(shipBoundingBox);
            lasers[6].setLaserWidth(laserWidth);
            lasers[6].setLaserHeight(laserHeight);
            lasers[6].setLaserMovementSpeed(laserMovementSpeed);
            lasers[6].setMovementType("DIAGONAL_RIGHT_30");
            lasers[6].setLaserBoundingBox(new Rectangle(lasers[6].getShipBoundingBox().getX() + lasers[6].getShipBoundingBox().getWidth() * 0.47f,
                    lasers[6].getShipBoundingBox().getY() + lasers[6].getShipBoundingBox().getHeight() * 0.9f,
                    lasers[6].getLaserWidth(), lasers[6].getLaserHeight()));
            // Giữa phải
            lasers[7] = new LaserTypeC(shipBoundingBox);
            lasers[7].setLaserWidth(laserWidth);
            lasers[7].setLaserHeight(laserHeight);
            lasers[7].setLaserMovementSpeed(laserMovementSpeed);
            lasers[7].setMovementType("DIAGONAL_RIGHT_60");
            lasers[7].setLaserBoundingBox(new Rectangle(lasers[7].getShipBoundingBox().getX() + lasers[7].getShipBoundingBox().getWidth() * 0.47f,
                    lasers[7].getShipBoundingBox().getY() + lasers[7].getShipBoundingBox().getHeight() * 1,
                    lasers[7].getLaserWidth(), lasers[1].getLaserHeight()));

            this.bullets = lasers;
        }
        return lasers;
    }

    @Override
    public void setTypename(String red) {

    }

    @Override
    public void drawLaser(Batch batch) {
        batch.draw(laserTexture,
                laserBoundingBox.x, laserBoundingBox.y, laserBoundingBox.width, laserBoundingBox.height);
    }

    @Override
    public Animation<TextureRegion> GetLaserAnimation(Texture texture, int titleWidth, int titleHeight, int textureNum, int rowTextureCount, int columnTextureCount) {
        // split texture

        textureNum -= 5;
        TextureRegion[][] textureRegion2D = TextureRegion.split(texture, titleWidth, titleHeight);

        // convert to 1D array
        TextureRegion[] textureRegion1D = new TextureRegion[textureNum];
        int index = 0;
        for(int i = 0; i < rowTextureCount; i++){
            for(int j = 0; j < columnTextureCount; j++){
                try{
                    if( (i != 0 && j != 0 ) || (i != 0 && j != 1) || (i != 3 && j != 1) || (i != 3 && j != 2) || (i != 3 && j != 3)){
                        textureRegion1D[index] = textureRegion2D[i][j];
                        index++;
                    }
                }
                catch (Exception e){

                }
            }
        }
        laserTimer = 0;

        // Frame duration = Desired Animation Time / number of images
        return new Animation<TextureRegion>(totalLaserAnimationTime / textureNum, textureRegion1D);
    }

    //region Getter and Setter


    public int getLevel() {
        return level;
    }

    @Override
    public void increaseShootingDuration(float elapsedTime) {

    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void setLaserBoundingBox(Rectangle laserBoundingBox) {
        this.laserBoundingBox = laserBoundingBox;
    }

    public LaserTypeC[] getBullets() {
        return bullets;
    }

    public void setBullets(LaserTypeC[] bullets) {
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

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }


//endregion Getter and Setter
}
