package hcmute.spaceshooter.Episode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Ships.EnemyBossShip;
import hcmute.spaceshooter.Ships.EnemyShip;

public interface IEpisode {
    void DropObjects(float deltaTime, SpriteBatch batch);

    void Start(float deltaTime, long startTime, SpriteBatch batch);

    void SpawnBoss(float deltaTime, SpriteBatch batch);

    void moveHorde(float deltaTime);

    void setEnemyBossLaserList(Stack<IEnemyLaser> enemyBossLaserList);

    void setEnemyBossesList(Stack<EnemyBossShip> enemyBossesList);

    void setEnemyShipList(Stack<EnemyShip> enemyShipList);

    void setMeteorList(Stack<Meteor> meteorList);

    void setMainAnimationList(Stack<IDropDownAnimation> upgradeDroppingItemList);
}
