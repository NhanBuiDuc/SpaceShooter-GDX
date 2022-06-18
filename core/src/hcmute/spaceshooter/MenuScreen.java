package hcmute.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MenuScreen implements Screen{
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    public ResourceManager rm;
    public MenuScreen()
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
        rm.menuTheme.setVolume(rm.musicVolume);
        rm.menuTheme.setLooping(true);
        rm.menuTheme.play();
        //create a container for the buttons and title
        Table table=new Table();
        table.top();
        table.setFillParent(true);
        table.padTop(80f);
        //create title
        Label.LabelStyle labelStyle=rm.skin.get("title", Label.LabelStyle.class);
        Label title=new Label("Space Shooter",labelStyle);
        title.setFontScale(0.4f);
        title.setAlignment(Align.center);
        title.setWrap(true);
        //create buttons
        TextButton.TextButtonStyle textButtonStyle=rm.skin.get("toggle", TextButton.TextButtonStyle.class);
        TextButton playButton=new TextButton("Play",textButtonStyle);
        playButton.getLabel().setFontScale(0.5f);
        TextButton settingsButton=new TextButton("Settings",textButtonStyle);
        settingsButton.getLabel().setFontScale(0.5f);
        TextButton exitButton=new TextButton("Exit",textButtonStyle);
        exitButton.getLabel().setFontScale(0.5f);
        //handle click event for play button
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y)
            {
                //change to the game screen
                stage.addAction(Actions.sequence(Actions.fadeOut(1),Actions.run(new Runnable() {
                    @Override
                    public void run()
                    {
                        rm.menuTheme.stop();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new SelectCampaignScreen());

                    }
                })));
            }
        });
        //handle click event for settings button
        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y)
            {
                //change to the settings screen
                stage.addAction(Actions.sequence(Actions.moveTo(-stage.getWidth(),0,.5f),Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        rm.menuTheme.stop();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());

                    }
                })));
            }
        });
        //handle click event for exit button
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y)
            {
                //exit application
                Gdx.app.exit();
            }
        });
        //add buttons and title to the container
        table.add(title).width(185f).padBottom(-10).row();
        table.add(playButton).size(200,60).padBottom(-20).row();
        table.add(settingsButton).size(200,60).padBottom(-20).row();
        table.add(exitButton).size(200,60).row();
        stage.addActor(table);
        stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1)));

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(rm.menuBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
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