package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.pools.GameObjectPools;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;
import touhou.explosion.Explosion;
import touhou.inputs.InputManager;
import touhou.players.Player;


public class BallSpell extends GameObject {
    private static final float SPEED = 5;
    public float typebullet;
    private BoxCollider boxCollider;
    private float damage;
    private InputManager inputManager;
    private Vector2D direction;


    public BallSpell(){
        super();
        this.renderer = new Animation(SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png"));
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 2;
        direction = new Vector2D(0, -SPEED);


    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(direction);
        shoot();
        hitEnemy();
        deActive();
    }
    private void shoot() {
        Vector2D vector2D;
        Enemy enemy = GameObject.MinimumDistance(this.screenPosition, Enemy.class);
        if (enemy != null){
            vector2D = enemy.getScreenPosition().subtract(this.getScreenPosition());
            direction.x = vector2D.x/vector2D.magnitude()*SPEED;
            direction.y = vector2D.y/vector2D.magnitude()*SPEED;
    }
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
            Explosion explosion = GameObjectPools.recycle(Explosion.class);
            explosion.getPosition().set(enemy.getScreenPosition());
            explosion.getAnimation().setOff(false);
            this.isActive = false;
        }
    }


    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
