package touhou.players;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
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

    public Player() {
        super();
        this.spellLock = false;
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        coolDownCounter = new FrameCounter(5);
        boxCollider = new BoxCollider(20,20);
        creatball(-20,0);
        creatball(20,0);
        children.add(boxCollider);
        HP = 500;
        spellQuantity = 1;
    }

    private void creatball(float x, float y) {
        balls = new Balls(x,y);
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
        if (inputManager.upPressed)
            position.addUp(0, -SPEED);
        if (inputManager.downPressed)
            position.addUp(0, SPEED);
        if (inputManager.leftPressed)
            position.addUp(-SPEED, 0);
        if (inputManager.rightPressed)
            position.addUp(SPEED, 0);

        if (constraints != null) {
            constraints.make(position);
        }
        if(inputManager.aPressed) {
            if(this.getPower() > 10*spellQuantity) {
                this.setPower(this.getPower() - 10 * spellQuantity);
                spellQuantity += 1;
                if(spellQuantity >= 4)
                    spellQuantity = 3;
            }
        }

        switch (spellQuantity) {
            case 1:
                castSpell_1();
                break;
            case 2:
                castSpell_2();
            case 3:
                castSpell_3();
        }


        if (this.getHP()<=0) {
            this.setActive(false);
        }
    }

    private void creatSpell(float x,float y,float typebullet) {
        PlayerSpell newSpell = new PlayerSpell(typebullet);
        newSpell.getPosition().set(this.position.add(x, y));
        GameObject.add(newSpell);
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
}
