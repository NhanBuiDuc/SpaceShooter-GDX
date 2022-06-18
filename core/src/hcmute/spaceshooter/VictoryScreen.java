package hcmute.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class VictoryScreen implements Screen {
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    public ResourceManager rm;
    public VictoryScreen()
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
        //set label style for title
        Label.LabelStyle titleStyle=rm.skin.get("title", Label.LabelStyle.class);
        //create title label
        Label title= new Label("Victory",titleStyle);
        title.setFontScale(0.45f);
        title.setSize(200,100);
        title.setPosition(0,250);
        title.setAlignment(Align.center);
        stage.addActor(title);
        //create container for achievement
        Window.WindowStyle windowStyle=rm.skin.get("special",Window.WindowStyle.class);
        Window achievement=new Window("",windowStyle);
        achievement.setOrigin(20,150);
        achievement.setSize(360,400);
        achievement.setScale(0.5f);
        achievement.setTouchable(Touchable.disabled);
        Label.LabelStyle labelStyle=rm.skin.get("default",Label.LabelStyle.class);
        Label report=new Label(rm.bosses.get(rm.campaignIndex)+" has been defeated!",labelStyle);
        report.setWrap(true);
        report.setAlignment(Align.center);
        report.setFontScale(1.2f);
        achievement.add(report).size(300,120).row();
        Label scoreLabel=new Label("Your record",labelStyle);
        scoreLabel.setFontScale(1.2f);
        scoreLabel.setAlignment(Align.center);
        achievement.add(scoreLabel).pad(15,15,15,15).row();
        Label score=new Label("...",labelStyle);
        score.setFontScale(1.2f);
        score.setAlignment(Align.center);
        achievement.add(score).row();
        achievement.setDebug(true);
        stage.addActor(achievement);
        TextButton returnButton=new TextButton("Quit",rm.skin);
        returnButton.getLabel().setFontScale(0.45f);
        returnButton.setSize(125,70);
        returnButton.setPosition(-5,0);
        returnButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
            }
        });
        TextButton continueButton=new TextButton("Continue",rm.skin);
        continueButton.getLabel().setFontScale(0.45f);
        continueButton.setSize(125,70);
        continueButton.setPosition(85,0);
        continueButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new SelectCampaignScreen());
            }
        });
        stage.addActor(returnButton);
        stage.addActor(continueButton);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f,0f,0f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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