package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Player {
    private static final int SPEED = 5;
    private Vector2D position;
    private InputManager inputManager;
    private Constraints constraints;
    public ArrayList<PlayerSpell> playerSpells;
    private ImageRenderer renderer;
    private FrameCounter coolDownCounter;
    private boolean spellLock;

    public Player() {
        this.spellLock = false;
        position = new Vector2D(384/2, 600);
        BufferedImage image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        renderer = new ImageRenderer(image);
        coolDownCounter = new FrameCounter(3);
    }

    public void setContraints(Constraints contraints) {
        this.constraints = contraints;
    }

    public void setRenderer(ImageRenderer renderer) {
        if (renderer == null)
            throw new IllegalArgumentException();
        this.renderer = renderer;
    }

    public void run() {
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
            newSpell.position.set(this.position.add(0, -30));
            playerSpells.add(newSpell);

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


    public void render(Graphics2D g2d) {
        renderer.render(g2d, position);
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }


}
