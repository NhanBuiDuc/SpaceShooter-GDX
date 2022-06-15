package hcmute.spaceshooter.Animation.SoundEffect;

import static hcmute.spaceshooter.GlobalVariables.laserEffect;

import com.badlogic.gdx.audio.Music;

public class LaserSoundEffect {
    private static final Music soundEffect = laserEffect;

    public static final void laserSound(){
        soundEffect.setVolume(0.1f);
        soundEffect.setLooping(false);
        soundEffect.play();
    }
}
