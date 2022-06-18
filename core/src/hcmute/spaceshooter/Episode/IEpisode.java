package hcmute.spaceshooter.Episode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import hcmute.spaceshooter.Animation.IDropDownAnimation;
import hcmute.spaceshooter.Animation.Meteor;
import hcmute.spaceshooter.Lasers.IEnemyLaser;
import hcmute.spaceshooter.Ships.EnemyBossShip;
import hcmute.spaceshooter.Ships.EnemyShip;

/**
 * The interface for the campaign design
 */
public interface IEpisode {

    /** Drop objects like upgrades and meteors
     *
     * @param deltaTime
     * @param batch
     */
    void DropObjects(float deltaTime, SpriteBatch batch);

    /** The start method to combined all the gameplay determinants
     *
     * @param deltaTime The time in seconds since the last render.
     * @param startTime The start time of the gameplay
     * @param batch The drawing object of Gdx
     */
    void Start(float deltaTime, long startTime, SpriteBatch batch);

    /** To spawn Boss and draw its bullets and lasers
     *
     * @param deltaTime The time in seconds since the last render.
     * @param batch The drawing object of Gdx
     */
    void SpawnBoss(float deltaTime, SpriteBatch batch);

    /** Move a group of enemy ships
     *
     * @param deltaTime The time in seconds since the last render.
     */
    void moveHorde(float deltaTime);

    /**
     *
     * @return Current enemy boss
     */
    EnemyShip getEnemyBoss();

}
