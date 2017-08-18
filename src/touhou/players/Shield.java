package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.BallSpell;
import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.inputs.InputManager;

public class Shield extends GameObject implements PhysicalBody {
    private BoxCollider boxCollider;
    private static float HP = 300;
    private final static float  damage = 50;
    private InputManager inputManager;

    public Shield(float x,float y){
        super();
        this.position.set(x,y);
        boxCollider = new BoxCollider(100,100);
        children.add(boxCollider);
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/Shield/rasengan.png")) ;
        HP = 200;

    }


    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        hitEnemy();
        hitBullet();
        if(this.getHP()<=0){
            this.isActive = false;
        }


    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    private void hitEnemy() {
        Enemy enemy = Physics.collideWith(this.boxCollider,Enemy.class);
        if(enemy != null){
            enemy.setActive(false);
            this.setHP(this.getHP()-200);

        }
    }
    private void hitBullet(){
        EnemyBullet enemyBullet = Physics.collideWith(this.boxCollider, EnemyBullet.class);
        if(enemyBullet != null){
            enemyBullet.setActive(false);
            this.setHP(this.getHP() - 50);
        }
    }


}
