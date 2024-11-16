package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

public class Arrow {
    private Texture arrowTexture;
    private Sprite arrowSprite;
    private float speed;
    private Rectangle arrowRectangle;
    //private float positionY;

    public Arrow(float arrowSpeed){
        arrowTexture = new Texture("Arrow.png");
        arrowSprite = new Sprite(arrowTexture);
        arrowSprite.setSize(0.5f,0.5f);
        speed = arrowSpeed;


    }


    public Sprite getArrowSprite() {
        return arrowSprite;
    }
    public void setPositionY(float y){
        arrowSprite.setPosition(0,y);
    }
    public void UpdatePositionX(){
        arrowSprite.translateX(speed);
    }

    public void setArrowRectangle(Rectangle arrowRectangle) {
        this.arrowRectangle = arrowRectangle;
    }
}
