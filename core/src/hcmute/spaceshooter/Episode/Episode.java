package hcmute.spaceshooter.Episode;

import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Animation.UpgradeTypeA;
import hcmute.spaceshooter.Ships.ExplosiveDrone;

public class Episode{
    UpgradeTypeA upgradeTypeA_1;
    UpgradeTypeA upgradeTypeA_2;
    UpgradeTypeA upgradeTypeA_3;
    UpgradeTypeA upgradeTypeA_4;
    UpgradeTypeA upgradeTypeA_5;
    // ExplosiveDrone explosiveDrone1;
    Meteor meteor;
    // FireMeteor fireMeteor1;
    Stack<IDropDownAnimation> mainAnimationList;
    public Episode(Stack<IDropDownAnimation> mainAnimationList) {
        this.mainAnimationList = mainAnimationList;

        upgradeTypeA_1 = new UpgradeTypeA();
        upgradeTypeA_2 = new UpgradeTypeA();
        upgradeTypeA_3 = new UpgradeTypeA();
        upgradeTypeA_4 = new UpgradeTypeA();
        upgradeTypeA_5 = new UpgradeTypeA();
        // explosiveDrone1 = new ExplosiveDrone();
        meteor = new Meteor();
        // fireMeteor1 = new FireMeteor();
        upgradeTypeA_1.getDrawingRectangle().setX(WORLD_WIDTH / 1);
        upgradeTypeA_2.getDrawingRectangle().setX(WORLD_WIDTH / 3);
        upgradeTypeA_3.getDrawingRectangle().setX(WORLD_WIDTH / 5);

        upgradeTypeA_5.getDrawingRectangle().setX(WORLD_WIDTH / 7f);
        // explosiveDrone1.getDrawingRectangle().setX(WORLD_WIDTH / 2f);
        meteor.getDrawingRectangle().setX(WORLD_WIDTH/3);
        // fireMeteor1.getDrawingRectangle().setX(WORLD_WIDTH/3.2f);
        mainAnimationList.push(upgradeTypeA_1);
        mainAnimationList.push(upgradeTypeA_2);
        mainAnimationList.push(upgradeTypeA_3);
        mainAnimationList.push(upgradeTypeA_4);
        mainAnimationList.push(upgradeTypeA_5);
        // mainAnimationList.push(explosiveDrone1);
        mainAnimationList.push(meteor);
        // mainAnimationList.push(fireMeteor1);
    }

    public void DropUpgrade(float deltaTime, long startTime, SpriteBatch batch) {
        System.out.println("Time elapsed in seconds = " + ((System.currentTimeMillis() - startTime) / 1000));
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
//        if (elapsedTime >= 1 && meteor.getTaken() == false) {
//            meteor.dropDownward(deltaTime, batch);
//        }
//        if (elapsedTime >= 1 && upgradeTypeA_1.getTaken() == false) {
//            upgradeTypeA_1.dropDownward(deltaTime, batch);
//        }
//        if (elapsedTime >= 1 && upgradeTypeA_2.getTaken() == false) {
//            upgradeTypeA_2.dropDownward(deltaTime, batch);
//        }
//        if (elapsedTime >= 1 && upgradeTypeA_3.getTaken() == false) {
//            upgradeTypeA_3.dropDownward(deltaTime, batch);
//        }
//        if (elapsedTime >= 4 && upgradeTypeA_4.getTaken() == false) {
//            upgradeTypeA_4.dropDownward(deltaTime, batch);
//        }
//        if (elapsedTime >= 1 && upgradeTypeA_5.getTaken() == false) {
//            upgradeTypeA_5.dropDownward(deltaTime, batch);
//        }
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
