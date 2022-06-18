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
    public Music selectCampaignTheme;
    public Music battleTheme;
    public Music bossTheme;
    public Music explosionSoundEffect;
    public Music laserSoundEffect;
    public Music startUpSoundEffect;
    public static float musicVolume=0.5f;
    public static float sfxVolume=0.5f;
    public static boolean musicMute=true;
    public static boolean sfxMute=true;
    public static int campaignIndex=0;
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
        assetManager.load("music/select-campaign.wav",Music.class);
        assetManager.load("music/battle.ogg",Music.class);
        assetManager.load("music/boss.ogg",Music.class);

        assetManager.load("sfx/start-up.wav", Music.class);
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
        selectCampaignTheme=assetManager.get("music/select-campaign.wav",Music.class);
        battleTheme=assetManager.get("music/battle.ogg",Music.class);
        bossTheme=assetManager.get("music/boss.ogg",Music.class);
        //load sfx
        startUpSoundEffect=assetManager.get("sfx/start-up.wav",Music.class);
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
        selectCampaignTheme.setVolume(musicVolume);
        battleTheme.setVolume(musicVolume);
        bossTheme.setVolume(musicVolume);
    }
    public void setSfxVolume(float sfxVolume)
    {
        explosionSoundEffect.setVolume(sfxVolume);
        laserSoundEffect.setVolume(sfxVolume);
        startUpSoundEffect.setVolume(sfxVolume);
    }
    public void dispose()
    {
        assetManager.dispose();
        textureAtlas.dispose();
        skin.dispose();
        menuTheme.dispose();
        loadingTheme.dispose();
        battleTheme.dispose();
        selectCampaignTheme.dispose();
        bossTheme.dispose();
        startUpSoundEffect.dispose();
        explosionSoundEffect.dispose();
        laserSoundEffect.dispose();
    }
}