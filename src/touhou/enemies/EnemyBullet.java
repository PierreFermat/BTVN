package touhou.enemies;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {
    private static final float SPEED = 5;
    public float typebullet;

    public EnemyBullet(float typebullet){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
        this.typebullet = typebullet;
    }

    public void run(){
        position.addUp(typebullet,SPEED);
    }
}
