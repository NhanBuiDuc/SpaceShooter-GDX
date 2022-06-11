package hcmute.spaceshooter.Episode;

import static hcmute.spaceshooter.GlobalVariables.WORLD_WIDTH;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.UpgradeTypeA;

public class Episode{
    UpgradeTypeA upgradeTypeA_1 = new UpgradeTypeA();
    UpgradeTypeA upgradeTypeA_2 = new UpgradeTypeA();
    UpgradeTypeA upgradeTypeA_3 = new UpgradeTypeA();
    Stack<IDropDownAnimation> mainAnimationList;
    public Episode(Stack<IDropDownAnimation> mainAnimationList) {
        upgradeTypeA_1.getDrawingRectangle().setX(WORLD_WIDTH / 5);
        upgradeTypeA_2.getDrawingRectangle().setX(WORLD_WIDTH / 2);
        this.mainAnimationList = mainAnimationList;
        mainAnimationList.push(upgradeTypeA_1);
        mainAnimationList.push(upgradeTypeA_2);
        mainAnimationList.push(upgradeTypeA_3);
    }

    public void DropUpgrade(float deltaTime, long startTime, SpriteBatch batch) {
        System.out.println("Time elapsed in seconds = " + ((System.currentTimeMillis() - startTime) / 1000));
        float elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

        if (elapsedTime >= 1 && elapsedTime <= 7 && upgradeTypeA_3.getTaken() == false) {
            upgradeTypeA_3.dropDownward(deltaTime, batch);
        }
        if (elapsedTime >= 1 && elapsedTime <= 7 && upgradeTypeA_1.getTaken() == false) {
            upgradeTypeA_1.dropDownward(deltaTime, batch);
        }

        if (elapsedTime >= 20 && elapsedTime <= 26 && upgradeTypeA_2.getTaken() == false) {
            upgradeTypeA_2.dropDownward(deltaTime, batch);
        }
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
