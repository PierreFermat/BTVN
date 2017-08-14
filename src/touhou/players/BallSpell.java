package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.Physics;
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


    public BallSpell(float typebullet){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/sphere-bullets/0.png"));
        this.typebullet = typebullet;
        boxCollider = new BoxCollider(20,20);
        children.add(boxCollider);
        damage = 3;

    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.addUp(typebullet,SPEED);
        hitEnemy();


    }
    private void hitEnemy() {
        Enemy enemy = Physics.collideWithEnemy(this.boxCollider);
        if(enemy != null){
            enemy.setEnemyHP(enemy.getEnemyHP() - this.damage);
            this.isActive = false;
        }
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
