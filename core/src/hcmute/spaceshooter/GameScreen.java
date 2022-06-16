package hcmute.spaceshooter;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;
import static hcmute.spaceshooter.GlobalVariables.background;
import static hcmute.spaceshooter.GlobalVariables.textureAtlas;

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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Stack;

import hcmute.spaceshooter.Animation.Explosion;
import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Ships.EnemyBossShip;
import hcmute.spaceshooter.Ships.EnemyShipTypeA;
import hcmute.spaceshooter.Ships.EnemyShipTypeB;
import hcmute.spaceshooter.Ships.EnemyShipTypeC;
import hcmute.spaceshooter.Ships.EnemyShipTypeD;
import hcmute.spaceshooter.Ships.EnemyShipTypeE;
import hcmute.spaceshooter.SoundEffect.ExplosionSoundEffect;
import hcmute.spaceshooter.Episode.Episode;
import hcmute.spaceshooter.Lasers.ILaser;
import hcmute.spaceshooter.Ships.EnemyShip;
import hcmute.spaceshooter.Ships.PlayerShip;

public class GameScreen implements Screen {

    //screen Base class for OrthographicCamera and PerspectiveCamera.
    private Camera camera;
    // Manages a Camera and determines how world coordinates are mapped to and from the screen.
    private Viewport viewport;

    //graphics
    // Draws batched quads using indices.
    private SpriteBatch batch;
    // explosion graphic Texture
    private Texture explosionTexture;
    // height of background in World units
//    private float backgroundHeight;
    /* TextTure region For:
    Player Ship, Player Shield, Player Laser, Enemy Ship, Enemy Shield, Enemy Laser */
    private TextureRegion playerShipTextureRegion, playerShieldTextureRegion,
            enemyShip_drone1_TextureRegion, enemyShip_battleShip1_TextureRegion,
            enemyShieldTextureRegion;

    //timing
    /* An Array of backgroundOffSet for each background texture
    A BackgroundOffSet determine how much downward a background go down at that specific deltaTime and multiply by the "backgroundMaxScrollingSpeed"*/
    private float backgroundOffSet = 0;
    //backgrounds indexing
    int index = 0;

    // Max Background Scrolling Speed, Specified at the start of the program
    private float backgroundMaxScrollingSpeed;

    // The time to spawn new enemy ships
    private float timeBetweenEnemySpawns = 5f;
    private float enemySpawnTimer = 0;

    //
    private final float TOUCH_MOVEMENT_THRESHOLD = 5f;

    // game objects

    // Player Ship
    private PlayerShip playerShip;

    // List of Enemy Ships
    private Stack<EnemyShip> enemyShipList;
    // List of Enemy Bosses
    private Stack<EnemyBossShip> enemyBossesList;
    // List of enemy fired Lasers
    private Stack<IEnemyLaser> enemyLaserList;
    // List of enemy bosses' fired Lasers
    private Stack<IEnemyLaser> enemyBossLaserList;
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

    // Upgrade Dropping List:
    Stack<IDropDownAnimation> upgradeDroppingItemList = new Stack<>();
    private Stack<Meteor> meteorList = new Stack<>();
    // Get current time:
    long startTime = TimeUtils.millis();
    // Get time elapsed since startTime:
    long elapsedTime = TimeUtils.timeSinceMillis(startTime);

    //
    Episode episode;

    // Main Constructor.
    public GameScreen() {
        // Set Up Screen
        camera = new OrthographicCamera();

        //  Set the View Port with the WORLD_WIDTH, WORLD_HEIGHT, and the OrthographicCamera
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //setting up the backgrounds


        //backgroundHeight = WORLD_HEIGHT * 2;

        // A background with backgroundOffSet = backgroundMaxScrollingSpeed will make the background done rendering the whole Height of the Screen for 4 second
        backgroundMaxScrollingSpeed = (float) WORLD_HEIGHT / 4;

        // initialize texture regions for animation
        // PlayerShip And Enemy Ship
        playerShipTextureRegion = textureAtlas.findRegion("player_ship");
        enemyShip_drone1_TextureRegion = textureAtlas.findRegion("explosive_drone");
        enemyShip_battleShip1_TextureRegion = textureAtlas.findRegion("enemy_ship01");
        //PlayerShield And EnemyShield
        playerShieldTextureRegion = textureAtlas.findRegion("shield2");
        enemyShieldTextureRegion = textureAtlas.findRegion("shield1");
        enemyShieldTextureRegion.flip(false, true);

        // Explosion
        explosionTexture = new Texture("explosion.png");

        // set up game objects
        playerShip = new PlayerShip(WORLD_WIDTH / 2, WORLD_HEIGHT / 4,
                10, 10, 100, 3, 0.5f,
                playerShipTextureRegion, playerShieldTextureRegion, true, 3);

//        enemyShip = new EnemyShip(WORLD_WIDTH / 2, WORLD_HEIGHT * 3 / 4,
//                10, 10,
//                50, 1,
//                0.3f, 5, 30, 1f,
//                enemyShipTextureRegion, enemyShieldTextureRegion, enemyLaserTextureRegion);

        // Initialize Lists
        enemyShipList = new Stack<>();
        enemyLaserList = new Stack<>();
        explosionList = new LinkedList<>();
        enemyBossesList = new Stack<>();
        enemyBossLaserList = new Stack<>();
        batch = new SpriteBatch();

        // Render HUD(score, life, shields)
        prepareHud();

        episode = new Episode(upgradeDroppingItemList, meteorList, enemyBossLaserList, enemyBossesList);
    }

    /**
     * Called when the screen should render itself.
     *
     * @param deltaTime The time in seconds since the last render.
     */

    @Override
    public void render(float deltaTime) {
        /*
            Sets up the Batch for drawing.
            This will disable depth buffer writing.
            It enables blending and texturing.
         */
        batch.enableBlending();
        batch.begin();
        // Get The Input Of User
        detectInput(deltaTime);
        timeSpan += deltaTime;
        /** Renders scrolling background, this should be the first graphic method to be called
         * Otherwise, other graphics will be overwritten
         */
        renderBackground(deltaTime);
        //
        episode.DropUpgrade(deltaTime, startTime, batch);
        //
        checkGetUpgrades();
        checkCrashing();

        // player ship
        playerShip.drawShip(batch);
        playerShip.countInvincibleTime(deltaTime);
        // detect collisions between lasers and ships
        try {
            detectCollisions(deltaTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        detectBossCollisions(deltaTime);
        // explosions
        updateAndRenderExplosions(deltaTime);

        // spawnEnemyShips
        spawnEnemyShips(deltaTime);

        // spawn Boss
        episode.SpawnBoss1(deltaTime, batch);

        //lasers
        renderLasers(deltaTime);
        removeEnemyShipsAtBounds();
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
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCentreX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2Y = hudRow1Y - hudVerticalMargin - font.getCapHeight();
        hudSectionWidth = WORLD_WIDTH / 3;
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
        font.draw(batch, String.format(Locale.getDefault(), "%02d", playerShip.getHP()), hudRightX, hudRow2Y, hudSectionWidth, Align.right, false);
    }

    private void moveEnemy(EnemyShip enemyShip, float deltaTime) {
        // strategy: determine the max distance the ship can move
        float leftLimit, rightLimit, upLimit, downLimit;
        leftLimit = -enemyShip.getBoundingBox().x;
        rightLimit = WORLD_WIDTH - enemyShip.getBoundingBox().x - enemyShip.getBoundingBox().width;
        downLimit = (float) WORLD_HEIGHT / 2 - enemyShip.getBoundingBox().y;
        downLimit = - WORLD_HEIGHT;
        upLimit = WORLD_HEIGHT - enemyShip.getBoundingBox().y - enemyShip.getBoundingBox().height;

        float xMove = enemyShip.getDirectionVector().x * enemyShip.getMovementSpeed() * deltaTime;
        float yMove = enemyShip.getDirectionVector().y * enemyShip.getMovementSpeed() * deltaTime;

        if(xMove > 0){
            xMove = Math.min(xMove, rightLimit);
            enemyShip.translate(xMove, yMove);
        }
        else{
            xMove = Math.max(xMove, leftLimit);
            enemyShip.translate(xMove, yMove);
        }

        if(yMove > 0){
            yMove = Math.min(yMove, upLimit);
            enemyShip.translate(xMove, yMove);
        }

    }
    private void spawnEnemyShips(float deltaTime){
        if(((System.currentTimeMillis() - startTime) / 1000) < 30){
            enemySpawnTimer += deltaTime;
            if(enemySpawnTimer > timeBetweenEnemySpawns){
                for(int i = 0; i < 2; i++){

                    enemyShipList.add(new EnemyShipTypeA());
                    enemySpawnTimer -= timeBetweenEnemySpawns;
                }
                for(int j = 0; j < 1; j++){
                    enemyShipList.add( new EnemyShipTypeB());
                    enemySpawnTimer -= timeBetweenEnemySpawns;
                }
                for(int j = 0; j < 1; j++){
                    enemyShipList.add( new EnemyShipTypeC());
                    enemySpawnTimer -= timeBetweenEnemySpawns;
                }
                for(int j = 0; j < 1; j++){
                    enemyShipList.add( new EnemyShipTypeD());
                    enemySpawnTimer -= timeBetweenEnemySpawns;
                }
                for(int j = 0; j < 1; j++){
                    enemyShipList.add( new EnemyShipTypeE());
                    enemySpawnTimer -= timeBetweenEnemySpawns;
                }
                enemySpawnTimer = 0;
            }
            ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();

            while(enemyShipListIterator.hasNext()){
                // enemy ships
                EnemyShip enemyShip = enemyShipListIterator.next();
                moveEnemy(enemyShip, deltaTime);
                enemyShip.MoveRandomly(deltaTime);
                enemyShip.drawShip(batch);

            }
        }
        else{
            enemyShipList.clear();
        }
    }
    private void detectInput(float deltaTime) {

        //keyboard input

        // strategy: determine the max distance the ship can move

        // check each key that matters and move accordingly

        float leftLimit, rightLimit, upLimit, downLimit;
        leftLimit = -playerShip.getBoundingBox().x;
        rightLimit = WORLD_WIDTH - playerShip.getBoundingBox().x - playerShip.getBoundingBox().width;
        downLimit = -playerShip.getBoundingBox().y;
        upLimit = (float) WORLD_HEIGHT - playerShip.getBoundingBox().y - playerShip.getBoundingBox().height;

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
    private void detectBossCollisions(float deltaTime){
        ListIterator<ILaser> laserListIterator;

        //For each player's laser, check whether it intersects an enemy ship
        laserListIterator = playerShip.getLaserList().listIterator();
        while (laserListIterator.hasNext()) {
            ILaser laser = laserListIterator.next();
            ListIterator<EnemyBossShip> enemyShipListIterator = enemyBossesList.listIterator();
            while(enemyShipListIterator.hasNext()){
                EnemyBossShip boss = enemyShipListIterator.next();
                if(boss != null){
                    if(boss.intersects(laser.getLaserBoundingBox())){
                        // contact with enemy ship
                        if(boss.hitAndCheckDestroyed()){
                            ExplosionSoundEffect.bigSoundEffect();

                            enemyShipListIterator.remove();
                            Explosion explosion = new Explosion(explosionTexture, new Rectangle(laser.getLaserBoundingBox()), 1f);
                            explosion.getBoundingBox().setWidth(explosion.getBoundingBox().getWidth() + 5);
                            explosion.getBoundingBox().setHeight(explosion.getBoundingBox().getHeight() +5);
                            explosionList.add(explosion);
                            score += 100;

                        }
                        else {
                            ExplosionSoundEffect.smallSoundEffect();

                            Explosion smallExplosion = new Explosion(explosionTexture, new Rectangle(laser.getLaserBoundingBox()), 0.2f);
                            smallExplosion.getBoundingBox().setWidth(smallExplosion.getBoundingBox().getWidth() - 10);
                            smallExplosion.getBoundingBox().setHeight(smallExplosion.getBoundingBox().getHeight() - 10);
                            smallExplosion.getBoundingBox().setX(smallExplosion.getBoundingBox().getX() + 10);
                            smallExplosion.getBoundingBox().setY(smallExplosion.getBoundingBox().getY() + 10);
                            explosionList.add(smallExplosion);
                        }
                        laserListIterator.remove();
                        break;
                    }
                }
            }
        }

        ListIterator<IEnemyLaser> enemyLaserListIterator;
        // For each enemies' laser, check whether it intersects an player ship
        enemyLaserListIterator = enemyBossLaserList.listIterator();

        while (enemyLaserListIterator.hasNext()) {
            IEnemyLaser laser = enemyLaserListIterator.next();
            if(playerShip.intersects(laser.getLaserBoundingBox())){
                if(playerShip.isInvincible() == false){
                    playerShip.setStartCounterInvincibleTime(true);
                    playerShip.setInvincible(true);
                    if(playerShip.hitAndCheckDestroyed()){
                        ExplosionSoundEffect.smallSoundEffect();
                        Rectangle rectangle = new Rectangle(playerShip.getBoundingBox());
                        Explosion smallExplosion = new Explosion(explosionTexture, rectangle, 0.5f);
                        smallExplosion.getBoundingBox().setWidth(smallExplosion.getBoundingBox().getWidth());
                        smallExplosion.getBoundingBox().setHeight(smallExplosion.getBoundingBox().getHeight());
                        smallExplosion.getBoundingBox().setX(smallExplosion.getBoundingBox().getX() - 5);
                        smallExplosion.getBoundingBox().setY(smallExplosion.getBoundingBox().getY() - 10);
                        explosionList.add(smallExplosion);
                        break;
                    }
                }
               else{
                    if(playerShip.isFinishInvincible() == true){
                        playerShip.setStartCounterInvincibleTime(false);
                        playerShip.setInvincibleTimer(0);
                        playerShip.setInvincible(false);
                    }

                }
            }

        }
    }
    private void detectCollisions(float deltaTime){
        ListIterator<ILaser> laserListIterator;

        //For each player's laser, check whether it intersects an enemy ship
        laserListIterator = playerShip.getLaserList().listIterator();
        while (laserListIterator.hasNext()) {
            ILaser laser = laserListIterator.next();
            ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
            while(enemyShipListIterator.hasNext()){
                EnemyShip enemyShip = enemyShipListIterator.next();
                if(enemyShip != null){
                    if(enemyShip.intersects(laser.getLaserBoundingBox())){
                        // contact with enemy ship
                        if(enemyShip.hitAndCheckDestroyed()){
                            ExplosionSoundEffect.bigSoundEffect();

                            enemyShipListIterator.remove();
                            Explosion explosion = new Explosion(explosionTexture, new Rectangle(laser.getLaserBoundingBox()), 1f);
                            explosion.getBoundingBox().setWidth(explosion.getBoundingBox().getWidth() + 5);
                            explosion.getBoundingBox().setHeight(explosion.getBoundingBox().getHeight() +5);
                            explosionList.add(explosion);
                            score += 100;

                        }
                        else {
                            ExplosionSoundEffect.smallSoundEffect();

                            Explosion smallExplosion = new Explosion(explosionTexture, new Rectangle(laser.getLaserBoundingBox()), 0.2f);
                            smallExplosion.getBoundingBox().setWidth(smallExplosion.getBoundingBox().getWidth() - 10);
                            smallExplosion.getBoundingBox().setHeight(smallExplosion.getBoundingBox().getHeight() - 10);
                            smallExplosion.getBoundingBox().setX(smallExplosion.getBoundingBox().getX() + 10);
                            smallExplosion.getBoundingBox().setY(smallExplosion.getBoundingBox().getY() + 10);
                            explosionList.add(smallExplosion);
                        }
                        laserListIterator.remove();
                        break;
                    }
                }

            }

        }

        //For each player's laser, check whether it intersects a meteor
        laserListIterator = playerShip.getLaserList().listIterator();
        while (laserListIterator.hasNext()) {
            ILaser laser = laserListIterator.next();
            ListIterator<Meteor> meteorListIterator = meteorList.listIterator();
            while(meteorListIterator.hasNext()){
                Meteor meteor = meteorListIterator.next();
                if(meteor != null){
                    if(meteor.intersects(laser.getLaserBoundingBox())){
                        Rectangle meteorRectangle = new Rectangle(meteor.getDrawingRectangle());
                        // contact with enemy ship
                        if(meteor.hitAndCheckDestroyed()){
                            ExplosionSoundEffect.bigSoundEffect();

                            meteor.setDestroyed(true);
                            meteorListIterator.remove();
                            Explosion explosion = new Explosion(explosionTexture, meteorRectangle, 1f);
                            explosion.getBoundingBox().setX(explosion.getBoundingBox().getX() - 5);
                            explosion.getBoundingBox().setY(explosion.getBoundingBox().getY() - 10);
                            explosion.getBoundingBox().setWidth(explosion.getBoundingBox().getWidth() + 5);
                            explosion.getBoundingBox().setHeight(explosion.getBoundingBox().getHeight() +5);
                            explosionList.add(explosion);
                            score += 100;

                        }
                        else {
                            ExplosionSoundEffect.smallSoundEffect();

                            Explosion smallExplosion = new Explosion(explosionTexture, meteorRectangle, 0.5f);
                            smallExplosion.getBoundingBox().setWidth(smallExplosion.getBoundingBox().getWidth());
                            smallExplosion.getBoundingBox().setHeight(smallExplosion.getBoundingBox().getHeight());
                            smallExplosion.getBoundingBox().setX(smallExplosion.getBoundingBox().getX() - 5);
                            smallExplosion.getBoundingBox().setY(smallExplosion.getBoundingBox().getY() - 10);
                            explosionList.add(smallExplosion);

                        }
                        laserListIterator.remove();
                        break;
                    }
                }

            }

        }

        // For each enemies' laser, check whether it intersects player ship
        ListIterator<IEnemyLaser> enemyLaserListIterator;
        enemyLaserListIterator = enemyLaserList.listIterator();

        while (enemyLaserListIterator.hasNext()) {
            IEnemyLaser laser = enemyLaserListIterator.next();
            if(playerShip.intersects(laser.getLaserBoundingBox())){

                if(playerShip.isInvincible() == false){

                    playerShip.setStartCounterInvincibleTime(true);
                    playerShip.setInvincible(true);
                    if(playerShip.hitAndCheckDestroyed()){
                        ExplosionSoundEffect.smallSoundEffect();
                        Rectangle rectangle = new Rectangle(laser.getLaserBoundingBox());
                        Explosion smallExplosion = new Explosion(explosionTexture, rectangle, 0.5f);
                        smallExplosion.getBoundingBox().setWidth(smallExplosion.getBoundingBox().getWidth());
                        smallExplosion.getBoundingBox().setHeight(smallExplosion.getBoundingBox().getHeight());
                        smallExplosion.getBoundingBox().setX(smallExplosion.getBoundingBox().getX() - 5);
                        smallExplosion.getBoundingBox().setY(smallExplosion.getBoundingBox().getY() - 10);
                        explosionList.add(smallExplosion);
                        break;
                    }
                    else{
                        // player ship destroyed
                    }
                    enemyLaserListIterator.remove();

                }
                else{
                    if(playerShip.isFinishInvincible() == true){
                        playerShip.setStartCounterInvincibleTime(false);
                        playerShip.setInvincibleTimer(0);
                        playerShip.setInvincible(false);
                    }
                        ExplosionSoundEffect.smallSoundEffect();
                        Rectangle rectangle = new Rectangle(laser.getLaserBoundingBox());
                        Explosion smallExplosion = new Explosion(explosionTexture, rectangle, 0.5f);
                        smallExplosion.getBoundingBox().setWidth(smallExplosion.getBoundingBox().getWidth());
                        smallExplosion.getBoundingBox().setHeight(smallExplosion.getBoundingBox().getHeight());
                        smallExplosion.getBoundingBox().setX(smallExplosion.getBoundingBox().getX() - 5);
                        smallExplosion.getBoundingBox().setY(smallExplosion.getBoundingBox().getY() - 10);
                        explosionList.add(smallExplosion);
                        enemyLaserListIterator.remove();
                }
            }

        }


    }
    private void checkGetUpgrades(){
        // Check the Upgrade Items are collected by player's ship
        ListIterator<IDropDownAnimation> itemsIterator = upgradeDroppingItemList.listIterator();
        while (itemsIterator.hasNext()) {
            IDropDownAnimation item = itemsIterator.next();
            if(playerShip.intersects(item.getDrawingRectangle())){
                    item.setTaken(true);
                    playerShip.upgrade(item);
                    itemsIterator.remove();
                }
            }
    }


    private void checkCrashing(){
        // Check the Meteors crash to the player's ship
        ListIterator<Meteor> itemsIterator = meteorList.listIterator();
        while (itemsIterator.hasNext()) {
            Meteor item = itemsIterator.next();
            if(playerShip.intersects(item.getDrawingRectangle())){
                item.setTaken(true);
                Explosion smallExplosion = new Explosion(explosionTexture, new Rectangle(playerShip.getBoundingBox()), 0.5f);
                explosionList.add(smallExplosion);
                ExplosionSoundEffect.bigSoundEffect();
                itemsIterator.remove();
                if(playerShip.isFinishInvincible() == false){
                    playerShip.setStartCounterInvincibleTime(true);
                    playerShip.setInvincible(true);
                    playerShip.hitAndCheckDestroyed();
                }
                else{
                    if(playerShip.isFinishInvincible() == true){
                        playerShip.setStartCounterInvincibleTime(false);
                        playerShip.setInvincibleTimer(0);
                        playerShip.setInvincible(false);
                    }
                }
            }
        }
        // Check the Enemy ships crash to the player's ship
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
        while (enemyShipListIterator.hasNext()) {
            EnemyShip ship = enemyShipListIterator.next();
            if(playerShip.intersects(ship.getBoundingBox())){
                Explosion smallExplosion = new Explosion(explosionTexture, new Rectangle(playerShip.getBoundingBox()), 0.5f);
                explosionList.add(smallExplosion);
                ExplosionSoundEffect.bigSoundEffect();
                enemyShipListIterator.remove();
                if(playerShip.isInvincible() == false){
                    playerShip.setStartCounterInvincibleTime(true);
                    playerShip.setInvincible(true);
                    playerShip.hitAndCheckDestroyed();
                }
                else{
                    if(playerShip.isFinishInvincible() == true){
                        playerShip.setStartCounterInvincibleTime(false);
                        playerShip.setInvincibleTimer(0);
                        playerShip.setInvincible(false);
                    }
                }
            }
        }
        // Check the Enemy ships crash to the player's ship
        ListIterator<EnemyBossShip> enemyBossShipListIterator = enemyBossesList.listIterator();
        while (enemyBossShipListIterator.hasNext()) {
            EnemyBossShip ship = enemyBossShipListIterator.next();
            if(playerShip.intersects(ship.getBoundingBox())){

                if(playerShip.isInvincible() == false){
                    Explosion smallExplosion = new Explosion(explosionTexture, new Rectangle(playerShip.getBoundingBox()), 0.5f);
                    explosionList.add(smallExplosion);
                    ExplosionSoundEffect.bigSoundEffect();
                    playerShip.setStartCounterInvincibleTime(true);
                    playerShip.setInvincible(true);
                    playerShip.hitAndCheckDestroyed();
                }
                else{
                    if(playerShip.isFinishInvincible() == true){
                        playerShip.setStartCounterInvincibleTime(false);
                        playerShip.setInvincibleTimer(0);
                        playerShip.setInvincible(false);
                    }
                }
            }
        }
    }
    private void removeEnemyShipsAtBounds(){
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
        while (enemyShipListIterator.hasNext()){
            EnemyShip enemyShip = enemyShipListIterator.next();
            if(enemyShip.getBoundingBox().getY() <= 0){
                enemyShipListIterator.remove();
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
            playerShip.GetLasers();
        }
        playerShip.DrawAndRemoveBullets(deltaTime, batch, WORLD_HEIGHT);
        // Update the status of the ship every deltaTime
        playerShip.update(deltaTime);

        //enemy lasers
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();
        while (enemyShipListIterator.hasNext()) {
            EnemyShip enemyShip = enemyShipListIterator.next();
            enemyShip.update(deltaTime);
            if (enemyShip.canFireLaser() && enemyShip.isAbleToFire()){
                for(IEnemyLaser laser: enemyShip.GetLasers()){
                    enemyLaserList.push(laser);
                }
            }
        }
        DrawAndRemoveEnemyBulletsIfAtBound(deltaTime);
    }

    public void DrawAndRemoveEnemyBulletsIfAtBound(float deltaTime){
        if(!enemyLaserList.isEmpty()){
            ListIterator<IEnemyLaser> iterator = enemyLaserList.listIterator();
            while (iterator.hasNext()) {
                IEnemyLaser laser = iterator.next();
                if(laser != null){
                    if (laser.getLaserBoundingBox().getY() + laser.getLaserBoundingBox().getHeight() < (-50)) {
                        iterator.remove();
                    }
                    else{
                        laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() - laser.getLaserMovementSpeed() * deltaTime);
                        laser.drawLaser(batch);
                    }
                }
            }
        }
    }

    /**
     * Called when the screen should render itself.
     * Render the backgrounds
     * @param deltaTime The time in seconds since the last render.
     */
    private void renderBackground(float deltaTime) {

        // The backgroundOffSet determine how far to the bottom a layer is placed.
        backgroundOffSet++;

        // Scrolling background
        if(backgroundOffSet % WORLD_HEIGHT == 0){
            backgroundOffSet = 0;
        }

        batch.draw(background, 0, -backgroundOffSet, WORLD_WIDTH, WORLD_HEIGHT);
        batch.draw(background, 0, -backgroundOffSet + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
    }

    /**
     * @param width
     * @param height
     * @see ApplicationListener#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     */
    @Override
    public void hide() {

    }
    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    @Override
    public void show() {

    }
    /**
     * Called when this screen should release all resources.
     */
    @Override
    public void dispose() {

    }
}
