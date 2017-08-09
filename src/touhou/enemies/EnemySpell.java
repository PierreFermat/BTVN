package touhou.enemies;
import tklibs.SpriteUtils;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemySpell {
    public Vector2D position;
    private ImageRenderer renderer;
    private final int SPEED = 4;

    public EnemySpell() {
        position = new Vector2D();
        BufferedImage image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        renderer = new ImageRenderer(image);
    }

    public void run(){
        position.addUp(0, SPEED);
    }

    public void render(Graphics2D g2d){
        renderer.render(g2d, position);
    }
}
