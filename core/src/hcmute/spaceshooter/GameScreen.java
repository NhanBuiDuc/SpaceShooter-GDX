package hcmute.spaceshooter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;

import hcmute.spaceshooter.Animation.Explosion;
import hcmute.spaceshooter.Animation.UpgradeTypeA;
import hcmute.spaceshooter.Lasers.Laser;
import hcmute.spaceshooter.Ships.EnemyShip;
import hcmute.spaceshooter.Ships.PlayerShip;

public class GameScreen implements Screen {

    //screen Base class for OrthographicCamera and PerspectiveCamera.
    private Camera camera;
    // Manages a Camera and determines how world coordinates are mapped to and from the screen.
    private Viewport viewport;

    //graphics

    // Draws batched quads using indices.
    public  final SpriteBatch batch;

    /* Loads images from texture atlases created by TexturePacker.
    A TextureAtlas must be disposed to free up the resources consumed by the backing textures.*/
    private TextureAtlas textureAtlas;

    // explosion graphic Texture
    private Texture explosionTexture;

    /* Backgrounds Texture Array . Each item defines a rectangular area of a texture.
    The coordinate system used has its origin in the upper left corner with the x-axis pointing to the right and the y axis pointing downwards.*/
    private TextureRegion[] backgrounds;

//    height of background in World units
//    private float backgroundHeight;

    /* TextTure region For:
    Player Ship, Player Shield, Player Laser, Enemy Ship, Enemy Shield, Enemy Laser */
    private TextureRegion playerShipTextureRegion, playerShieldTextureRegion,
            enemyShip_drone1_TextureRegion, enemyShip_battleShip1_TextureRegion,
            enemyShieldTextureRegion;
    //timing

    /* An Array of backgroundOffSet for each background texture
    A BackgroundOffSet determine how much downward a background go down at that specific deltaTime and multiply by the "backgroundMaxScrollingSpeed"*/
    private float[] backgroundOffSets = {0,0,0,0};

    // Max Background Scrolling Speed, Specified at the start of the program
    private float backgroundMaxScrollingSpeed;

    // The time to spawn new enemy ships
    private float timeBetweenEnemySpawns = 3f;
    private float enemySpawnTimer = 0;

    // world parameters



    //
    private final float TOUCH_MOVEMENT_THRESHOLD = 5f;

    // game objects

    // Player Ship
    private PlayerShip playerShip;

    // List of Enemy Ships
    private LinkedList<EnemyShip> enemyShipList;


    // List of enemy fired Lasers
    private LinkedList<Laser> enemyLaserList;

    // List of Explosion
    private LinkedList<Explosion> explosionList;

    // Player's score
    private int score = 0;

    // Heads-Up Display

    // Displaying Text Font
    BitmapFont font;

    // The margin, coordinate of the HUDs. A table-like type of display.
    float hudVerticalMargin, hudLeftX, hudRightX, hudCentreX, hudRow1Y, hudRow2Y, hudSectionWidth;

    /**
     *  The real time span from the beginning of the program
     */
    float timeSpan;
    // Main Constructor.
    public GameScreen() {
        // Set Up Screen
        camera = new OrthographicCamera();

        //  Set the View Port with the WORLD_WIDTH, WORLD_HEIGHT, and the OrthographicCamera
        viewport = new StretchViewport(SpaceShooterGame.WORLD_WIDTH, SpaceShooterGame.WORLD_HEIGHT, camera);

        //set up the texture atlas from the file assets/image.atlas.
        textureAtlas = new TextureAtlas("images.atlas");

        //setting up the backgrounds
        backgrounds = new TextureRegion[4];
        backgrounds[0] = textureAtlas.findRegion("Starscape00");
        backgrounds[1] = textureAtlas.findRegion("Starscape01");
        backgrounds[2] = textureAtlas.findRegion("Starscape02");
        backgrounds[3] = textureAtlas.findRegion("Starscape03");

        //backgroundHeight = WORLD_HEIGHT * 2;

        // A background with backgroundOffSet = backgroundMaxScrollingSpeed will make the background done rendering the whole Height of the Screen for 4 second
        backgroundMaxScrollingSpeed = (float) SpaceShooterGame.WORLD_HEIGHT / 4;

        // initialize texture regions for animation
        // PlayerShip And Enemy Ship
        playerShipTextureRegion = textureAtlas.findRegion("playerShip2_blue");
        enemyShip_drone1_TextureRegion = textureAtlas.findRegion("small.drone");
        enemyShip_battleShip1_TextureRegion = textureAtlas.findRegion("enemyBlack1");
        //PlayerShield And EnemyShield
        playerShieldTextureRegion = textureAtlas.findRegion("shield2");
        enemyShieldTextureRegion = textureAtlas.findRegion("shield1");
        enemyShieldTextureRegion.flip(false, true);

        // Explosion
        explosionTexture = new Texture("explosion.png");

        // set up game objects
        playerShip = new PlayerShip(SpaceShooterGame.WORLD_WIDTH / 2, SpaceShooterGame.WORLD_HEIGHT / 4,
                10, 10, 50, 3, 0.4f,
                playerShipTextureRegion, playerShieldTextureRegion);

//        enemyShip = new EnemyShip(WORLD_WIDTH / 2, WORLD_HEIGHT * 3 / 4,
//                10, 10,
//                50, 1,
//                0.3f, 5, 30, 1f,
//                enemyShipTextureRegion, enemyShieldTextureRegion, enemyLaserTextureRegion);

        // Initialize Lists
        enemyShipList = new LinkedList<>();
        enemyLaserList = new LinkedList<>();
        enemyLaserList = new LinkedList<>();
        explosionList = new LinkedList<>();

        batch = new SpriteBatch();

        // Render HUD(score, life, shields)
        prepareHud();
    }
    /**
     * Called when the screen should render itself.
     *
     * @param deltaTime The time in seconds since the last render.
     */

    UpgradeTypeA upgradeTypeA = new UpgradeTypeA(SpaceShooterGame.WORLD_WIDTH, SpaceShooterGame.WORLD_HEIGHT);

    @Override
    public void render(float deltaTime) {
        /*
            Sets up the Batch for drawing.
            This will disable depth buffer writing.
            It enables blending and texturing.
         */
        batch.begin();



        /** Renders scrolling background, this should be the first graphic method to be called
         * Otherwise, other graphics will be overwritten
         */
        renderBackground(deltaTime);

        //
        DropUpgrade(deltaTime);

        timeSpan += deltaTime;


        // Get The Input Of User
        detectInput(deltaTime);

        //
        spawnEnemyShips(deltaTime);

        //
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();

        while(enemyShipListIterator.hasNext()){
            // enemy ships
            EnemyShip enemyShip = enemyShipListIterator.next();
            moveEnemy(enemyShip, deltaTime);
            enemyShip.MoveRandomly(deltaTime);
            enemyShip.draw(batch);
        }

        // player ship
        playerShip.draw(batch);

        //lasers
        renderLasers(deltaTime);

        // detect collisions between lasers and ships
        detectCollisions();

        // explosions
        updateAndRenderExplosions(deltaTime);

        // hud rendering
        updateAndRenderHUD(deltaTime);

        batch.end();
    }

    // Render HUD(score, life, shields) for the main screen

    private void prepareHud() {
        //Create a BitmapFont from out font File
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeOfTheGalaxyRegular-OVEa6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 72;
        fontParameter.borderWidth = 3.6f;
        fontParameter.color = new Color(1, 1, 1, 0.3f);
        fontParameter.borderColor = new Color(0, 0, 0, 0.3f);

        font = fontGenerator.generateFont(fontParameter);
        // Scale the font to fit world
        font.getData().setScale(0.08f);

        // Calculate hud margins, etc.
        hudVerticalMargin = font.getCapHeight() / 2;
        hudLeftX = hudVerticalMargin;
        hudRightX = SpaceShooterGame.WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCentreX = SpaceShooterGame.WORLD_WIDTH / 3;
        hudRow1Y = SpaceShooterGame.WORLD_HEIGHT - hudVerticalMargin;
        hudRow2Y = hudRow1Y - hudVerticalMargin - font.getCapHeight();
        hudSectionWidth = SpaceShooterGame.WORLD_WIDTH / 3;
    }

    //
    private void updateAndRenderHUD(float deltaTime) {
        // render Top row labels
        font.draw(batch, "Score", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false);
        font.draw(batch, "Shield", hudCentreX, hudRow1Y, hudSectionWidth, Align.center, false);
        font.draw(batch, "Lives", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false);

        // render second row values
        font.draw(batch, String.format(Locale.getDefault(), "%06d", score), hudLeftX, hudRow2Y, hudSectionWidth, Align.left, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", playerShip.shield), hudCentreX, hudRow2Y, hudSectionWidth, Align.center, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", playerShip.getLives()), hudRightX, hudRow2Y, hudSectionWidth, Align.right, false);
    }
    private void DropUpgrade(float deltaTime){

        upgradeTypeA.dropDownward(deltaTime, batch);
//        if(timeSpan >= 5 && timeSpan <= upgradeTypeA.getTotalAnimationTime()){
//
//            upgradeTypeA.dropDownward(deltaTime, WORLD_WIDTH, batch);
//        }

    }
    private void moveEnemy(EnemyShip enemyShip, float deltaTime) {
        // strategy: determine the max distance the ship can move

        float leftLimit, rightLimit, upLimit, downLimit;
        leftLimit = -enemyShip.getBoundingBox().x;
        rightLimit = SpaceShooterGame.WORLD_WIDTH - enemyShip.getBoundingBox().x - enemyShip.getBoundingBox().width;
        downLimit = (float) SpaceShooterGame.WORLD_HEIGHT / 2 -enemyShip.getBoundingBox().y;
        upLimit = SpaceShooterGame.WORLD_HEIGHT - enemyShip.getBoundingBox().y - enemyShip.getBoundingBox().height;

        float xMove = enemyShip.getDirectionVector().x * enemyShip.getMovementSpeed() * deltaTime;
        float yMove = enemyShip.getDirectionVector().y * enemyShip.getMovementSpeed() * deltaTime;

        if(xMove > 0){
            xMove = Math.min(xMove, rightLimit);

        }
        else xMove = Math.max(xMove, leftLimit);

        if(yMove > 0){
            yMove = Math.min(yMove, upLimit);

        }
        else yMove = Math.max(yMove, downLimit);

        enemyShip.translate(xMove, yMove);
    }
    private void spawnEnemyShips(float deltaTime){
        enemySpawnTimer += deltaTime;
        if(enemySpawnTimer > timeBetweenEnemySpawns){
            for(int i = 0; i < 10; i++){
                enemyShipList.add( new EnemyShip(SpaceShooterGame.random.nextFloat() * (SpaceShooterGame.WORLD_WIDTH -10),
                        SpaceShooterGame.WORLD_HEIGHT - 5,
                        7, 7, 70, 0, 0.3f,
                        enemyShip_drone1_TextureRegion, enemyShieldTextureRegion));
                enemySpawnTimer -= timeBetweenEnemySpawns;
            }
            for(int j = 0; j < 5; j++){
                enemyShipList.add( new EnemyShip(SpaceShooterGame.random.nextFloat() * (SpaceShooterGame.WORLD_WIDTH -10),
                        SpaceShooterGame.WORLD_HEIGHT - 5,
                        10, 10, 50, 1, 0.3f,
                        enemyShip_battleShip1_TextureRegion, enemyShieldTextureRegion));
                enemySpawnTimer -= timeBetweenEnemySpawns;
            }
            enemySpawnTimer = 0;
        }

    }
    private void detectInput(float deltaTime) {

        //keyboard input

        // strategy: determine the max distance the ship can move

        // check each key that matters and move accordingly

        float leftLimit, rightLimit, upLimit, downLimit;
        leftLimit = -playerShip.getBoundingBox().x;
        rightLimit = SpaceShooterGame.WORLD_WIDTH - playerShip.getBoundingBox().x - playerShip.getBoundingBox().width;
        downLimit = -playerShip.getBoundingBox().y;
        upLimit = (float) SpaceShooterGame.WORLD_HEIGHT - playerShip.getBoundingBox().y - playerShip.getBoundingBox().height;

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && rightLimit > 0){
//            float xChange = playerShip.movementSpeed * deltaTime;
//            xChange = Math.min(xChange, rightLimit);
            // playerShip.translate(xChange, 0f);

            playerShip.translate(Math.min(playerShip.getMovementSpeed() * deltaTime, rightLimit), 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && upLimit > 0){
            playerShip.translate(0f, Math.min(playerShip.getMovementSpeed() * deltaTime, upLimit));

        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && leftLimit < 0){
            playerShip.translate(Math.max(-playerShip.getMovementSpeed() * deltaTime, leftLimit), 0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && downLimit < 0){
            playerShip.translate(0f, Math.max(-playerShip.getMovementSpeed() * deltaTime, downLimit));
        }

        // touch input (also mouse)
        if(Gdx.input.isTouched()){
            // get the screen position of the touch
            float xTouchPixels = Gdx.input.getX();
            float yTouchPixels = Gdx.input.getY();

            // convert to world position
            Vector2 touchPoint = new Vector2(xTouchPixels, yTouchPixels);
            touchPoint = viewport.unproject(touchPoint);

            // calculate the x and y differences
            Vector2 playerShipCentre = new Vector2(
                    playerShip.getBoundingBox().x + playerShip.getBoundingBox().width / 2,
                    playerShip.getBoundingBox().y + playerShip.getBoundingBox().height / 2);

            float touchDistance = touchPoint.dst(playerShipCentre);

            if(touchDistance > TOUCH_MOVEMENT_THRESHOLD){
                float xTouchDifference = touchPoint.x - playerShipCentre.x;
                float yTouchDifference = touchPoint.y - playerShipCentre.y;

                // scale to the maximum speed of the ship
                float xMove = xTouchDifference / touchDistance * playerShip.getMovementSpeed() * deltaTime;
                float yMove = yTouchDifference / touchDistance * playerShip.getMovementSpeed() * deltaTime;

                if(xMove > 0){
                    xMove = Math.min(xMove, rightLimit);

                }
                else xMove = Math.max(xMove, leftLimit);

                if(yMove > 0){
                    yMove = Math.min(yMove, upLimit);

                }
                else yMove = Math.max(yMove, downLimit);

                playerShip.translate(xMove, yMove);
            }

        }

    }

    private void detectCollisions() {
        // For each player laser, check whether it intersects an enemy ship
        ListIterator<Laser> laserListIterator = playerShip.getLaserList().listIterator();
        while (laserListIterator.hasNext()) {
            Laser laser = laserListIterator.next();
            ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
            while(enemyShipListIterator.hasNext()){
                EnemyShip enemyShip = enemyShipListIterator.next();
                if(enemyShip.intersects(laser.getBoundingBox())){
                    // contact with enemy ship
                    if(enemyShip.hitAndCheckDestroyed(laser)){
                        enemyShipListIterator.remove();
                        explosionList.add(new Explosion(explosionTexture,
                                new Rectangle(enemyShip.getBoundingBox()),
                                0.7f));
                        score += 100;
                    }
                    laserListIterator.remove();
                    break;
                }
            }

        }
        // For each enemy laser, check whether it intersects an player ship
        laserListIterator = enemyLaserList.listIterator();
        while (laserListIterator.hasNext()) {
            Laser laser = laserListIterator.next();
            if(playerShip.intersects(laser.getBoundingBox())){
                // contact with player ship
                if(playerShip.hitAndCheckDestroyed(laser)){
                    if(playerShip.hitAndCheckDestroyed(laser)){
                        explosionList.add(new Explosion(explosionTexture,
                                new Rectangle(playerShip.getBoundingBox()),
                                1.6f));
                        playerShip.setLives(playerShip.getLives() - 1);
                    }
                    laserListIterator.remove();
                }

            }

        }
    }

    private void updateAndRenderExplosions(float deltaTime) {
        ListIterator<Explosion> explosionListIterator = explosionList.listIterator();
        while (explosionListIterator.hasNext()){
            Explosion explosion = explosionListIterator.next();
            explosion.update(deltaTime);
            if(explosion.isFinished()){
                explosionListIterator.remove();
            }
            else{
                explosion.draw(batch);
            }
        }
    }

    private void renderLasers(float deltaTime){
        // create new lasers
        //player lasers
        if(playerShip.canFireLaser()){
            playerShip.fireLasers(deltaTime, batch, SpaceShooterGame.WORLD_HEIGHT);
        }
        playerShip.RemoveBullets(deltaTime, batch, SpaceShooterGame.WORLD_HEIGHT);
        // Update the status of the ship every deltaTime
        playerShip.update(deltaTime, batch, SpaceShooterGame.WORLD_HEIGHT);
    }
    /**
     * Called when the screen should render itself.
     * Render the backgrounds
     * @param deltaTime The time in seconds since the last render.
     */
    private void renderBackground(float deltaTime) {

        // The backgroundOffSets of each layer determine how far to the bottom a layer is placed.
        backgroundOffSets[0] += deltaTime * backgroundMaxScrollingSpeed / 8;
        backgroundOffSets[1] += deltaTime * backgroundMaxScrollingSpeed / 4;
        backgroundOffSets[2] += deltaTime * backgroundMaxScrollingSpeed / 2;
        backgroundOffSets[3] += deltaTime * backgroundMaxScrollingSpeed;

        // Render for each layer
        for (int layer = 0; layer < backgroundOffSets.length; layer++){
            if(backgroundOffSets[layer] > SpaceShooterGame.WORLD_HEIGHT){
                backgroundOffSets[layer] = 0;
            }
            batch.draw(backgrounds[layer], 0, -backgroundOffSets[layer], SpaceShooterGame.WORLD_WIDTH, SpaceShooterGame.WORLD_HEIGHT);
            batch.draw(backgrounds[layer], 0, -backgroundOffSets[layer] + SpaceShooterGame.WORLD_HEIGHT, SpaceShooterGame.WORLD_WIDTH, SpaceShooterGame.WORLD_HEIGHT);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }
    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    @Override
    public void show() {

    }
    @Override
    public void dispose() {

    }

}
