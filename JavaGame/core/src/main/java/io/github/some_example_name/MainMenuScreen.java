package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;

public class MainMenuScreen implements Screen {

    final TheGame game;
    private Stage stage;
    private Skin skin;

    TextField usernameTF;
    TextField passwordTF;

    public MainMenuScreen(final TheGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage( new FitViewport(800, 600));
        usernameTF = new TextField("Enter a Username", skin);
        passwordTF = new TextField("Enter a Password", skin);
        usernameTF.setPosition(50, 100);
        usernameTF.setSize(200, 40);
        passwordTF.setPosition(50,50 );
        passwordTF.setSize(200,40);
        TextButton button = new TextButton("Login", skin);
        button.setPosition(400, 75);
        button.setSize(150, 50);

        usernameTF.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (focused) {
                    usernameTF.setText("");
                }
            }
        });

        passwordTF.addListener(new FocusListener() {
            @Override
            public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                if (focused) {
                    passwordTF.setText("");
                }
            }
        });
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //manque juste la logic avec base de donnée
                game.setScreen(new Main(game));
            }
        });

        stage.addActor(passwordTF);
        stage.addActor(usernameTF);
        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.GRAY);

//        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
//        game.viewport.apply();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.font.getData().setScale(1f);
        game.font.setColor(Color.BLUE);
        game.font.draw(game.batch, "Welcome to zombieShooter!!!", 220, 400);
        game.batch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
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

    }

}
