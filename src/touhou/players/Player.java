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
    private int exp;
    private Balls balls;
    private Shield shield;
    private FrameCounter shieldCounter;
    private Vector2D velocity;
    private PlayerAnimator animator;
    private boolean trippleSpells;
    private boolean doubleSpells;
    private int TimeSpell3;
    private int TimeSpell2;

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
        shieldCounter = new FrameCounter(50);
        velocity = new Vector2D();
        TimeSpell3 = 500;
        TimeSpell2 = 500;

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

    public void setTrippleSpells(boolean trippleSpells) {
        this.trippleSpells = trippleSpells;
    }

    public void setDoubleSpells(boolean doubleSpells) {
        this.doubleSpells = doubleSpells;
    }

    public int getTimeSpell3() {
        return TimeSpell3;
    }

    public int getTimeSpell2() {
        return TimeSpell2;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
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
        if(inputManager.xPressed){
            castSpell();
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
    private void castSpell(){
        if(trippleSpells){
            castSpell_3();
            TimeSpell3 --;
            if(TimeSpell3 <0){
                this.setTrippleSpells(false);
                TimeSpell3 = 500;
            }

        }
        else {
            if (doubleSpells) {
                castSpell_2();
                TimeSpell2 --;
                if(TimeSpell2 <0){
                    this.setDoubleSpells(false);
                    TimeSpell2 = 500;
                }
            }
            else {
                castSpell_1();
            }
        }
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
