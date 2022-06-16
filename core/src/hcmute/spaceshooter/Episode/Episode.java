package hcmute.spaceshooter.Episode;

import static hcmute.spaceshooter.GlobalVariables.WORLD_HEIGHT;
import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ListIterator;
import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Animation.UpgradeTypeC;
import hcmute.spaceshooter.Lasers.Boss1_LaserTypeA;
import hcmute.spaceshooter.Lasers.Boss1_LaserTypeB;
import hcmute.spaceshooter.Lasers.Boss1_LaserTypeC;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Ships.EnemyBoss1;
import hcmute.spaceshooter.Ships.EnemyBossShip;
import hcmute.spaceshooter.SpaceShooterGame;

public class Episode{
    UpgradeTypeC upgradeTypeA_1;
    UpgradeTypeC upgradeTypeA_2;
    UpgradeTypeC upgradeTypeA_3;
    UpgradeTypeC upgradeTypeA_4;
    UpgradeTypeC upgradeTypeA_5;

    EnemyBoss1 enemyBoss1;
    float Boss1_TypeC_StartingShootingTime = 0;
    float Boss1_TypeC_ShootingTimer = 0;
    boolean isBoss1_TypeC_Shooting = false;
    Meteor meteor;
    Stack<IDropDownAnimation> mainAnimationList;
    Stack<IEnemyLaser> enemyBossLaserList;
    // List of Enemy Ships
    private Stack<EnemyBossShip> enemyBossesList;
    float elapsedTime;

    public Episode(Stack<IDropDownAnimation> mainAnimationList, Stack<Meteor> meteorList, Stack<IEnemyLaser> enemyBossLaserList, Stack<EnemyBossShip> enemyBossesList) {
        this.mainAnimationList = mainAnimationList;
        this.enemyBossLaserList = enemyBossLaserList;
        this.enemyBossesList = enemyBossesList;

        upgradeTypeA_1 = new UpgradeTypeC();
        upgradeTypeA_2 = new UpgradeTypeC();
        upgradeTypeA_3 = new UpgradeTypeC();
        upgradeTypeA_4 = new UpgradeTypeC();
        upgradeTypeA_5 = new UpgradeTypeC();
        enemyBoss1 = new EnemyBoss1();

        meteor = new Meteor();
        // fireMeteor1 = new FireMeteor();
        upgradeTypeA_1.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        upgradeTypeA_2.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeA_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeA_4.getDrawingRectangle().setX(WORLD_WIDTH / 7);
        upgradeTypeA_5.getDrawingRectangle().setX(WORLD_WIDTH / 9);
        meteor.getDrawingRectangle().setX(WORLD_WIDTH/3);
        mainAnimationList.push(upgradeTypeA_1);
        mainAnimationList.push(upgradeTypeA_2);
        mainAnimationList.push(upgradeTypeA_3);
        mainAnimationList.push(upgradeTypeA_4);
        mainAnimationList.push(upgradeTypeA_5);

        meteorList.push(meteor);


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


        if (elapsedTime >= 1 && meteor.getTaken() == false && meteor.getDestroyed() == false) {
            meteor.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 2 && upgradeTypeA_1.getTaken() == false) {
            upgradeTypeA_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 3 && upgradeTypeA_2.getTaken() == false) {
            upgradeTypeA_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 4 && upgradeTypeA_3.getTaken() == false) {
            upgradeTypeA_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 5 && upgradeTypeA_4.getTaken() == false) {
            upgradeTypeA_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 6 && upgradeTypeA_5.getTaken() == false) {
            upgradeTypeA_5.dropDownward(deltaTime, batch);
        }
    }

    public void SpawnBoss1(float deltaTime,  SpriteBatch batch){

        if(elapsedTime == 5){
            if(!enemyBossesList.contains(enemyBoss1)){
                enemyBossesList.push(enemyBoss1);
            }

        }
        if(elapsedTime >= 5 && !enemyBoss1.IsDead()){
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
