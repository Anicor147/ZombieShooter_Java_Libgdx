package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

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

    @Override
    public void create() {
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
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(currentFrame,
                Gdx.graphics.getWidth() / 2 - currentFrame.getRegionWidth() / 2,
                Gdx.graphics.getHeight() / 2 - currentFrame.getRegionHeight() / 2,
                currentFrame.getRegionWidth() * 10f,  // Scale width by 10x
                currentFrame.getRegionHeight() * 10f  // Scale height by 10x
        );
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        atlas.dispose();
        image.dispose();
    }
}
