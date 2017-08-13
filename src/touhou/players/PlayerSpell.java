package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;


public class PlayerSpell extends GameObject implements PhysicalBody {
    private BoxCollider boxCollider;
    private float damage;

    public PlayerSpell() {
        super();
        damage = 5;
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/1.png"));
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
    }

    public float getDamage() {
        return damage;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        position.addUp(0, -10);
        hitEnemy();
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWithEnemy(this.boxCollider);

        if(enemy != null) {
            enemy.setActive(false);
        }


    }

    @Override
    public BoxCollider getBoxCollider() {
        return boxCollider;
    }
}
