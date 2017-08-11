package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Enemy extends GameObject {
    private static final float SPEED = 2;
    private FrameCounter frameCounter;

    public Enemy(){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png")) ;
        frameCounter = new FrameCounter(30);
    }

    //controller
    public void run(){
        super.run();
        fly();
        shoot();
    }

    private void shoot() {

        if(frameCounter.run()) {
            CreatBullet(1,10, 2);
           CreatBullet(1,10,0);
           CreatBullet(1,10,-2);
            frameCounter.reset();
        }
    }

    private void CreatBullet(float x,float y, float typebullet) {
        EnemyBullet newBullet = new EnemyBullet(typebullet);
        newBullet.getPosition().set(this.position.add(x, y));
        GameObject.add(newBullet);
    }

    private void fly() {
        position.addUp(0,SPEED);
    }

}
