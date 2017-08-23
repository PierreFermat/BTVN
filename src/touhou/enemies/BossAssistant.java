package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.Animation;
import tklibs.SpriteUtils;
import touhou.players.Player;

public class BossAssistant extends GameObject implements PhysicalBody{
    private final BoxCollider boxCollider;
    private final float damage;
    private final Vector2D direction;
    private final double SPEED;
    private Player player;

    public BossAssistant(){
        super();
        this.renderer = new Animation(
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
                SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")) ;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 10;
        SPEED = 5;
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
        if (player != null){
            vector2D = player.getScreenPosition().subtract(this.getScreenPosition());
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
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setHP(player.getHP()- this.damage);
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

