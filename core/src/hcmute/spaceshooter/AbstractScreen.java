package hcmute.spaceshooter;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AbstractScreen implements Screen {
    protected SpaceShooterGame game;
    protected ResourceManager rm;
    protected Viewport viewport;
    protected Stage stage;
    protected OrthographicCamera cam;
    public AbstractScreen(final SpaceShooterGame game,final ResourceManager rm)
    {
        this.game=game;
        this.rm=rm;
        cam=new OrthographicCamera(SpaceShooterGame.WIDTH,SpaceShooterGame.HEIGHT);
        cam.setToOrtho(false);
        viewport=new StretchViewport(SpaceShooterGame.WIDTH,SpaceShooterGame.HEIGHT);
        stage=new Stage(viewport);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act(delta);
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
    }

    public OrthographicCamera getCamera()
    {
        return cam;
    }
    public Stage getStage()
    {
        return stage;
    }
    public SpaceShooterGame getGame()
    {
        return game;
    }
    public void setFadeScreen(final Screen screen)
    {
        stage.addAction(Actions.sequence(Actions.fadeOut(0.3f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(screen);
                    }
                })));
    }
    public void setSlideScreen(final Screen screen)
    {
        stage.addAction(Actions.sequence(Actions.sequence(
                Actions.moveBy(SpaceShooterGame.WIDTH,0,0.15f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(screen);
                    }
                })
        )));
    }
}
