package touhou.players;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.inputs.InputManager;

public class Player extends GameObject {
    private static final int SPEED = 5;
    private InputManager inputManager;
    private Constraints constraints;

    private FrameCounter coolDownCounter;
    private boolean spellLock;

    public Player() {
        super();
        this.spellLock = false;
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/players/straight/0.png"));
        coolDownCounter = new FrameCounter(5);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void run() {
        super.run();
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

        castSpell();
    }

    private void castSpell() {
        if (inputManager.xPressed && !spellLock) {
            PlayerSpell newSpell = new PlayerSpell();
            newSpell.getPosition().set(this.position.add(0, -30));
            GameObject.add(newSpell);

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
}
