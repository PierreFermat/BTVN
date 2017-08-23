package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.pools.GameObjectPools;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
//import touhou.explosion.Explosion;
import touhou.explosion.Explosion;
import touhou.players.Player;

import java.util.ArrayList;

import static java.lang.Math.*;

public class EnemyBullet extends GameObject implements PhysicalBody {
    private float SPEED = 3;
    private final Vector2D velocity;
    private Enemy enemy;
    private double typeBullet;
    private BoxCollider boxCollider;
    private float damage;
    private double speedOther;
    private final static double p = PI;


    public EnemyBullet(){
        super();
        this.typeBullet = 0;
        speedOther = 0;
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/cyan.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/white.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/yellow.png")
        );
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 10;
        velocity = new Vector2D();
    }

    public void setSPEED(float SPEED) {
        this.SPEED = SPEED;
    }

    public void setSpeedOther(double speedOther) {
        this.speedOther = speedOther;
    }

    public void setTypeBullet(double typeBullet) {
        this.typeBullet = typeBullet;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(SPEED*cos(typeBullet*p), SPEED* sin(typeBullet*p) + speedOther);
        hitPlayer();
        deActive();
    }
    private void deActive() {
        if (this.screenPosition.y > 768) {
            this.isActive = false;
        } else {
            if(this.screenPosition.y < 0){
                this.isActive = false;
            }
        }
    }
    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setHP(player.getHP() - this.damage);
            this.isActive = false;

        }
    }



    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
