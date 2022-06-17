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
    public int backgroundOffset=0;
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
        Label title=new Label("Space Invaders",titleStyle);
        title.setFontScale(0.35f);
        title.setWrap(true);
        title.setSize(220,300);
        title.setPosition(-10,100);
        title.setAlignment(Align.center);
        SequenceAction actions=new SequenceAction(Actions.sequence(Actions.fadeIn(1f),Actions.delay(2.5f),Actions.fadeOut(2.5f),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        })));
        title.addAction(actions);
        stage.addActor(title);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        backgroundOffset++;
        //scrolling background
        batch.draw(rm.splash,-backgroundOffset,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(rm.splash,-backgroundOffset+Gdx.graphics.getWidth(),0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
