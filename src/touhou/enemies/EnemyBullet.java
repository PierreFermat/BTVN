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

public class EnemyBullet extends GameObject implements PhysicalBody {
    private static final float SPEED = 5;
    public float typebullet;
    private BoxCollider boxCollider;
    private float damage;


    public EnemyBullet(){
        super();
        this.typebullet = 0;
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/cyan.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/pink.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/red.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/white.png"),
                SpriteUtils.loadImage("assets/images/enemies/bullets/yellow.png")
        );
        this.typebullet = typebullet;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 10;
    }

    public void setTypebullet(float typebullet) {
        this.typebullet = typebullet;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(typebullet,SPEED);
        hitPlayer();
        deActive();
    }
    private void deActive() {
        if(this.screenPosition.y >768){
            this.isActive = false;
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
