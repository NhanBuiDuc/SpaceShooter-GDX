package hcmute.spaceshooter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hcmute.spaceshooter.Episode.Episode1;
import hcmute.spaceshooter.Episode.Episode2;
import hcmute.spaceshooter.Episode.Episode3;

public class SelectCampaignScreen implements Screen {
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    private int backgroundOffset=0;
    public ResourceManager rm;
    public SelectCampaignScreen()
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
        rm.selectCampaignTheme.setVolume(rm.musicVolume);
        rm.selectCampaignTheme.setLooping(true);
        rm.selectCampaignTheme.play();
        //set label style for title
        Label.LabelStyle titleStyle=rm.skin.get("title", Label.LabelStyle.class);
        Label title= new Label("Select Campaign",titleStyle);
        title.setFontScale(0.3f);
        title.setSize(200,100);
        title.setPosition(0,280);
        title.setAlignment(Align.center);
        title.setWrap(true);
        stage.addActor(title);
        //set container for the scroll pane and objective description
        Window.WindowStyle scrollTableStyle= rm.skin.get("special", Window.WindowStyle.class);
        Window scrollTable=new Window("",scrollTableStyle);
        scrollTable.setOrigin(20,80);
        scrollTable.setSize(360,500);
        scrollTable.setScale(0.5f);
        Array<TextButton> campaignButtons=new Array<TextButton>();
        //set default label style
        Label.LabelStyle labelStyle=rm.skin.get("default", Label.LabelStyle.class);
        //set style for boss window
        Window.WindowStyle bossWindowStyle=rm.skin.get("default",Window.WindowStyle.class);
        //create boss window
        Window bossWindow=new Window("Boss",bossWindowStyle);
        bossWindow.getTitleLabel().setFontScale(1f);
        bossWindow.getTitleLabel().setAlignment(Align.center);
        //create boss description window
        final Label bossDesc=new Label("...",labelStyle);//have not selected any campaigns
        bossDesc.setAlignment(Align.center);
        bossDesc.setFontScale(1.2f);
        bossWindow.add(bossDesc);
        bossWindow.pack();
        //create a container for a group of labels and button
        Table selectionContainer=new Table();
        for (int i=0;i<rm.campaigns.size;i++)
        {
            final int index=i;
            //create a group of labels and button: campaign name and player's record
            Group g=new Group();
            Label name=new Label(rm.campaigns.get(index).name,labelStyle);
            name.setPosition(50,80);
            name.setFontScale(1.4f);
            name.setTouchable(Touchable.disabled);
            name.setAlignment(Align.left);
            Label record=new Label("Record: "+rm.campaigns.get(rm.campaignIndex).record,labelStyle);
            record.setPosition(50,50);
            record.setFontScale(0.8f);
            record.setTouchable(Touchable.disabled);
            record.setAlignment(Align.left);
            final TextButton b=new TextButton("",rm.skin);
            campaignButtons.add(b);
            //change boss description to corresponding campaign
            b.addListener(new ClickListener()
            {
                @Override
                public void clicked(InputEvent event,float x,float y)
                {
                    rm.campaignIndex=index;
                    bossDesc.setText(rm.campaigns.get(rm.campaignIndex).boss);
                    System.out.println(rm.campaignIndex);
                }
            });
            //add group to the selection container
            b.setFillParent(true);
            g.addActor(b);
            g.addActor(name);
            g.addActor(record);
            selectionContainer.add(g).pad(-15).size(300,140).row();
        }
        selectionContainer.pack();
        selectionContainer.setTransform(false);
        selectionContainer.top();
        //create a scroll pane to contain each selection container
        ScrollPane scrollPane=new ScrollPane(selectionContainer,rm.skin);
        scrollPane.setScrollingDisabled(true,false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.layout();
        //add scroll pane and boss window to the parent container
        scrollTable.add(scrollPane).padTop(10).size(300,350).fill().row();
        scrollTable.add(bossWindow).pad(20,0,0,0).size(350,100).row();
        stage.addActor(scrollTable);
        //create a button to confirm campaign selection
        TextButton playButton=new TextButton("Enter",rm.skin);
        playButton.getLabel().setFontScale(0.6f);
        playButton.setSize(130,60);
        playButton.setPosition(80,-5);
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y)
            {
                rm.selectCampaignTheme.stop();
                switch (rm.campaignIndex){
                    case 0:
                        GameScreen gameScreen1 = new GameScreen();
                        gameScreen1.episode = new Episode1(gameScreen1.upgradeDroppingItemList, gameScreen1.meteorList,
                                gameScreen1.enemyBossLaserList, gameScreen1.enemyBossesList, gameScreen1.enemyShipList);
                        ((Game)Gdx.app.getApplicationListener()).setScreen(gameScreen1);
                        break;
                    case 1:
                        GameScreen gameScreen2 = new GameScreen();
                        gameScreen2.episode = new Episode2(gameScreen2.upgradeDroppingItemList, gameScreen2.meteorList,
                                gameScreen2.enemyBossLaserList, gameScreen2.enemyBossesList, gameScreen2.enemyShipList);
                        ((Game)Gdx.app.getApplicationListener()).setScreen(gameScreen2);
                        break;
                    case 2:
                        GameScreen gameScreen3 = new GameScreen();
                        gameScreen3.episode = new Episode3(gameScreen3.upgradeDroppingItemList, gameScreen3.meteorList,
                                gameScreen3.enemyBossLaserList, gameScreen3.enemyBossesList, gameScreen3.enemyShipList);
                        ((Game)Gdx.app.getApplicationListener()).setScreen(gameScreen3);
                        break;
                    default:
                        Gdx.app.exit();
                }
            }
        });
        //create a button to confirm campaign selection
        TextButton returnButton=new TextButton("Return",rm.skin);
        returnButton.getLabel().setFontScale(0.6f);
        returnButton.setSize(130,60);
        returnButton.setPosition(-10,-5);
        returnButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y)
            {
                stage.addAction(Actions.sequence(Actions.fadeOut(1),Actions.run(new Runnable() {
                    //return to menu screen
                    @Override
                    public void run() {
                        rm.selectCampaignTheme.stop();
                        ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen());
                    }
                })));

            }
        });
        stage.addActor(playButton);
        stage.addActor(returnButton);
        stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1)));
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