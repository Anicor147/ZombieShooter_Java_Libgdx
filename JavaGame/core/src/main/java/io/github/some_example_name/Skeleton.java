package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Skeleton {
    private SpriteBatch skeletonBatch;
    private TextureRegion textureRegion;
    private TextureAtlas skeletonAtlas;
    private Sprite skeletonSprite;
    private Animation<TextureRegion> skeletonAnimation;
    private float speed;

    private int positionX = 7;
    private int positionY;
    private float timeInPlace ;
    Random random = new Random();
    int rand ;
    public Skeleton( float speed) {
        skeletonBatch = new SpriteBatch();
        skeletonAtlas = new TextureAtlas(Gdx.files.internal("Atlas/sWalk.atlas"));
        Array<TextureAtlas.AtlasRegion> walkFrames = skeletonAtlas.findRegions("Skeleton-Walk");
        for (TextureRegion frame : walkFrames) {
            frame.flip(true, false);
        }
        skeletonAnimation = new Animation<>(0.1f, walkFrames);
        skeletonAnimation.setPlayMode(Animation.PlayMode.LOOP);
        this.speed = speed;
        this.positionY = getRandomPositionY();
        this.timeInPlace = 0f;
    }

    public TextureRegion getTextureRegion(float deltaTime) {
        return skeletonAnimation.getKeyFrame(deltaTime, true);
    }

    public int getPositionX() {
        return positionX;
    }

    public void UpdatePositionX(float deltaTime) {
        timeInPlace += deltaTime;

        if (timeInPlace >= 2500f) {
            positionX -= speed ;
            if (positionX < 0) positionX = 0;
            timeInPlace = 0f;
        }
    }

    public int getRandomPositionY(){
        return random.nextInt(0,5);
    }
    public int getPositionY(){
        return positionY;
    }
}
