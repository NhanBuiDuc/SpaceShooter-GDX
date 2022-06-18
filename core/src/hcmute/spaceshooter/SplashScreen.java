package hcmute.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SplashScreen implements Screen {
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    public ResourceManager rm;
    public SplashScreen()
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
        Label.LabelStyle titleStyle=rm.skin.get("title", Label.LabelStyle.class);
        Label title=new Label("Space Shooter",titleStyle);
        title.setFontScale(0.4f);
        title.setWrap(true);
        title.setSize(220,300);
        title.setPosition(-10,50);
        title.setAlignment(Align.center);
        SequenceAction actions=new SequenceAction(Actions.sequence(Actions.fadeIn(1f),Actions.delay(1f),Actions.fadeOut(1f),Actions.run(new Runnable() {
            @Override
            public void run() {
                rm.splashTheme.stop();
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        })));
        rm.splashTheme.setVolume(rm.sfxVolume);
        rm.splashTheme.play();
        title.addAction(actions);
        stage.addActor(title);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(rm.splashBackground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,false);
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
