package hcmute.spaceshooter.Animation.SoundEffect;

import static hcmute.spaceshooter.GlobalVariables.explosionEffect;

import com.badlogic.gdx.audio.Music;

public class ExplosionSoundEffect {
    private static final Music soundEffect = explosionEffect;

    public static final void smallSoundEffect(){
        soundEffect.setVolume(0.1f);
        soundEffect.setLooping(false);
        soundEffect.play();
    }
    public static final void bigSoundEffect(){
        soundEffect.setVolume(0.2f);
        soundEffect.setLooping(false);
        soundEffect.play();
    }
}

