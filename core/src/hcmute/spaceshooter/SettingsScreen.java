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
    }
    @Override
    public void show() {
        //play theme
        rm.settingsTheme.setVolume(rm.musicVolume);
        rm.settingsTheme.setLooping(true);
        rm.settingsTheme.play();
        //set label style for title
        Label.LabelStyle titleStyle=rm.skin.get("title", Label.LabelStyle.class);
        //create title label
        Label title= new Label("Settings",titleStyle);
        title.setFontScale(0.35f);
        title.setSize(200,100);
        title.setPosition(115,280,Align.center);
        stage.addActor(title);
        //create container for setting options
        Window.WindowStyle windowStyle=rm.skin.get("special",Window.WindowStyle.class);
        Window settingsWindow=new Window("",windowStyle);
        settingsWindow.setOrigin(20,120);
        settingsWindow.setSize(360,400);
        settingsWindow.setScale(0.5f);
        //set label style
        Label.LabelStyle labelStyle=rm.skin.get("default", Label.LabelStyle.class);
        //create label for music volume slider
        Label musicLabel=new Label("Music",labelStyle);
        musicLabel.setTouchable(Touchable.disabled);
        musicLabel.setFontScale(1.2f);
        //create slider to adjust music volume
        final Slider musicSlider=new Slider(0.f,1.f,0.02f,false,rm.skin);
        //create label for sfx volume slider
        Label sfxLabel=new Label("Sfx",labelStyle);
        sfxLabel.setTouchable(Touchable.disabled);
        sfxLabel.setFontScale(1.2f);
        //create slider to adjust sfx volume
        final Slider sfxSlider=new Slider(0.f,1.f,0.02f,false,rm.skin);
        //create misc label
        Label miscLabel=new Label("Misc",labelStyle);
        miscLabel.setAlignment(Align.center);
        miscLabel.setFontScale(1.2f);
        CheckBox.CheckBoxStyle checkBoxStyle=rm.skin.get("default", CheckBox.CheckBoxStyle.class);
        //create checkboxes for muting music and sfx
        final CheckBox muteMusic=new CheckBox("Mute music",checkBoxStyle);
        final CheckBox muteSfx=new CheckBox("Mute Sfx",checkBoxStyle);
        //handle event for music slider
        musicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rm.musicVolume=musicSlider.getValue();
                if(musicSlider.isDragging())
                {
                    if(musicSlider.getValue()==0f)
                    {
                        muteMusic.setChecked(true);
                        rm.setMusicVolume(0f);
                    }
                    else
                    {
                        muteMusic.setChecked(false);
                        rm.setMusicVolume(musicSlider.getValue());
                    }
                }
            }
        });
        //handle event for sfx slider
        sfxSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                rm.sfxVolume=sfxSlider.getValue();
                if(sfxSlider.isDragging())
                {
                    if(sfxSlider.getValue()==0f)
                    {
                        muteSfx.setChecked(true);
                        rm.setSfxVolume(0f);
                    }
                    else
                    {
                        muteSfx.setChecked(false);
                        rm.setSfxVolume(sfxSlider.getValue());
                    }
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
        //handle event for mute music checkbox
        muteMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(muteMusic.isChecked())
                    rm.setMusicVolume(0f);
                else
                {
                    rm.setMusicVolume(musicSlider.getValue());
                }
            }
        });
        //handle event for mute sound effects checkbox
        muteSfx.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(muteMusic.isChecked()) {
                    rm.setSfxVolume(0f);
                }
                else
                {
                    rm.setSfxVolume(sfxSlider.getValue());
                }
            }
        });
        //create a button to confirm campaign selection
        TextButton saveButton=new TextButton("Save",rm.skin);
        saveButton.getLabel().setFontScale(0.6f);
        saveButton.setSize(150,55);
        saveButton.setPosition(100,40,Align.center);
        saveButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                rm.settingsTheme.stop();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });
        //set saved settings
        musicSlider.setValue(rm.musicVolume);
        sfxSlider.setValue(rm.sfxVolume);
        if(rm.musicVolume==0)
        {
            muteMusic.setChecked(true);
        }
        if(rm.sfxVolume==0)
        {
            muteSfx.setChecked(true);
        }
        stage.addActor(saveButton);
        stage.addActor(settingsWindow);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(rm.settingsBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
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