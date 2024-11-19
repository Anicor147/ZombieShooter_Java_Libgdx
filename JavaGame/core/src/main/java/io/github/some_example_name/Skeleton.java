package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.Random;

public class Skeleton {
    private SpriteBatch skeletonBatch;
    private TextureRegion textureRegion;
    private TextureAtlas skeletonAtlas;
    private Sprite skeletonSprite;
    private Animation<TextureRegion> skeletonAnimation;
    private float speed;
    private Rectangle skeletonRectangle;

    private float positionX = 7;
    private int positionY;
    private float timeInPlace;
    Random random = new Random();
    int rand;
    private int score;

    public Skeleton(float speed) {
        skeletonBatch = new SpriteBatch();
        skeletonAtlas = new TextureAtlas(Gdx.files.internal("Atlas/sWalk.atlas"));

        Array<TextureAtlas.AtlasRegion> walkFrames = skeletonAtlas.findRegions("Skeleton-Walk");
        for (TextureRegion frame : walkFrames) {
            frame.flip(true, false);
        }
        skeletonAnimation = new Animation<>(0.1f, walkFrames);
        skeletonSprite = new Sprite(skeletonAnimation.getKeyFrame(0));
        skeletonSprite.setSize(0.5f,0.5f);

        this.speed = speed;
        this.positionY = getRandomPositionY();
        this.timeInPlace = 0f;
        this.score = 1;
    }

    public int getScore (){
        return score;
    }
    public Sprite getSprite() {
        return skeletonSprite;
    }

    public TextureRegion getTextureRegion(float deltaTime) {
        return skeletonAnimation.getKeyFrame(deltaTime, true);
    }

    public float getPositionX() {
        return positionX;
    }

    public void UpdatePositionX() {
        positionX -= speed* Gdx.graphics.getDeltaTime();
        skeletonSprite.setX(positionX);
        if (positionX <= 0) positionX =0;
    }

    public int getRandomPositionY() {
        return random.nextInt(0, 5);
    }

    public int getPositionY() {
        return positionY;
    }

    public Rectangle getSkeletonRectangle() {
        return skeletonRectangle;
    }

    public void setSkeletonRectangle(Rectangle skeletonRectangle) {
        this.skeletonRectangle = skeletonRectangle;
    }

}
