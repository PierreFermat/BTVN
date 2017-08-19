package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.inputs.InputManager;
import touhou.players.Player;


public class BallSpell extends GameObject {
    private static final float SPEED = -5;
    public float typebullet;
    private BoxCollider boxCollider;
    private float damage;
    private InputManager inputManager;


    public BallSpell(){
        super();
        this.typebullet = 0;
        this.renderer = new Animation(SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/1.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/2.png"),
                SpriteUtils.loadImage("assets/images/sphere-bullets/3.png"));
        this.typebullet = typebullet;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 5;

    }

    public void setTypebullet(float typebullet) {
        this.typebullet = typebullet;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(typebullet,SPEED);
        hitEnemy();
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
            this.isActive = false;
        }
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
