package hcmute.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class ResourceManager {
    public TextureAtlas textureAtlas;
    public Skin skin;
    public Texture background,splash;
    public SpriteBatch batch;
    public static Music menuTheme;
    public static Music loadingTheme;
    public static Music battleTheme;
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
        assetManager=new AssetManager();
        assetManager.load("star-soldier-ui.atlas",TextureAtlas.class);
        assetManager.load("star-soldier-ui.json",Skin.class);

        assetManager.load("music/menu.wav",Music.class);
        assetManager.load("music/loading.wav",Music.class);
        assetManager.load("music/battle.wav",Music.class);

        assetManager.load("sfx/start-level.wav", Sound.class);

        assetManager.load("screen/background.png",Texture.class);
        assetManager.load("screen/splash.jpg",Texture.class);

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
        //load sfx
        battleStart=assetManager.get("sfx/start-level.wav",Sound.class);
        //load background
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