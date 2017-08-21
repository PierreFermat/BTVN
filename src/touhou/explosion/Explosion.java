package touhou.explosion;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

import java.awt.*;

public class Explosion extends GameObject {
    Animation animation;
    public Explosion(){
        super();
        animation = new Animation(2,true,false,
//                SpriteUtils.loadImage("assets/images/enemies/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/1.png"),
//                SpriteUtils.loadImage("assets/images/enemies/explosion/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/3.png"),
//                SpriteUtils.loadImage("assets/images/enemies/explosion/4.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/5.png"),
//                SpriteUtils.loadImage("assets/images/enemies/explosion/6.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/7.png")
        );
        this.renderer = animation;
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (animation.isStop()){
            this.setActive(false);
        }
    }
    @Override
    public void render(Graphics2D g2d){
        super.render(g2d);
        if(animation.isStop()){
            this.isActive = false;
        }
    }
    public void reset(){
        super.reset();
        animation.reset();
    }

    public Animation getAnimation() {
        return animation;
    }
}