package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Sprite idle;
    private TextureAtlas atlas;
    private Animation<TextureRegion> playerAnimation;
    private float stateTime;
    private FitViewport viewport;
    private boolean isPaused = false;
    private float timePassed = 1000f;
    private Sprite thePlayer;
    private float playerY;
    private Array<Arrow> arrows;
    private Music music;
    private Slider musicSlider;
    private Skin uiSKin;

    private Array<Skeleton> skeletons;


    @Override
    public void create() {
        viewport = new FitViewport(8, 5);
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal("Atlas/idle.atlas"));
        arrows = new Array<>();
        skeletons = new Array<>();
        uiSKin = new Skin(Gdx.files.internal("uiskin.json"));

        musicSlider = new Slider(0f, 1f, 0.1f, false, uiSKin);


        Array<TextureAtlas.AtlasRegion> idleFrames = atlas.findRegions("Archer-Idle");

        playerAnimation = new Animation<>(0.1f, idleFrames);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP);

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = playerAnimation.getKeyFrame(stateTime);
        thePlayer = new Sprite(currentFrame);
        thePlayer.setSize(0.6f, 0.6f);
        //idle = new Sprite(idleFrames.first());
        music = Gdx.audio.newMusic(Gdx.files.internal("The Heavy - Short Change Hero.mp3"));
        music.setLooping(true);
        music.setVolume(.2f);
        music.play();
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
            draw();
        }
    }

    private void drawSkeleton(float deltaTime) {

        timePassed -= deltaTime ;
        if (timePassed <= 0) {
            var skeleton = new Skeleton(0.1f);
            skeletons.add(skeleton);
            timePassed = 1000f;
        }

        for (int i = 0; i < skeletons.size; i++) {
            batch.draw(skeletons.get(i).getTextureRegion(deltaTime),
                    skeletons.get(i).getPositionX(),
                    skeletons.get(i).getPositionY(),
                    0.5f, 0.5f);

                skeletons.get(i).UpdatePositionX(stateTime);
        }

    }

    private void draw() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //Passe au travers de l'animation du skelette avec stateTime
        stateTime += Gdx.graphics.getDeltaTime();
//        TextureRegion currentSkeletonFrame = skeletonAnimation.getKeyFrame(stateTime);
        TextureRegion currentPlayerFrame = playerAnimation.getKeyFrame(stateTime);
        thePlayer.setRegion(currentPlayerFrame);
        //
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        input();

        for (int i = arrows.size - 1; i >= 0; i--) {
            Sprite arrowSprite = arrows.get(i).getArrowSprite();
            float arrowWidth = arrowSprite.getWidth();
            arrows.get(i).getArrowSprite().draw(batch);
            arrows.get(i).UpdatePositionX();
            //Set Rectangle pour hitbox


            if (arrowSprite.getY() < -arrowWidth) arrows.removeIndex(i);
        }

        thePlayer.draw(batch);
        drawSkeleton(stateTime);
//        musicSlider.draw(batch,1);
        batch.end();
    }

    private void input() {
        var deltaTime = Gdx.graphics.getDeltaTime();
        float positionY = 0;

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {

            positionY += 1;
            thePlayer.translateY(positionY);
            playerY += positionY;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            positionY -= 1;
            thePlayer.translateY(positionY);
            playerY += positionY;

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            var Arrow = new Arrow(2f * deltaTime);
            arrows.add(Arrow);
            Arrow.setPositionY(playerY);
        }
    }

}
