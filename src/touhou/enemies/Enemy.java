package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;

import java.util.Random;
import java.util.concurrent.Callable;

public class Enemy extends GameObject implements PhysicalBody {
    private static final float SPEED = 2;
    private FrameCounter frameCounter;
    private BoxCollider  boxCollider;
    private float EnemyHP;
    private  float damage;
    private Random random = new Random();
    private int typeEnemy;

    public Enemy(){
        super();
        frameCounter = new FrameCounter(30);
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 100;
        typeEnemy = random.nextInt(3);
        if(typeEnemy == 0){
            renderer = new Animation(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")) ;
            setEnemyHP(10);
        }
        if(typeEnemy == 2){
            renderer = new Animation(SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")) ;
            setEnemyHP(10);
        }
        if(typeEnemy == 1){
            renderer = new Animation(SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/pink/2.png"),
                    SpriteUtils.loadImage("assets/images/enemies/level0/pink/3.png")) ;
            setEnemyHP(20);
        }
    }


    public float getDamage() {
        return damage;
    }

    //controller
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        fly();
        shoot();
        hitPlayer();
        if (this.getEnemyHP() <= 0){
            this.isActive = false;
        }
    }


    private void shoot() {
        if(typeEnemy == 0){
            bullet_0();
        }
        if(typeEnemy == 1){
            bullet_1();
        }
        if(typeEnemy == 2){
            bullet_0();
        }

    }
    private void bullet_1(){
        if(frameCounter.run()) {
            CreatBullet(1,10, 2);
            CreatBullet(1,10,0);
            CreatBullet(1,10, -2);
            frameCounter.reset();
        }
    }
    private void bullet_0(){
        if(frameCounter.run()) {

            CreatBullet(1,10,0);

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

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    public float getEnemyHP() {
        return EnemyHP;
    }

    public void setEnemyHP(float enemyHP) {
        EnemyHP = enemyHP;
    }
    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setHP(player.getHP() - this.damage);
            this.isActive = false;
        }
    }

}
