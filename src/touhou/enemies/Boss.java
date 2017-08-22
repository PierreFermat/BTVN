package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.pools.GameObjectPools;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;
import touhou.explosion.Explosion;
import touhou.players.Player;

public class Boss extends GameObject implements PhysicalBody {
    private Animation animation;
    private int HP;
    private int damage;
    private int SPEED;
    private FrameCounter TimeAttack;
    private FrameCounter frameCounter;
    private FrameCounter counterCounter;
    private BoxCollider boxCollider;
    private int bulletlock;
    public Boss(){
        super();
        boxCollider = new BoxCollider(40,40);
        children.add(boxCollider);
        animation = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png")
        );
        this.renderer = animation;
        TimeAttack = new FrameCounter(3);
        frameCounter = new FrameCounter(6);
        HP = 1000;
        bulletlock = 50;
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        fly();
        shoot();
        if (this.getHP() <= 0){
            this.isActive = false;
        }
    }


    private void fly() {
    }
    private void shoot() {
        if (bulletlock >= 0) {
            if(bulletlock >30){
                if (frameCounter.run()) {
                    CreatBullet(0, 0, 0, 0,5);
                    CreatBullet(0, 0, 1, 0,5);
                    CreatBullet(0, 0, 0.5, 0,5);
                    CreatBullet(0, 0, -0.5, 0,5);
                    CreatBullet(0, 0, 0.25, 0,5);
                    CreatBullet(0, 0, -0.25, 0,5);
                    CreatBullet(0, 0, 0.75, 0,5);
                    CreatBullet(0, 0, -0.75, 0,5);
                    CreatBullet(0, 0, 0.125, 0,5);
                    CreatBullet(0, 0, 0.375, 0,5);
                    CreatBullet(0, 0, 0.625, 0,5);
                    CreatBullet(0, 0, 0.875, 0,5);
                    CreatBullet(0, 0, -0.125, 0,5);
                    CreatBullet(0, 0, -0.375, 0,5);
                    CreatBullet(0, 0, -0.625, 0,5);
                    CreatBullet(0, 0, -0.875, 0,5);
                    frameCounter.reset();
                }
            }else{
                if (TimeAttack.run()) {
                    CreatBullet(0, 0, 0, 0, 3);
                    CreatBullet(0, 0, 1, 0, 3);
                    CreatBullet(0, 0, 0.5, 0, 3);
                    CreatBullet(0, 0, -0.5, 0, 3);
                    CreatBullet(0, 0, 0.25, 0, 3);
                    CreatBullet(0, 0, -0.25, 0, 3);
                    CreatBullet(0, 0, 0.75, 0, 3);
                    CreatBullet(0, 0, -0.75, 0, 3);
                    CreatBullet(0, 0, 0.125, 0, 3);
                    CreatBullet(0, 0, 0.375, 0, 3);
                    CreatBullet(0, 0, 0.625, 0, 3);
                    CreatBullet(0, 0, 0.875, 0, 3);
                    CreatBullet(0, 0, -0.125, 0, 3);
                    CreatBullet(0, 0, -0.375, 0, 3);
                    CreatBullet(0, 0, -0.625, 0, 3);
                    CreatBullet(0, 0, -0.875, 0, 3);
                    TimeAttack.reset();

                }

            }
            bulletlock --;
            if(bulletlock <= 0){
                bulletlock = -100;
            }

        }
        else {
            bulletlock ++;
            if(bulletlock >= -1){
                bulletlock = 50;
            }
        }
    }

    private void CreatBullet(float x,float y, double typeBullet, double SpeedOther,float SPEED) {
        EnemyBullet newBullet = GameObjectPools.recycle(EnemyBullet.class);
        newBullet.setTypeBullet(typeBullet);
        newBullet.setSpeedOther(SpeedOther);
        newBullet.setSPEED(SPEED);
        newBullet.getPosition().set(this.position.add(x, y));
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
    public boolean isActive() {
        return this.isActive;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    public void  getHit(){
        Explosion explosion = new Explosion();
        explosion.getPosition().set(this.position);
        explosion.getPosition().set(this.screenPosition);
        GameObject.add(explosion);
    }
}