package hcmute.spaceshooter.Episode;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ListIterator;
import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Animation.UpgradeTypeA;
import hcmute.spaceshooter.Animation.UpgradeTypeB;
import hcmute.spaceshooter.Animation.UpgradeTypeC;
import hcmute.spaceshooter.Animation.UpgradeTypeD;
import hcmute.spaceshooter.Animation.UpgradeTypeE;
import hcmute.spaceshooter.Lasers.Boss1_LaserTypeA;
import hcmute.spaceshooter.Lasers.Boss1_LaserTypeB;
import hcmute.spaceshooter.Lasers.Boss1_LaserTypeC;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Ships.EnemyBoss1;
import hcmute.spaceshooter.Ships.EnemyBossShip;
import hcmute.spaceshooter.Ships.EnemyShip;
import hcmute.spaceshooter.Ships.EnemyShipTypeA;
import hcmute.spaceshooter.Ships.EnemyShipTypeB;
import hcmute.spaceshooter.Ships.EnemyShipTypeC;
import hcmute.spaceshooter.Ships.EnemyShipTypeD;
import hcmute.spaceshooter.Ships.EnemyShipTypeE;
import hcmute.spaceshooter.SpaceShooterGame;

public class Episode1 implements IEpisode{ //Upgrade boxes
    //UpgradeTypeA
    UpgradeTypeA upgradeTypeA_1;
    UpgradeTypeA upgradeTypeA_2;
    UpgradeTypeA upgradeTypeA_3;
    UpgradeTypeA upgradeTypeA_4;
    UpgradeTypeA upgradeTypeA_5;
    //UpgradeTypeB
    UpgradeTypeB upgradeTypeB_1;
    UpgradeTypeB upgradeTypeB_2;
    UpgradeTypeB upgradeTypeB_3;
    UpgradeTypeB upgradeTypeB_4;
    UpgradeTypeB upgradeTypeB_5;
    //UpgradeTypeC
    UpgradeTypeC upgradeTypeC_1;
    UpgradeTypeC upgradeTypeC_2;
    UpgradeTypeC upgradeTypeC_3;
    UpgradeTypeC upgradeTypeC_4;
    UpgradeTypeC upgradeTypeC_5;
    //UpgradeTypeD
    UpgradeTypeD upgradeTypeD_1;
    UpgradeTypeD upgradeTypeD_2;
    UpgradeTypeD upgradeTypeD_3;
    UpgradeTypeD upgradeTypeD_4;
    UpgradeTypeD upgradeTypeD_5;
    //UpgradeTypeE
    UpgradeTypeE upgradeTypeE_1;
    UpgradeTypeE upgradeTypeE_2;
    UpgradeTypeE upgradeTypeE_3;
    UpgradeTypeE upgradeTypeE_4;
    UpgradeTypeE upgradeTypeE_5;

    Meteor meteor1;
    Meteor meteor2;
    Meteor meteor3;
    Meteor meteor4;
    Meteor meteor5;

    EnemyBoss1 enemyBoss;
    // List of mainAnimationList
    Stack<IDropDownAnimation> mainAnimationList;
    // List of Enemy Lasers
    Stack<IEnemyLaser> enemyBossLaserList;
    // List of Enemy Ships
    private Stack<EnemyBossShip> enemyBossesList;
    // List of Enemy Ships
    private Stack<EnemyShip> enemyShipList;
    // List of Meteors
    Stack<Meteor> meteorList;

    // real time counter
    float elapsedTime;

    // For Type C only
    boolean isBoss_TypeC_Shooting = false;

    // boolean for horde spawning
    boolean isSpawnHordeTypeA_1 = false;
    boolean isSpawnHordeTypeA_2 = false;
    boolean isSpawnHordeTypeB_1 = false;
    boolean isSpawnHordeTypeB_2 = false;
    boolean isSpawnHordeTypeC_1 = false;
    boolean isSpawnHordeTypeC_2 = false;
    boolean isSpawnHordeTypeD_1 = false;
    boolean isSpawnHordeTypeD_2 = false;
    boolean isSpawnHordeTypeE_1 = false;
    boolean isSpawnHordeTypeE_2 = false;

    int countHordeTypeA_ableToFire = 2;
    float xHorde = 0, yHorde = WORLD_HEIGHT;
    int countAtBound = 0;
    boolean isMoveRight = true;
    boolean isMoveToMiddle = false;

    public Episode1(){
        upgradeTypeA_1 = new UpgradeTypeA();
        upgradeTypeA_2 = new UpgradeTypeA();
        upgradeTypeA_3 = new UpgradeTypeA();
        upgradeTypeA_4 = new UpgradeTypeA();
        upgradeTypeA_5 = new UpgradeTypeA();

        upgradeTypeB_1 = new UpgradeTypeB();
        upgradeTypeB_2 = new UpgradeTypeB();
        upgradeTypeB_3 = new UpgradeTypeB();
        upgradeTypeB_4 = new UpgradeTypeB();
        upgradeTypeB_5 = new UpgradeTypeB();

        upgradeTypeC_1 = new UpgradeTypeC();
        upgradeTypeC_2 = new UpgradeTypeC();
        upgradeTypeC_3 = new UpgradeTypeC();
        upgradeTypeC_4 = new UpgradeTypeC();
        upgradeTypeC_5 = new UpgradeTypeC();

        upgradeTypeD_1 = new UpgradeTypeD();
        upgradeTypeD_2 = new UpgradeTypeD();
        upgradeTypeD_3 = new UpgradeTypeD();
        upgradeTypeD_4 = new UpgradeTypeD();
        upgradeTypeD_5 = new UpgradeTypeD();

        upgradeTypeE_1 = new UpgradeTypeE();
        upgradeTypeE_2 = new UpgradeTypeE();
        upgradeTypeE_3 = new UpgradeTypeE();
        upgradeTypeE_4 = new UpgradeTypeE();
        upgradeTypeE_5 = new UpgradeTypeE();

        upgradeTypeA_1.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeA_2.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeA_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeA_4.getDrawingRectangle().setX(WORLD_WIDTH / 7);
        upgradeTypeA_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

        upgradeTypeB_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeB_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeB_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeB_4.getDrawingRectangle().setX(WORLD_WIDTH / 7);
        upgradeTypeB_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

        upgradeTypeC_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeC_2.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeC_3.getDrawingRectangle().setX(WORLD_WIDTH / 5+10);
        upgradeTypeC_4.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeC_5.getDrawingRectangle().setX(WORLD_WIDTH / 2+10);

        upgradeTypeD_1.getDrawingRectangle().setX(WORLD_WIDTH / 9);
        upgradeTypeD_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeD_3.getDrawingRectangle().setX(WORLD_WIDTH / 2+15);
        upgradeTypeD_4.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeD_5.getDrawingRectangle().setX(WORLD_WIDTH / 2+5);

        upgradeTypeE_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeE_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeE_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeE_4.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeE_5.getDrawingRectangle().setX(WORLD_WIDTH / 2+10);

        mainAnimationList.push(upgradeTypeA_1);
        mainAnimationList.push(upgradeTypeA_2);
        mainAnimationList.push(upgradeTypeA_3);
        mainAnimationList.push(upgradeTypeA_4);
        mainAnimationList.push(upgradeTypeA_5);
        mainAnimationList.push(upgradeTypeB_1);
        mainAnimationList.push(upgradeTypeB_2);
        mainAnimationList.push(upgradeTypeB_3);
        mainAnimationList.push(upgradeTypeB_4);
        mainAnimationList.push(upgradeTypeB_5);
        mainAnimationList.push(upgradeTypeC_1);
        mainAnimationList.push(upgradeTypeC_2);
        mainAnimationList.push(upgradeTypeC_3);
        mainAnimationList.push(upgradeTypeC_4);
        mainAnimationList.push(upgradeTypeC_5);
        mainAnimationList.push(upgradeTypeD_1);
        mainAnimationList.push(upgradeTypeD_2);
        mainAnimationList.push(upgradeTypeD_3);
        mainAnimationList.push(upgradeTypeD_4);
        mainAnimationList.push(upgradeTypeD_5);
        mainAnimationList.push(upgradeTypeE_1);
        mainAnimationList.push(upgradeTypeE_2);
        mainAnimationList.push(upgradeTypeE_3);
        mainAnimationList.push(upgradeTypeE_4);
        mainAnimationList.push(upgradeTypeE_5);

        meteor1 = new Meteor();
        meteor2 = new Meteor();
        meteor3 = new Meteor();
        meteor4 = new Meteor();
        meteor5 = new Meteor();

        meteor1.getDrawingRectangle().setX(WORLD_WIDTH/2);
        meteor2.getDrawingRectangle().setX(WORLD_WIDTH/2+20);
        meteor3.getDrawingRectangle().setX(WORLD_WIDTH/2+30);
        meteor4.getDrawingRectangle().setX(WORLD_WIDTH/2-10);
        meteor5.getDrawingRectangle().setX(WORLD_WIDTH/2+10);

        meteorList.push(meteor1);
        meteorList.push(meteor2);
        meteorList.push(meteor3);
        meteorList.push(meteor4);
        meteorList.push(meteor5);

        enemyBoss = new EnemyBoss1();
    }
    public Episode1(Stack<IDropDownAnimation> mainAnimationList, Stack<Meteor> meteorList, Stack<IEnemyLaser> enemyBossLaserList,
                    Stack<EnemyBossShip> enemyBossesList, Stack<EnemyShip> enemyShipList) {
        this.mainAnimationList = mainAnimationList;
        this.meteorList = meteorList;
        this.enemyBossLaserList = enemyBossLaserList;
        this.enemyBossesList = enemyBossesList;
        this.enemyShipList = enemyShipList;

        upgradeTypeA_1 = new UpgradeTypeA();
        upgradeTypeA_2 = new UpgradeTypeA();
        upgradeTypeA_3 = new UpgradeTypeA();
        upgradeTypeA_4 = new UpgradeTypeA();
        upgradeTypeA_5 = new UpgradeTypeA();

        upgradeTypeB_1 = new UpgradeTypeB();
        upgradeTypeB_2 = new UpgradeTypeB();
        upgradeTypeB_3 = new UpgradeTypeB();
        upgradeTypeB_4 = new UpgradeTypeB();
        upgradeTypeB_5 = new UpgradeTypeB();

        upgradeTypeC_1 = new UpgradeTypeC();
        upgradeTypeC_2 = new UpgradeTypeC();
        upgradeTypeC_3 = new UpgradeTypeC();
        upgradeTypeC_4 = new UpgradeTypeC();
        upgradeTypeC_5 = new UpgradeTypeC();

        upgradeTypeD_1 = new UpgradeTypeD();
        upgradeTypeD_2 = new UpgradeTypeD();
        upgradeTypeD_3 = new UpgradeTypeD();
        upgradeTypeD_4 = new UpgradeTypeD();
        upgradeTypeD_5 = new UpgradeTypeD();

        upgradeTypeE_1 = new UpgradeTypeE();
        upgradeTypeE_2 = new UpgradeTypeE();
        upgradeTypeE_3 = new UpgradeTypeE();
        upgradeTypeE_4 = new UpgradeTypeE();
        upgradeTypeE_5 = new UpgradeTypeE();

        upgradeTypeA_1.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeA_2.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeA_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeA_4.getDrawingRectangle().setX(WORLD_WIDTH / 7);
        upgradeTypeA_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

        upgradeTypeB_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeB_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeB_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeB_4.getDrawingRectangle().setX(WORLD_WIDTH / 7);
        upgradeTypeB_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

        upgradeTypeC_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeC_2.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeC_3.getDrawingRectangle().setX(WORLD_WIDTH / 5+10);
        upgradeTypeC_4.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeC_5.getDrawingRectangle().setX(WORLD_WIDTH / 2+10);

        upgradeTypeD_1.getDrawingRectangle().setX(WORLD_WIDTH / 9);
        upgradeTypeD_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeD_3.getDrawingRectangle().setX(WORLD_WIDTH / 2+15);
        upgradeTypeD_4.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeD_5.getDrawingRectangle().setX(WORLD_WIDTH / 2+5);

        upgradeTypeE_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeE_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeE_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeE_4.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeE_5.getDrawingRectangle().setX(WORLD_WIDTH / 2+10);

        mainAnimationList.push(upgradeTypeA_1);
        mainAnimationList.push(upgradeTypeA_2);
        mainAnimationList.push(upgradeTypeA_3);
        mainAnimationList.push(upgradeTypeA_4);
        mainAnimationList.push(upgradeTypeA_5);
        mainAnimationList.push(upgradeTypeB_1);
        mainAnimationList.push(upgradeTypeB_2);
        mainAnimationList.push(upgradeTypeB_3);
        mainAnimationList.push(upgradeTypeB_4);
        mainAnimationList.push(upgradeTypeB_5);
        mainAnimationList.push(upgradeTypeC_1);
        mainAnimationList.push(upgradeTypeC_2);
        mainAnimationList.push(upgradeTypeC_3);
        mainAnimationList.push(upgradeTypeC_4);
        mainAnimationList.push(upgradeTypeC_5);
        mainAnimationList.push(upgradeTypeD_1);
        mainAnimationList.push(upgradeTypeD_2);
        mainAnimationList.push(upgradeTypeD_3);
        mainAnimationList.push(upgradeTypeD_4);
        mainAnimationList.push(upgradeTypeD_5);
        mainAnimationList.push(upgradeTypeE_1);
        mainAnimationList.push(upgradeTypeE_2);
        mainAnimationList.push(upgradeTypeE_3);
        mainAnimationList.push(upgradeTypeE_4);
        mainAnimationList.push(upgradeTypeE_5);

        meteor1 = new Meteor();
        meteor2 = new Meteor();
        meteor3 = new Meteor();
        meteor4 = new Meteor();
        meteor5 = new Meteor();

        meteor1.getDrawingRectangle().setX(WORLD_WIDTH/2);
        meteor2.getDrawingRectangle().setX(WORLD_WIDTH/2+20);
        meteor3.getDrawingRectangle().setX(WORLD_WIDTH/2+30);
        meteor4.getDrawingRectangle().setX(WORLD_WIDTH/2-10);
        meteor5.getDrawingRectangle().setX(WORLD_WIDTH/2+10);

        meteorList.push(meteor1);
        meteorList.push(meteor2);
        meteorList.push(meteor3);
        meteorList.push(meteor4);
        meteorList.push(meteor5);

        enemyBoss = new EnemyBoss1();
    }

    public void Start(float deltaTime, long startTime, SpriteBatch batch){
        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Time elapsed in seconds = " + elapsedTime);

        DropObjects(deltaTime, batch);
        SpawnEnemy(startTime, batch);
        SpawnBoss(deltaTime, batch);

    }

    private void SpawnEnemy(long startTime, SpriteBatch batch) {
        if (elapsedTime == 1 && isSpawnHordeTypeA_1 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeA(3, 3);
            isSpawnHordeTypeA_1 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 50 && isSpawnHordeTypeB_1 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeB(3, 3);
            isSpawnHordeTypeB_1 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 100 && isSpawnHordeTypeC_1 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeC(3, 3);
            isSpawnHordeTypeC_1 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 150 && isSpawnHordeTypeD_1 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeD(3, 3);
            isSpawnHordeTypeD_1 = true;
        }
        if (elapsedTime == 200 && isSpawnHordeTypeE_1 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeE(3, 3);
            isSpawnHordeTypeE_1 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 250 && isSpawnHordeTypeA_2 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeA(3, 3);
            isSpawnHordeTypeA_2 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 300 && isSpawnHordeTypeB_2 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeB(3, 3);
            isSpawnHordeTypeB_2 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 350 && isSpawnHordeTypeC_2 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeC(3, 3);
            isSpawnHordeTypeC_2 = true;
        }
        if (elapsedTime == 400 && isSpawnHordeTypeD_2 == false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeD(3, 3);
            isSpawnHordeTypeD_2 = true;
            isMoveToMiddle = false;
        }
        if (elapsedTime == 450 && isSpawnHordeTypeE_2== false) {
            countAtBound = 0;
            xHorde = 0;
            yHorde = WORLD_HEIGHT;
            spawnHordeTypeE(3, 3);
            isSpawnHordeTypeE_2 = true;
            isMoveToMiddle = false;
        }
    }

    public void DropObjects(float deltaTime, SpriteBatch batch) {

        //Meteor
        if (elapsedTime >= 30 && meteor1.getTaken() == false && meteor1.getDestroyed() == false) {
            meteor1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 35 && meteor2.getTaken() == false && meteor2.getDestroyed() == false) {
            meteor2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 43 && meteor3.getTaken() == false && meteor3.getDestroyed() == false) {
            meteor3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 50 && meteor4.getTaken() == false && meteor4.getDestroyed() == false) {
            meteor4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 52 && meteor5.getTaken() == false && meteor5.getDestroyed() == false) {
            meteor5.dropDownward(deltaTime, batch);
        }

        //UpgradeType A
        if (elapsedTime >= 10 && upgradeTypeA_1.getTaken() == false) {
            upgradeTypeA_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 30 && upgradeTypeA_2.getTaken() == false) {
            upgradeTypeA_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 50 && upgradeTypeA_3.getTaken() == false) {
            upgradeTypeA_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 70 && upgradeTypeA_4.getTaken() == false) {
            upgradeTypeA_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 90 && upgradeTypeA_5.getTaken() == false) {
            upgradeTypeA_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeB
        if (elapsedTime >= 1 && upgradeTypeB_1.getTaken() == false) {
            upgradeTypeB_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 2 && upgradeTypeB_2.getTaken() == false) {
            upgradeTypeB_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 3 && upgradeTypeB_3.getTaken() == false) {
            upgradeTypeB_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 4 && upgradeTypeB_4.getTaken() == false) {
            upgradeTypeB_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 5 && upgradeTypeB_5.getTaken() == false) {
            upgradeTypeB_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeC
        if (elapsedTime >= 55 && upgradeTypeC_1.getTaken() == false) {
            upgradeTypeC_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 65 && upgradeTypeC_2.getTaken() == false) {
            upgradeTypeC_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 75 && upgradeTypeC_3.getTaken() == false) {
            upgradeTypeC_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 135 && upgradeTypeC_4.getTaken() == false) {
            upgradeTypeC_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 145 && upgradeTypeC_5.getTaken() == false) {
            upgradeTypeC_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeD
        if (elapsedTime >= 130 && upgradeTypeD_1.getTaken() == false) {
            upgradeTypeD_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 155 && upgradeTypeD_2.getTaken() == false) {
            upgradeTypeD_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 165 && upgradeTypeD_3.getTaken() == false) {
            upgradeTypeD_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 185 && upgradeTypeD_4.getTaken() == false) {
            upgradeTypeD_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 195 && upgradeTypeD_5.getTaken() == false) {
            upgradeTypeD_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeE
        if (elapsedTime >= 190 && upgradeTypeE_1.getTaken() == false) {
            upgradeTypeE_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 210 && upgradeTypeE_2.getTaken() == false) {
            upgradeTypeE_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 235 && upgradeTypeE_3.getTaken() == false) {
            upgradeTypeE_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 260 && upgradeTypeE_4.getTaken() == false) {
            upgradeTypeE_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 275 && upgradeTypeE_5.getTaken() == false) {
            upgradeTypeE_5.dropDownward(deltaTime, batch);
        }
    }

    public void SpawnBoss(float deltaTime, SpriteBatch batch){
        if(elapsedTime == 1){
            if(!enemyBossesList.contains(enemyBoss)){
                enemyBossesList.push(enemyBoss);

            }
        }
        if(elapsedTime >= 1 && !enemyBoss.IsDead()){
            enemyBoss.drawShip(batch);
            makeBossLasers(deltaTime, batch, elapsedTime);
            enemyBoss.update(deltaTime);
        }

    }

    private void makeBossLasers(float deltaTime, SpriteBatch batch, float elapsedTime) {
        ListIterator<EnemyBossShip> enemyBossShipListIterator = enemyBossesList.listIterator();

        while (enemyBossShipListIterator.hasNext()) {
            EnemyBossShip enemyBossShip = enemyBossShipListIterator.next();
            enemyBossShip.update(deltaTime);

            if (enemyBossShip.canFireLaser()) {
                enemyBossShip.setLaserI(new Boss1_LaserTypeA(enemyBossShip.getBoundingBox()));
                if (elapsedTime % 10 == 0) {
                    for (IEnemyLaser laser : enemyBossShip.GetLasers()) {
                        if(laser.isFinished() == false)
                            enemyBossLaserList.push(laser);
                    }
                }
                if (elapsedTime % 3 == 0) {
                    enemyBossShip.setLaserI(new Boss1_LaserTypeB(enemyBossShip.getBoundingBox()));
                    for(IEnemyLaser laser: enemyBossShip.FireTypeB(deltaTime)){
                        enemyBossLaserList.push(laser);
                    }
                }
                if (elapsedTime % 2 == 0) {
                    float randomX = SpaceShooterGame.random.nextFloat() * (WORLD_WIDTH - 10);
                    enemyBossShip.setLaserI(new Boss1_LaserTypeC(enemyBossShip.getBoundingBox()));
                    for(IEnemyLaser laser: enemyBossShip.GetLasers()){

                        laser.getLaserBoundingBox().setX(randomX);
                        enemyBossLaserList.push(laser);
                    }
                    randomX = SpaceShooterGame.random.nextFloat() * (WORLD_WIDTH);
                    for(IEnemyLaser laser: enemyBossShip.GetLasers()){
                        laser.getLaserBoundingBox().setX(randomX);
                        enemyBossLaserList.push(laser);
                    }
                    enemyBossShip.setTimeSinceLastShot(0);
                }
            }
        }

        DrawAndRemoveBossBullets(deltaTime, batch);
    }

    public void DrawAndRemoveBossBullets(float deltaTime, Batch batch) {
        if (!enemyBossLaserList.isEmpty()) {
            ListIterator<IEnemyLaser> iterator = enemyBossLaserList.listIterator();
            while (iterator.hasNext()) {
                IEnemyLaser laser = iterator.next();
                if (laser != null) {
                    if (laser instanceof Boss1_LaserTypeA) {
                        laser.drawLasersWithAnimation(deltaTime, batch);
                        if (laser.isFinished() == true)
                            iterator.remove();
                    } else {
                        if (laser instanceof Boss1_LaserTypeB) {
                            if (laser.getLaserBoundingBox().getY() + laser.getLaserBoundingBox().getHeight() < (-50)) {
                                iterator.remove();
                            } else {
                                laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() - laser.getLaserMovementSpeed() * deltaTime);
                                laser.drawLaser(batch);
                            }
                        } else {
                            if (laser instanceof Boss1_LaserTypeC) {

                                if (laser.getLaserBoundingBox().getY() + laser.getLaserBoundingBox().getHeight() < (0)) {

                                    iterator.remove();
                                }
                                else {

                                    if (((Boss1_LaserTypeC) laser).getLaserBoundingBox().getY() > WORLD_HEIGHT / 3 && (((Boss1_LaserTypeC) laser).isSpreading() == false)) {
                                        laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() - laser.getLaserMovementSpeed() * deltaTime);
                                        laser.drawLaser(batch);
                                    }
                                    else {
                                        ((Boss1_LaserTypeC) laser).setSpreading(true);
                                        if (laser.getMovementType() == "UP_LEFT") {
                                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 15f * deltaTime);
                                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                                        } else if (laser.getMovementType() == "UP_RIGHT") {
                                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 15f * deltaTime);
                                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() + laser.getLaserMovementSpeed() * deltaTime);
                                        } else if (laser.getMovementType() == "RIGHT") {
                                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 20f  * deltaTime);
                                        } else if (laser.getMovementType() == "DOWN_RIGHT") {
                                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() + 15f * deltaTime);
                                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() - laser.getLaserMovementSpeed() * deltaTime);
                                        } else if (laser.getMovementType() == "DOWN_LEFT") {
                                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 15f * deltaTime);
                                            laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() - laser.getLaserMovementSpeed() * deltaTime);
                                        } else if (laser.getMovementType() == "LEFT") {
                                            laser.getLaserBoundingBox().setX(laser.getLaserBoundingBox().getX() - 20f * deltaTime);
                                        }
                                        isBoss_TypeC_Shooting = false;
                                        laser.drawLaser(batch);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void spawnHordeTypeA(int row, int col){
        float random =  SpaceShooterGame.random.nextFloat() * (row * col);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                EnemyShip enemyShip = new EnemyShipTypeA();
                enemyShip.setInHorde(true);
                enemyShip.setMovementSpeed(1);
                enemyShip.getBoundingBox().setX(xHorde);
                enemyShip.getBoundingBox().setY(yHorde);
                enemyShip.setAbleToFire(false);
                if(countHordeTypeA_ableToFire > 0){
                    if(i * j == random){
                        enemyShip.setAbleToFire(true);
                        countHordeTypeA_ableToFire -= 1;
                    }
                }

                xHorde += enemyShip.getBoundingBox().getWidth();
                enemyShipList.add(enemyShip);
                if(j == col - 1){
                    xHorde = 0;
                    yHorde -= enemyShip.getBoundingBox().getHeight();
                }

            }
        }

        random =  SpaceShooterGame.random.nextFloat() * (row * col);

        xHorde = 7 * row + 10;
        yHorde = WORLD_HEIGHT;
        countHordeTypeA_ableToFire = 2;

        for(int a = 0; a < row; a++) {
            for(int b = 0; b < col; b++){
                EnemyShip enemyShip = new EnemyShipTypeA();
                enemyShip.setInHorde(true);
                enemyShip.setMovementSpeed(1);
                enemyShip.getBoundingBox().setX(xHorde);
                enemyShip.getBoundingBox().setY(yHorde);
                enemyShip.setAbleToFire(false);
                if(countHordeTypeA_ableToFire > 0){
                    if(a * b == random){
                        enemyShip.setAbleToFire(true);
                        countHordeTypeA_ableToFire -= 1;
                    }
                }

                xHorde += enemyShip.getBoundingBox().getWidth();
                enemyShipList.add(enemyShip);
                if(b == col - 1){
                    xHorde = 7 * row + 10;
                    yHorde -= enemyShip.getBoundingBox().getHeight();
                }

            }
        }
    }
    public void spawnHordeTypeB(int row, int col){
        float random =  SpaceShooterGame.random.nextFloat() * (row * col);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                EnemyShip enemyShip = new EnemyShipTypeB();
                enemyShip.setInHorde(true);
                enemyShip.setMovementSpeed(3);
                enemyShip.getBoundingBox().setX(xHorde);
                enemyShip.getBoundingBox().setY(yHorde);
                enemyShip.setAbleToFire(false);
                if(countHordeTypeA_ableToFire > 0){
                    if(i * j == random){
                        enemyShip.setAbleToFire(true);
                        countHordeTypeA_ableToFire -= 1;
                    }
                }

                xHorde += enemyShip.getBoundingBox().getWidth();
                enemyShipList.add(enemyShip);
                if(j == col - 1){
                    xHorde = 21;
                    yHorde -= enemyShip.getBoundingBox().getHeight();
                }

            }
        }

    }
    public void spawnHordeTypeC(int row, int col){
        float random =  SpaceShooterGame.random.nextFloat() * (row * col);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                EnemyShip enemyShip = new EnemyShipTypeC();
                enemyShip.setInHorde(true);
                enemyShip.setMovementSpeed(3);
                enemyShip.getBoundingBox().setX(xHorde);
                enemyShip.getBoundingBox().setY(yHorde);
                enemyShip.setAbleToFire(false);
                if(countHordeTypeA_ableToFire > 0){
                    if(i * j == random){
                        enemyShip.setAbleToFire(true);
                        countHordeTypeA_ableToFire -= 1;
                    }
                }

                xHorde += enemyShip.getBoundingBox().getWidth();
                enemyShipList.add(enemyShip);
                if(j == col - 1){
                    xHorde = 0;
                    yHorde -= enemyShip.getBoundingBox().getHeight();
                }

            }
        }

    }
    public void spawnHordeTypeD(int row, int col){
        float random =  SpaceShooterGame.random.nextFloat() * (row * col);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                EnemyShip enemyShip = new EnemyShipTypeD();
                enemyShip.setInHorde(true);
                enemyShip.setMovementSpeed(3);
                enemyShip.getBoundingBox().setX(xHorde);
                enemyShip.getBoundingBox().setY(yHorde);
                enemyShip.setAbleToFire(false);
                if(countHordeTypeA_ableToFire > 0){
                    if(i * j == random){
                        enemyShip.setAbleToFire(true);
                        countHordeTypeA_ableToFire -= 1;
                    }
                }

                xHorde += enemyShip.getBoundingBox().getWidth();
                enemyShipList.add(enemyShip);
                if(j == col - 1){
                    xHorde = 0;
                    yHorde -= enemyShip.getBoundingBox().getHeight();
                }

            }
        }

    }
    public void spawnHordeTypeE(int row, int col){
        float random =  SpaceShooterGame.random.nextFloat() * (row * col);

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                EnemyShip enemyShip = new EnemyShipTypeE();
                enemyShip.setInHorde(true);
                enemyShip.setMovementSpeed(3);
                enemyShip.getBoundingBox().setX(xHorde);
                enemyShip.getBoundingBox().setY(yHorde);
                enemyShip.setAbleToFire(false);
                if(countHordeTypeA_ableToFire > 0){
                    if(i * j == random){
                        enemyShip.setAbleToFire(true);
                        countHordeTypeA_ableToFire -= 1;
                    }
                }

                xHorde += enemyShip.getBoundingBox().getWidth();
                enemyShipList.add(enemyShip);
                if(j == col - 1){
                    xHorde = 0;
                    yHorde -= enemyShip.getBoundingBox().getHeight();
                }

            }
        }

    }
    public void moveHorde(float deltaTime){

        Stack<EnemyShip> enemyShipStack = new Stack<>();
        ListIterator<EnemyShip> enemyShipListIterator = enemyShipList.listIterator();

        while(enemyShipListIterator.hasNext()){
            EnemyShip enemyShip = enemyShipListIterator.next();
            if(enemyShip.isInHorde() == true){
                enemyShipStack.push(enemyShip);
            }
        }

        enemyShipListIterator = enemyShipStack.listIterator();

        while(enemyShipListIterator.hasNext()){

            EnemyShip enemyShip = enemyShipListIterator.next();
            if(enemyShip.getBoundingBox().getX() <= 0){
                countAtBound += 1;
                isMoveRight = true;
            }
            if(enemyShip.getBoundingBox().getX() + enemyShip.getBoundingBox().getWidth() >= WORLD_WIDTH){
                countAtBound += 1;
                isMoveRight = false;
            }
            else{
                // countAtBound = false;
            }
        }

        if(countAtBound / 2 == 0){
            isMoveRight = true;
        }
        else{
            if(countAtBound / 2 == 1){
                isMoveRight = false;
            }
        }

        enemyShipListIterator = enemyShipStack.listIterator();
        while(enemyShipListIterator.hasNext()){
            EnemyShip enemyShip = enemyShipListIterator.next();
            if(enemyShip.getBoundingBox().getY() <= WORLD_HEIGHT / 2){
                isMoveToMiddle = true;
                break;
            }
        }

        if(isMoveToMiddle == false){
            enemyShipListIterator = enemyShipStack.listIterator();
            while(enemyShipListIterator.hasNext()) {
                EnemyShip enemyShip = enemyShipListIterator.next();
                enemyShip.getBoundingBox().setY(enemyShip.getBoundingBox().getY() - enemyShip.getMovementSpeed() * deltaTime);
            }
        }
        else {
            enemyShipListIterator = enemyShipStack.listIterator();

            if(isMoveRight == true){
                while(enemyShipListIterator.hasNext()){
                    EnemyShip enemyShip = enemyShipListIterator.next();
                    enemyShip.getBoundingBox().setX(enemyShip.getBoundingBox().getX() + enemyShip.getMovementSpeed() * deltaTime);
                }
            }
            else {
                while(enemyShipListIterator.hasNext()){
                    EnemyShip enemyShip = enemyShipListIterator.next();
                    enemyShip.getBoundingBox().setX(enemyShip.getBoundingBox().getX() - enemyShip.getMovementSpeed() * deltaTime);
                }
            }
        }

    }

    /* Getter Setter */
    public EnemyShip getEnemyBoss(){
        return enemyBoss;
    }

    public Stack<IEnemyLaser> getEnemyBossLaserList() {
        return enemyBossLaserList;
    }

    public void setEnemyBossLaserList(Stack<IEnemyLaser> enemyBossLaserList) {
        this.enemyBossLaserList = enemyBossLaserList;
    }

    public Stack<EnemyBossShip> getEnemyBossesList() {
        return enemyBossesList;
    }

    public void setEnemyBossesList(Stack<EnemyBossShip> enemyBossesList) {
        this.enemyBossesList = enemyBossesList;
    }

    public Stack<EnemyShip> getEnemyShipList() {
        return enemyShipList;
    }

    public void setEnemyShipList(Stack<EnemyShip> enemyShipList) {
        this.enemyShipList = enemyShipList;
    }

    public Stack<IDropDownAnimation> getMainAnimationList() {
        return mainAnimationList;
    }

    public void setMainAnimationList(Stack<IDropDownAnimation> mainAnimationList) {
        this.mainAnimationList = mainAnimationList;
    }

    public Stack<Meteor> getMeteorList() {
        return meteorList;
    }

    public void setMeteorList(Stack<Meteor> meteorList) {
        this.meteorList = meteorList;
    }
}
