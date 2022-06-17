package hcmute.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class ResourceManager {
    public TextureAtlas textureAtlas;
    public Skin skin;
    public Texture background,splash,menu;
    public SpriteBatch batch;
    public Music menuTheme;
    public Music loadingTheme;
    public Music battleTheme;
    public Music bossTheme;
    public Music backgroundTheme;

    public static Music explosionSoundEffect;
    public static Music laserSoundEffect;
    public static Sound battleStart;
    public static float musicVolume=.0f;
    public static float sfxVolume=.0f;
    public static boolean musicMute=true;
    public static boolean sfxMute=true;
    public AssetManager assetManager;
    public Array<String> campaigns=new Array<String>();
    public Array<String> bosses=new Array<String>();
    public ResourceManager()
    {
        assetManager =new AssetManager();
        assetManager.load("star-soldier-ui.atlas",TextureAtlas.class);
        assetManager.load("star-soldier-ui.json",Skin.class);

        assetManager.load("music/menu.wav",Music.class);
        assetManager.load("music/loading.wav",Music.class);
        assetManager.load("music/battle.wav",Music.class);
        assetManager.load("music/battle.ogg",Music.class);
        assetManager.load("music/boss.ogg",Music.class);

        assetManager.load("sfx/start-level.wav", Sound.class);
        assetManager.load("sfx/explosion_effect.wav",Music.class);
        assetManager.load("sfx/laser_effect.mp3",Music.class);

        assetManager.load("screen/background.png",Texture.class);
        assetManager.load("screen/splash.jpg",Texture.class);
        assetManager.load("screen/menu.jpg",Texture.class);

        assetManager.finishLoading();
        //load atlas
        textureAtlas=assetManager.get("star-soldier-ui.atlas", TextureAtlas.class);
        //load skin
        skin=new Skin(textureAtlas);
        skin.load(Gdx.files.internal("star-soldier-ui.json"));
        //load sound
        menuTheme=assetManager.get("music/menu.wav", Music.class);
        loadingTheme=assetManager.get("music/loading.wav",Music.class);
        battleTheme=assetManager.get("music/battle.wav",Music.class);
        bossTheme=assetManager.get("music/boss.ogg",Music.class);
        backgroundTheme=assetManager.get("music/battle.ogg",Music.class);
        //load sfx
        battleStart=assetManager.get("sfx/start-level.wav",Sound.class);
        explosionSoundEffect=assetManager.get("sfx/explosion_effect.wav",Music.class);
        laserSoundEffect=assetManager.get("sfx/laser_effect.mp3",Music.class);
        //load background
        menu=assetManager.get("screen/menu.jpg",Texture.class);
        background=assetManager.get("screen/background.png",Texture.class);
        splash=assetManager.get("screen/splash.jpg",Texture.class);
        //set campaigns
        campaigns.add("Earth");
        campaigns.add("Mars");
        campaigns.add("Saturn");
        campaigns.add("Jupiter");
        //set bosses
        bosses.add("Earth Overlord");
        bosses.add("????");
        bosses.add("?????");
        bosses.add("??????");
    }
    public void setMusicVolume(float musicVolume)
    {
        menuTheme.setVolume(musicVolume);
        loadingTheme.setVolume(musicVolume);
        battleTheme.setVolume(musicVolume);
        backgroundTheme.setVolume(musicVolume);
    }
    public void setSfxVolume(float sfxVolume)
    {
        explosionSoundEffect.setVolume(sfxVolume);
        laserSoundEffect.setVolume(sfxVolume);
    }

    public void dispose()
    {
        assetManager.dispose();
        textureAtlas.dispose();
        skin.dispose();
        menuTheme.dispose();
        loadingTheme.dispose();
        battleTheme.dispose();
        battleStart.dispose();
    }
}