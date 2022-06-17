package hcmute.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SettingsScreen implements Screen {
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    public ResourceManager rm;
    private int backgroundOffset=0;
    public SettingsScreen()
    {
        rm=new ResourceManager();
        batch=new SpriteBatch();
        //set viewport
        viewport=new StretchViewport(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        viewport.apply();
        //set stage for actors
        stage=new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        //play theme
        rm.menuTheme.setVolume(rm.musicVolume);
        rm.menuTheme.setLooping(true);
        rm.menuTheme.play();
    }
    @Override
    public void show() {
        //set label style for title
        Label.LabelStyle titleStyle=rm.skin.get("title", Label.LabelStyle.class);
        Label title= new Label("Settings",titleStyle);
        title.setFontScale(0.35f);
        title.setSize(200,100);
        title.setPosition(105,280,Align.center);
        stage.addActor(title);
        //create container for setting options
        Window.WindowStyle windowStyle=rm.skin.get("special",Window.WindowStyle.class);
        Window settingsWindow=new Window("",windowStyle);
        settingsWindow.setOrigin(0,120);
        settingsWindow.setSize(360,400);
        settingsWindow.setScale(0.5f);
        //set label style
        Label.LabelStyle labelStyle=rm.skin.get("default", Label.LabelStyle.class);
        Label musicLabel=new Label("Music",labelStyle);
        musicLabel.setTouchable(Touchable.disabled);
        musicLabel.setFontScale(1.2f);
        final Slider musicSlider=new Slider(0.f,1.f,0.02f,false,rm.skin);
        musicSlider.setValue(rm.musicVolume);
        Label sfxLabel=new Label("Sfx",labelStyle);
        sfxLabel.setTouchable(Touchable.disabled);
        final Slider sfxSlider=new Slider(0.f,1.f,0.02f,false,rm.skin);
        sfxSlider.setValue(rm.sfxVolume);
        sfxLabel.setFontScale(1.2f);
        Label miscLabel=new Label("Misc",labelStyle);
        miscLabel.setAlignment(Align.center);
        CheckBox.CheckBoxStyle checkBoxStyle=rm.skin.get("default", CheckBox.CheckBoxStyle.class);
        final CheckBox muteMusic=new CheckBox("Mute music",checkBoxStyle);
        final CheckBox muteSfx=new CheckBox("Mute Sfx",checkBoxStyle);
        //handle event for music slider
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rm.musicVolume=musicSlider.getValue();
                if(rm.musicMute)
                {
                    rm.setMusicVolume(0f);
                }
                if(musicSlider.isDragging() && musicSlider.getValue()!=0f)
                {
                    muteMusic.setChecked(false);
                }
                if(musicSlider.isDragging() && musicSlider.getValue()==0f)
                {
                    muteMusic.setChecked(true);
                }
            }
        });
        //handle event for sfx slider
        sfxSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rm.sfxVolume=sfxSlider.getValue();
                if(!sfxSlider.isDragging() && sfxSlider.getValue()!=0f)
                {
                    muteSfx.setChecked(false);
                }
                if(!sfxSlider.isDragging() && sfxSlider.getValue()==0f)
                {
                    muteSfx.setChecked(true);
                }
            }
        });
        settingsWindow.add(musicLabel).pad(15,0,15,15).row();
        settingsWindow.add(musicSlider).size(240,20).row();
        settingsWindow.row();
        settingsWindow.add(sfxLabel).pad(15,0,15,15).row();
        settingsWindow.add(sfxSlider).size(240,20).row();
        settingsWindow.add(miscLabel).padTop(15).row();
        settingsWindow.add(muteMusic).padTop(15).size(240,50).row();
        settingsWindow.add(muteSfx).padTop(15).size(240,50).row();
        settingsWindow.row();
        muteMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rm.musicMute=muteMusic.isChecked();
                if(muteMusic.isChecked())
                    rm.setMusicVolume(0f);
                else
                {
                    rm.setMusicVolume(rm.musicVolume);
                }
            }
        });

        muteSfx.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rm.sfxMute=muteSfx.isChecked();
                if(muteMusic.isChecked()) {
                    rm.sfxMute = true;
                    rm.sfxVolume = 0f;
                }
                else
                {
                    rm.sfxMute=false;
                }
            }
        });

        //create a button to confirm campaign selection
        TextButton saveButton=new TextButton("Save",rm.skin);
        saveButton.getLabel().setFontScale(0.6f);
        saveButton.setSize(150,55);
        saveButton.setPosition(90,40,Align.center);
        saveButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });
        stage.addActor(saveButton);
        stage.addActor(settingsWindow);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        backgroundOffset++;
        //scrolling background
        batch.draw(rm.background,0,-backgroundOffset,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(rm.background,0,-backgroundOffset+Gdx.graphics.getHeight(),Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        if(backgroundOffset%Gdx.graphics.getHeight()==0)
        {
            backgroundOffset=0;
        }
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        rm.dispose();
    }

}