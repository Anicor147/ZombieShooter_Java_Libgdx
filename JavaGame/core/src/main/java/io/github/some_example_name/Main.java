package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.Console;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Sprite idle;
    private TextureAtlas atlas;
    private Animation<TextureRegion> animation;
    private TextureRegion textureRegion;
    private float stateTime;
    private FitViewport viewport;
    private boolean isPaused = false;


    @Override
    public void create() {
        viewport = new FitViewport(8,5);
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("Atlas/idle.atlas"));

        Array<TextureAtlas.AtlasRegion> idleFrames = atlas.findRegions("Archer-Idle");

        animation = new Animation<>(0.1f, idleFrames);
        animation.setPlayMode(Animation.PlayMode.LOOP);

        idle = new Sprite(idleFrames.first());
        idle.setPosition(Gdx.graphics.getWidth() / 2 - idle.getWidth() / 2, Gdx.graphics.getHeight() / 2 - idle.getHeight() / 2);
        idle.setScale(10f);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void render() {
        input();

        viewport.apply();
        stateTime += Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (!isPaused){
            //Mettre la logic et les draw ici
            draw(currentFrame);
        }
        batch.end();

    }

    private void draw(TextureRegion currentFrame){
        batch.draw(currentFrame,
                Gdx.graphics.getWidth() / 2 - currentFrame.getRegionWidth() / 2,
                Gdx.graphics.getHeight() / 2 - currentFrame.getRegionHeight() / 2,
                currentFrame.getRegionWidth() * 10f,  // Scale width by 10x
                currentFrame.getRegionHeight() * 10f  // Scale height by 10x
        );
        batch.draw(currentFrame,0,1,0.5f,0.5f);
    }

    private void input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){

            isPaused = !isPaused;
        }
    }

}
