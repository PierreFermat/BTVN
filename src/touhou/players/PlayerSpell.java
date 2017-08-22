package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.pools.GameObjectPools;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.Boss;
import touhou.enemies.Enemy;
import touhou.explosion.Explosion;


public class PlayerSpell extends GameObject implements PhysicalBody {
    private static final float SPEED = -10 ;
    private BoxCollider boxCollider;
    private int damage;
    private float typeBullet;
    private Player player;

    public PlayerSpell() {
        super();
        this.typeBullet = 0;
        damage = 10;
        renderer = new Animation(1,false,false,
                SpriteUtils.loadImage("assets/images/player-spells/a/1.png")
                ) ;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
    }

    public void setTypeBullet(float typeBullet) {
        this.typeBullet = typeBullet;
    }

    public float getDamage() {
        return damage;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(typeBullet, SPEED);
        hitEnemy();
        hitBoss();
        deActive();
    }

    private void deActive() {
        if(this.screenPosition.y < 0){
            this.isActive = false;
        }
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider, Enemy.class);
        if(enemy != null){
            enemy.setEnemyHP(enemy.getEnemyHP() - this.damage);
            enemy.getHit();
            this.isActive = false;
        }
    }
    private void hitBoss(){
        Boss boss = Physics.collideWith(this.boxCollider, Boss.class);
        if(boss != null){
            boss.setHP(boss.getHP() - this.damage);
            boss.getHit();
            this.isActive =false;

        }
    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }

    @Override
    public boolean isActive() {
        return false;
    }


}
