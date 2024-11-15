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
    private TextureRegion theSkeleton;
    private Animation<TextureRegion> playerAnimation;
    private Animation<TextureRegion> skeletonAnimation;
    private float stateTime;
    private FitViewport viewport;
    private boolean isPaused = false;
    private int timer;
    private Sprite thePlayer;


    @Override
    public void create() {
        viewport = new FitViewport(8, 5);
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("Atlas/idle.atlas"));

        Array<TextureAtlas.AtlasRegion> idleFrames = atlas.findRegions("Archer-Idle");

        playerAnimation = new Animation<>(0.1f, idleFrames);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = playerAnimation.getKeyFrame(stateTime);
        thePlayer = new Sprite(currentFrame);
        thePlayer.setSize(0.6f,0.6f);


        //idle = new Sprite(idleFrames.first());

        createSkeleton();
        theSkeleton = skeletonAnimation.getKeyFrame(stateTime);
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

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {

            isPaused = !isPaused;
        }
        if (!isPaused) {
            //Mettre la logic et les draw ici
            input();
            draw();
        }
    }

    private void drawSkeleton(TextureRegion currentSkeletonFrame) {
           /* Random random = new Random();
            int rand = random.nextInt(0, 5);*/
            batch.draw(currentSkeletonFrame, 7, 0, 0.5f, 0.5f);
    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //Passe au travers de l'animation du skelette avec stateTime
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentSkeletonFrame = skeletonAnimation.getKeyFrame(stateTime);
        TextureRegion currentPlayerFrame = playerAnimation.getKeyFrame(stateTime);
        thePlayer.setRegion(currentPlayerFrame);
        //
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        thePlayer.draw(batch);
        drawSkeleton(currentSkeletonFrame);
        batch.end();
    }

    private void input() {
        float speed = 2f;
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)){

            thePlayer.translateY(speed * deltaTime);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)){
            thePlayer.translateY(-speed * deltaTime);
        }
    }

}
