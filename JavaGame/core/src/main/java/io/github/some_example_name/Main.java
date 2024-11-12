package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private SpriteBatch skeletonBatch;
    private Sprite idle;
    private TextureAtlas atlas;
    private TextureAtlas skeletonAtlas;
    private Animation<TextureRegion> playerAnimation;
    private Animation<TextureRegion> skeletonAnimation;
    private float stateTime;
    private FitViewport viewport;
    private boolean isPaused = false;
    private int timer;


    @Override
    public void create() {
        viewport = new FitViewport(8, 5);
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("Atlas/idle.atlas"));

        Array<TextureAtlas.AtlasRegion> idleFrames = atlas.findRegions("Archer-Idle");

        playerAnimation = new Animation<>(0.1f, idleFrames);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP);

        idle = new Sprite(idleFrames.first());

        createSkeleton();
    }

    private void createSkeleton() {

        skeletonBatch = new SpriteBatch();
        skeletonAtlas = new TextureAtlas(Gdx.files.internal("Atlas/sWalk.atlas"));

        Array<TextureAtlas.AtlasRegion> walkFrames = skeletonAtlas.findRegions("Skeleton-Walk");
        //Flip le sprite du skelete vers la gauche
        for (TextureRegion frame : walkFrames) {
            frame.flip(true, false);
        }

        skeletonAnimation = new Animation<>(0.1f, walkFrames);
        skeletonAnimation.setPlayMode(Animation.PlayMode.LOOP);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();

        viewport.apply();
        stateTime += Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        TextureRegion currentFrame = playerAnimation.getKeyFrame(stateTime);
        TextureRegion currentSkeletonFrame = skeletonAnimation.getKeyFrame(stateTime);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        if (!isPaused) {
            //Mettre la logic et les draw ici
            draw(currentFrame);
            drawSkeleton(currentSkeletonFrame);
        }
        batch.end();

    }

    private void drawSkeleton(TextureRegion currentFrame) {
            Random random = new Random();
            int rand = random.nextInt(0, 5);
            batch.draw(currentFrame, 7, rand, 0.5f, 0.5f);
    }

    private void draw(TextureRegion currentFrame) {
     /*   batch.draw(currentFrame,
                Gdx.graphics.getWidth() / 2 - currentFrame.getRegionWidth() / 2,
                Gdx.graphics.getHeight() / 2 - currentFrame.getRegionHeight() / 2,
                currentFrame.getRegionWidth() * 10f,  // Scale width by 10x
                currentFrame.getRegionHeight() * 10f  // Scale height by 10x
        );*/
        batch.draw(currentFrame, 0, 1, 0.5f, 0.5f);
    }

    private void input() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {

            isPaused = !isPaused;
        }
    }

}
