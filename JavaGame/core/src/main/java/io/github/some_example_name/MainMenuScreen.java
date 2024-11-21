package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class MainMenuScreen implements Screen {

    final TheGame game;


    TextField username;
    public MainMenuScreen(final TheGame game) {
        this.game = game;
        username = new TextField();

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
        game.font.draw(game.batch, "Welcome to zombieShooter!!!", 220, 400); // Adjust coordinates as needed
        game.font.draw(game.batch, "Tap anywhere to begin!", 250, 100);
        username.setText("Enter Username here");
        username.setLocation(100, 200);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new Main(game));
            dispose();
        }
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

    }
}
