package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.pools.GameObjectPools;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.BallSpell;
import touhou.enemies.EnemyBullet;
import touhou.inputs.InputManager;

import java.util.Random;
import java.util.Vector;

public class Balls extends GameObject implements PhysicalBody{

    private FrameCounter frameCounter;
    private BoxCollider boxCollider;
    private InputManager inputManager;
    private boolean upSpell;
    private Animation animation;

    public Balls(float x,float y){
        super();
        this.position.set(x,y);
        frameCounter = new FrameCounter(30);
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        this.animation = new Animation(
                SpriteUtils.loadImage("assets/images/sphere/0.png"),
                SpriteUtils.loadImage("assets/images/sphere/1.png"),
                SpriteUtils.loadImage("assets/images/sphere/2.png"),
                SpriteUtils.loadImage("assets/images/sphere/3.png")) ;
        this.renderer = animation;
        CreatBullet();
    }
    public void setReverse(boolean reverse){
        this.animation.setReverse(reverse);
    }

    private void CreatBullet() {
        BulletSpawners spellSpawner = new BulletSpawners();
        children.add(spellSpawner);
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
