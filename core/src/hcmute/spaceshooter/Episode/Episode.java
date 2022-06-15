package hcmute.spaceshooter.Episode;

import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Animation.UpgradeTypeA;
import hcmute.spaceshooter.Animation.UpgradeTypeB;
import hcmute.spaceshooter.Animation.UpgradeTypeC;
import hcmute.spaceshooter.Animation.UpgradeTypeD;
import hcmute.spaceshooter.Animation.UpgradeTypeE;
import hcmute.spaceshooter.Ships.ExplosiveDrone;

public class Episode{
    UpgradeTypeE upgradeTypeA_1;
    UpgradeTypeE upgradeTypeA_2;
    UpgradeTypeE upgradeTypeA_3;
    UpgradeTypeE upgradeTypeA_4;
    UpgradeTypeE upgradeTypeA_5;

    Meteor meteor;
    Stack<IDropDownAnimation> mainAnimationList;
    public Episode(Stack<IDropDownAnimation> mainAnimationList, Stack<Meteor> meteorList) {
        this.mainAnimationList = mainAnimationList;

        upgradeTypeA_1 = new UpgradeTypeE();
        upgradeTypeA_2 = new UpgradeTypeE();
        upgradeTypeA_3 = new UpgradeTypeE();
        upgradeTypeA_4 = new UpgradeTypeE();
        upgradeTypeA_5 = new UpgradeTypeE();
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

    public void DropUpgrade(float deltaTime, long startTime, SpriteBatch batch) {
        System.out.println("Time elapsed in seconds = " + ((System.currentTimeMillis() - startTime) / 1000));
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
        if (elapsedTime >= 1 && meteor.getTaken() == false && meteor.getDestroyed() == false) {
            meteor.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 1 && upgradeTypeA_1.getTaken() == false) {
            upgradeTypeA_1.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 1 && upgradeTypeA_2.getTaken() == false) {
            upgradeTypeA_2.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 1 && upgradeTypeA_3.getTaken() == false) {
            upgradeTypeA_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 4 && upgradeTypeA_4.getTaken() == false) {
            upgradeTypeA_4.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 1 && upgradeTypeA_5.getTaken() == false) {
            upgradeTypeA_5.dropDownward(deltaTime, batch);
        }
//        if (elapsedTime >= 1 && explosiveDrone1.getTaken() == false) {
//            explosiveDrone1.dropDownward(deltaTime, batch);
//        }
//        if (elapsedTime >= 1 && elapsedTime <= 9 && fireMeteor1.getTaken() == false) {
//            fireMeteor1.dropDownward(deltaTime, batch);
//        }
    }


//    @Override
//    public void DropUpgrade() {
//
//    }
//
//    @Override
//    public void SpawnEnemy() {
//
//    }
}
