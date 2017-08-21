package touhou.players;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.pools.GameObjectPools;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.enemies.Enemy;
import touhou.inputs.InputManager;

public class Player extends GameObject implements PhysicalBody {
    private static final int SPEED = 5;
    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;
    private BoxCollider boxCollider;
    private float HP;
    private int power;
    private int spellQuantity;
    private Balls balls;
    private Shield shield;
    private FrameCounter shieldCounter;
    private Vector2D velocity;
    private PlayerAnimator animator;


    public Player() {
        super();
        this.spellLock = false;
        animator = new PlayerAnimator();
        this.renderer = animator;
        coolDownCounter = new FrameCounter(6);
        boxCollider = new BoxCollider(20,20);
        creatrightball();
        creatleftball();
        children.add(boxCollider);
        HP = 500;
        spellQuantity = 1;
        shieldCounter = new FrameCounter(50);
        velocity = new Vector2D();


    }

    private void creatrightball() {
        balls = new Balls(20,0);
        balls.setReverse(true);
        children.add(balls);
    }
    private void creatleftball(){
        balls = new Balls(-20,0);
        children.add(balls);
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        velocity.set(0,0);
        if (inputManager.upPressed)
            velocity.y = -SPEED;
        if (inputManager.downPressed)
            velocity.y = SPEED;
        if (inputManager.leftPressed)
            velocity.x = -SPEED;
        if (inputManager.rightPressed)
            velocity.x = SPEED;
        position.addUp(velocity);
        animator.Update(this);

        if (constraints != null) {
            constraints.make(position);
        }
        if(inputManager.aPressed) {
            if(this.getPower() > 10*spellQuantity) {
                this.setPower(this.getPower() - 10 * spellQuantity);
                spellQuantity += 1;
                if(spellQuantity >= 4)
                    spellQuantity = 3;
                    this.setPower(this.getPower()+ 30);
            }
        }
        if(inputManager.sPressed) {
            if (this.getPower() > 10) {
                this.setPower(this.getPower() - 10);
                shield = new Shield(0,0);
                children.add(shield);
                shield.setActive(true);
            }
        }

        switch (spellQuantity) {
            case 1:
                castSpell_1();
                break;
            case 2:
                castSpell_2();
                break;
            case 3:
                castSpell_3();
                break;
        }
        if (this.getHP()<=0) {
            this.setActive(false);
        }
    }

    private void creatSpell(float x,float y,float typebullet) {
        PlayerSpell newSpell = GameObjectPools.recycle(PlayerSpell.class);
        newSpell.setTypeBullet(typebullet);
        newSpell.getPosition().set(this.position.add(x, y));
    }
    private void castSpell_1(){
        if (inputManager.xPressed && !spellLock) {
            creatSpell(0,-30,0);
            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();

    }
    private void castSpell_2(){
        if (inputManager.xPressed && !spellLock) {
            creatSpell(6,-30,0);
            creatSpell(-6,-30,0);

            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();

    }
    private void castSpell_3(){
        if (inputManager.xPressed && !spellLock) {
            creatSpell(0,-30,0);
            creatSpell(0,-30,-2);
            creatSpell(0,-30,2);
            spellLock = true;
            coolDownCounter.reset();
        }
        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock) {
            if (coolDownCounter.run()) {
                spellLock = false;
            }
        }
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    public float getHP() {
        return HP;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    @Override
    public String toString() {
        return "Player{" +
                "HP=" + HP +
                '}';
    }

    public Vector2D getVelocity() {
        return velocity;
    }
}
