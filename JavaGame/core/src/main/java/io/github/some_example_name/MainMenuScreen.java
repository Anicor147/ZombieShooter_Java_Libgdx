package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class MainMenuScreen implements Screen {

    final TheGame game;

    public MainMenuScreen(final TheGame game) {
        this.game = game;

      }

      private void Pusherrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr(){}
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);

        game.batch.begin();
        float scale = game.viewport.getWorldHeight() / Gdx.graphics.getHeight();
        game.font.setColor(Color.BLUE);
        game.font.draw(game.batch, "Welcome to zombieShooter!!!", 1, 3); // Adjust coordinates as needed
        game.font.draw(game.batch, "Tap anywhere to begin!", 2, 4);
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
