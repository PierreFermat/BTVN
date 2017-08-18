package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
//import touhou.explosion.Explosion;
import touhou.players.Player;

import java.util.ArrayList;

public class EnemyBullet extends GameObject implements PhysicalBody {
    private static final float SPEED = 5;
    public float typebullet;
    private BoxCollider boxCollider;
    private float damage;


    public EnemyBullet(float typebullet){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png"));
        this.typebullet = typebullet;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 10;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(typebullet,SPEED);
        hitPlayer();
    }
    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setHP(player.getHP() - this.damage);
            this.isActive = false;
//            GameObject explosion = new GameObject();
//            explosion.setPosition(this.getPosition());
//            GameObject.add(explosion);

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
