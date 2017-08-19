package touhou.explosion;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

public class Explosion extends GameObject {
    Animation animation;
    public Explosion(){
        super();
        animation = new Animation(2,
                SpriteUtils.loadImage("assets/images/enemies/explosion/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/3.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/4.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/5.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/6.png"),
                SpriteUtils.loadImage("assets/images/enemies/explosion/7.png")
        );
        this.renderer = animation;
    }
    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (animation.GetOff()){
            this.setActive(false);
        }
    }

    public Animation getAnimation() {
        return animation;
    }
}