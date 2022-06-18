package hcmute.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

public class ResourceManager {
    public TextureAtlas textureAtlas;
    public Skin skin;
    public Texture settingsBackground,splashBackground,menuBackground,victoryBackground,gameOverBackground,
    selectCampaignBackground;
    public SpriteBatch batch;
    public Music menuTheme;
    public Music settingsTheme;
    public Music selectCampaignTheme;
    public Music battleTheme;
    public Music bossTheme;
    public Music victoryTheme;
    public Music gameOverTheme;
    public Music explosionSoundEffect;
    public Music laserSoundEffect;
    public Music splashTheme;
    public float musicVolume=.5f;
    public float sfxVolume=.5f;
    public AssetManager assetManager;
    public JsonReader jsonReader;
    public static  int campaignIndex;
    public static Array<Campaign> campaigns=new Array<Campaign>();
    public FileHandle file=Gdx.files.local("player.json");
    public ResourceManager()
    {
        assetManager =new AssetManager();
        jsonReader=new JsonReader();
        assetManager.load("star-soldier-ui.atlas",TextureAtlas.class);
        assetManager.load("star-soldier-ui.json",Skin.class);

        assetManager.load("music/menu.mp3",Music.class);
        assetManager.load("music/settings.mp3",Music.class);
        assetManager.load("music/select-campaign.wav",Music.class);
        assetManager.load("music/battle.ogg",Music.class);
        assetManager.load("music/boss.ogg",Music.class);
        assetManager.load("music/victory.mp3",Music.class);
        assetManager.load("music/game-over.mp3",Music.class);

        assetManager.load("music/splash.mp3", Music.class);
        assetManager.load("sfx/explosion_effect.wav",Music.class);
        assetManager.load("sfx/laser_effect.mp3",Music.class);

        assetManager.load("screen/settings.jpg",Texture.class);
        assetManager.load("screen/splash.jpg",Texture.class);
        assetManager.load("screen/menu.jpg",Texture.class);
        assetManager.load("screen/victory.jpg",Texture.class);
        assetManager.load("screen/game-over.jpg",Texture.class);
        assetManager.load("screen/select-campaign.jpg",Texture.class);
        assetManager.finishLoading();
        //load atlas
        textureAtlas=assetManager.get("star-soldier-ui.atlas", TextureAtlas.class);
        //load skin
        skin=new Skin(textureAtlas);
        skin.load(Gdx.files.internal("star-soldier-ui.json"));
        //load sound
        menuTheme=assetManager.get("music/menu.mp3", Music.class);
        settingsTheme=assetManager.get("music/settings.mp3",Music.class);
        selectCampaignTheme=assetManager.get("music/select-campaign.wav",Music.class);
        battleTheme=assetManager.get("music/battle.ogg",Music.class);
        bossTheme=assetManager.get("music/boss.ogg",Music.class);
        victoryTheme=assetManager.get("music/victory.mp3",Music.class);
        gameOverTheme=assetManager.get("music/game-over.mp3",Music.class);
        splashTheme=assetManager.get("music/splash.mp3",Music.class);
        //load sfx
        explosionSoundEffect=assetManager.get("sfx/explosion_effect.wav",Music.class);
        laserSoundEffect=assetManager.get("sfx/laser_effect.mp3",Music.class);
        //load background
        menuBackground=assetManager.get("screen/menu.jpg",Texture.class);
        settingsBackground=assetManager.get("screen/settings.jpg",Texture.class);
        splashBackground=assetManager.get("screen/splash.jpg",Texture.class);
        victoryBackground=assetManager.get("screen/victory.jpg",Texture.class);
        gameOverBackground=assetManager.get("screen/game-over.jpg",Texture.class);
        selectCampaignBackground=assetManager.get("screen/select-campaign.jpg",Texture.class);

    }
    //set volume for theme
    public void setMusicVolume(float musicVolume)
    {
        menuTheme.setVolume(musicVolume);
        settingsTheme.setVolume(musicVolume);
        selectCampaignTheme.setVolume(musicVolume);
        gameOverTheme.setVolume(musicVolume);
        victoryTheme.setVolume(musicVolume);
        battleTheme.setVolume(musicVolume);
        bossTheme.setVolume(musicVolume);
        splashTheme.setVolume(musicVolume);
    }
    //set volume for sound effects
    public void setSfxVolume(float sfxVolume)
    {
        explosionSoundEffect.setVolume(sfxVolume/3);
        laserSoundEffect.setVolume(sfxVolume/3);
    }
    //load campaign list available
    public void loadCampaigns()
    {
        JsonValue base=jsonReader.parse(Gdx.files.internal("resources/campaigns.json"));
        for(JsonValue campaign:base.get("campaigns"))
        {
            String campaignName=campaign.getString("name");
            String bossName=campaign.getString("boss");
            int record=campaign.getInt("record");
            campaigns.add(new Campaign(campaignName,bossName,record));
        }
    }
    public void dispose()
    {
        assetManager.dispose();
        textureAtlas.dispose();
        skin.dispose();
        menuTheme.dispose();
        settingsTheme.dispose();
        battleTheme.dispose();
        selectCampaignTheme.dispose();
        bossTheme.dispose();
        splashTheme.dispose();
        victoryTheme.dispose();
        gameOverTheme.dispose();
        explosionSoundEffect.dispose();
        laserSoundEffect.dispose();
    }
}