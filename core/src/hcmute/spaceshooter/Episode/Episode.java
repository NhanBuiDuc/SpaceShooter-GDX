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
import hcmute.spaceshooter.SpaceShooterGame;

public class Episode{ //Upgrade boxes
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

    EnemyBoss1 enemyBoss1;

    //    Stack<IDropDownAnimation> mainAnimationList;
//    Stack<Meteor> meteorList;
    Stack<IEnemyLaser> enemyBossLaserList;
    // List of Enemy Ships
    private Stack<EnemyBossShip> enemyBossesList;
    float boss1StartingShootingTimer = 0;
    float elapsedTime;

    float Boss1_TypeC_StartingShootingTime = 0;
    float Boss1_TypeC_ShootingTimer = 0;
    boolean isBoss1_TypeC_Shooting = false;
    Meteor meteor;
    Stack<IDropDownAnimation> mainAnimationList;

    public Episode(Stack<IDropDownAnimation> mainAnimationList, Stack<Meteor> meteorList, Stack<IEnemyLaser> enemyBossLaserList, Stack<EnemyBossShip> enemyBossesList) {
        this.mainAnimationList = mainAnimationList;
        this.enemyBossLaserList = enemyBossLaserList;
        this.enemyBossesList = enemyBossesList;

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
        upgradeTypeC_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeC_4.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeC_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

        upgradeTypeD_1.getDrawingRectangle().setX(WORLD_WIDTH / 9);
        upgradeTypeD_2.getDrawingRectangle().setX(WORLD_WIDTH / 7);
        upgradeTypeD_3.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeD_4.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeD_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

        upgradeTypeE_1.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeE_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeE_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeE_4.getDrawingRectangle().setX(WORLD_WIDTH / 4);
        upgradeTypeE_5.getDrawingRectangle().setX(WORLD_WIDTH / 2);

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

        enemyBoss1 = new EnemyBoss1();
    }

    public void Start(float deltaTime, long startTime, SpriteBatch batch){
        elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("Time elapsed in seconds = " + elapsedTime);

        DropObjects(deltaTime, batch);
        if(isBoss1_TypeC_Shooting == true){
            UpdateTypeCTimer(deltaTime);
        }
        SpawnBoss1(deltaTime, batch);

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
        if (elapsedTime >= 60 && upgradeTypeA_2.getTaken() == false) {
            upgradeTypeA_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 75 && upgradeTypeA_3.getTaken() == false) {
            upgradeTypeA_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 170 && upgradeTypeA_4.getTaken() == false) {
            upgradeTypeA_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 180 && upgradeTypeA_5.getTaken() == false) {
            upgradeTypeA_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeB
        if (elapsedTime >= 70 && upgradeTypeB_1.getTaken() == false) {
            upgradeTypeB_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 100 && upgradeTypeB_2.getTaken() == false) {
            upgradeTypeB_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 120 && upgradeTypeB_3.getTaken() == false) {
            upgradeTypeB_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 230 && upgradeTypeB_4.getTaken() == false) {
            upgradeTypeB_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 250 && upgradeTypeB_5.getTaken() == false) {
            upgradeTypeB_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeC
        if (elapsedTime >= 20 && upgradeTypeC_1.getTaken() == false) {
            upgradeTypeC_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 40 && upgradeTypeC_2.getTaken() == false) {
            upgradeTypeC_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 75 && upgradeTypeC_3.getTaken() == false) {
            upgradeTypeC_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 200 && upgradeTypeC_4.getTaken() == false) {
            upgradeTypeC_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 220 && upgradeTypeC_5.getTaken() == false) {
            upgradeTypeC_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeD
        if (elapsedTime >= 25 && upgradeTypeD_1.getTaken() == false) {
            upgradeTypeD_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 35 && upgradeTypeD_2.getTaken() == false) {
            upgradeTypeD_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 100 && upgradeTypeD_3.getTaken() == false) {
            upgradeTypeD_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 150 && upgradeTypeD_4.getTaken() == false) {
            upgradeTypeD_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 160 && upgradeTypeD_5.getTaken() == false) {
            upgradeTypeD_5.dropDownward(deltaTime, batch);
        }

        //UpgradeTypeE
        if (elapsedTime >= 185 && upgradeTypeE_1.getTaken() == false) {
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

    public void SpawnBoss1(float deltaTime,  SpriteBatch batch){

        if(elapsedTime == 300){
            if(!enemyBossesList.contains(enemyBoss1)){
                enemyBossesList.push(enemyBoss1);
            }

        }

        if(elapsedTime >= 300 && !enemyBoss1.IsDead()){
            enemyBoss1.drawShip(batch);
            makeBoss1Lasers(deltaTime, batch, elapsedTime);
            enemyBoss1.update(deltaTime);
        }

    }

    private void makeBoss1Lasers(float deltaTime, SpriteBatch batch, float elapsedTime) {
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
//                if (elapsedTime % 3 == 0) {
//                    enemyBossShip.setLaserI(new Boss1_LaserTypeB(enemyBossShip.getBoundingBox()));
//                    for(IEnemyLaser laser: enemyBossShip.FireTypeB(deltaTime)){
//                        enemyBossLaserList.push(laser);
//                    }
//                }
                if (elapsedTime % 4 == 0) {
                    float randomX = SpaceShooterGame.random.nextFloat() * (WORLD_WIDTH);
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
                                int count = 0;
                                for(IEnemyLaser laserTypeC: enemyBossLaserList){
                                    if(laserTypeC instanceof Boss1_LaserTypeC){
                                        count += 1;
                                    }
                                }
                                if(count == 0 && enemyBossLaserList.empty() == false){
                                    Boss1_TypeC_StartingShootingTime = 0;
                                }
                                if (laser.getLaserBoundingBox().getY() + laser.getLaserBoundingBox().getHeight() < (0)) {

                                    iterator.remove();
                                }
                                else {
                                    if (Boss1_TypeC_StartingShootingTime == 0) {
                                        Boss1_TypeC_StartingShootingTime = elapsedTime;
                                    }
//                                    if (Boss1_TypeC_ShootingTimer - Boss1_TypeC_StartingShootingTime < 0.5) {
//                                        laser.getLaserBoundingBox().setY(laser.getLaserBoundingBox().getY() - laser.getLaserMovementSpeed() * deltaTime);
//                                        laser.drawLaser(batch);
//                                    }
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
                                        isBoss1_TypeC_Shooting = false;
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
    public void UpdateTypeCTimer(float deltaTime){
        Boss1_TypeC_ShootingTimer = elapsedTime;
    }
}
