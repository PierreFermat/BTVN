package touhou.players;

import tklibs.SpriteUtils;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;

import java.awt.*;


public class PlayerSpell {
    public Vector2D position;
    public ImageRenderer renderer;

    public PlayerSpell() {
        this.renderer = new ImageRenderer(SpriteUtils.loadImage(
                "assets/images/player-spells/a/1.png"
        ));
        position = new Vector2D();
    }

    public void render(Graphics2D g2d) {
        renderer.render(g2d, position);
    }

    public void run() {
        position.addUp(0, -10);
    }
}
