package touhou.enemies;

import tklibs.SpriteUtils;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;




public class Enemy {
    public Vector2D position;
    public Constraints constraints;
    private FrameCounter frameCounter;
    private ImageRenderer renderer;
    public ArrayList<EnemySpell> enemySpells;
    private boolean spellLock;
    String color;


    private final int SPEED = 5;


    public Enemy( String color) {
        position = new Vector2D();


        BufferedImage image;
        image = SpriteUtils.loadImage("assets/images/enemies/level0/"+ color + "/0.png");
        renderer = new ImageRenderer(image);
        frameCounter = new FrameCounter(20);
    }

    public void run(){
        if (constraints != null) {
            constraints.make(position);
        }

        position.addUp(3, SPEED);

        castSpell();
    }

    private void castSpell() {
        if (!spellLock) {
            EnemySpell newSpell = new EnemySpell();
            newSpell.position.set(this.position.add(0, +50));
            enemySpells.add(newSpell);

            spellLock = true;
            frameCounter.reset();
        }

        unlockSpell();
    }

    private void unlockSpell() {
        if (spellLock) {
            if (frameCounter.run()) {
                spellLock = false;
            }
        }
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
    public void render(Graphics2D g2d) {
        renderer.render(g2d, position);
    }
}




