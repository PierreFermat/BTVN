package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;


public class PlayerSpell extends GameObject {

    public PlayerSpell() {
        super(Vector2D, width, height);
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/player-spells/a/1.png"));

    }

    public void run() {
        position.addUp(0, -10);
    }
}
