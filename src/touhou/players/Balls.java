package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.BallSpell;
import touhou.enemies.EnemyBullet;
import touhou.inputs.InputManager;

import java.util.Random;
import java.util.Vector;

public class Balls extends GameObject implements PhysicalBody{

    private FrameCounter frameCounter;
    private BoxCollider boxCollider;
    private InputManager inputManager;
    private boolean upSpell;

    public Balls(float x,float y){
        super();
        this.position.set(x,y);
        frameCounter = new FrameCounter(20);
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/sphere/0.png")) ;
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        shoot();

    }


    private void shoot() {

            bullet_1();
    }

    private void bullet_1() {
        if(frameCounter.run()) {
            CreatBullet(1,10,0);
            frameCounter.reset();
        }
    }



    private void CreatBullet(float x,float y, float typebullet) {
        BallSpell newBullet = new BallSpell(typebullet);
        newBullet.getPosition().set(this.screenPosition.add(x, y));
        GameObject.add(newBullet);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }


}