package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Arrow {
    private Texture arrowTexture;
    private Sprite arrowSprite;
    private float speed;
    //private float positionY;

    public Arrow(){
        arrowTexture = new Texture("Arrow.png");
        arrowSprite = new Sprite(arrowTexture);
        arrowSprite.setSize(0.5f,0.5f);

    }


    public Sprite getArrowSprite() {
        return arrowSprite;
    }
    public void setPositionY(float y){
        arrowSprite.setPosition(0,y);
    }
}
