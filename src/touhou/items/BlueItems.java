package touhou.items;

import bases.GameObject;
import bases.Vector2D;
import bases.physics.BoxCollider;
import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.physics.PhysicalBody;
import bases.renderers.ImageRenderer;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.players.Player;
import touhou.players.Player;

public class BlueItems extends GameObject implements PhysicalBody {
    private BoxCollider boxCollider;
    public final static int BISPEED = 10;

    public BlueItems() {
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/power-up-blue.png"));
        boxCollider = new BoxCollider(20,20);
        this.children.add(boxCollider);
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        move();
        hitPlayer();
        deactiveIfNeeded();
    }

    private void move() {
        position.addUp(0,BISPEED);
    }
    private void deactiveIfNeeded() {
        if(this.screenPosition.y > 768){
            this.isActive = false;
        }
    }

    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setDoubleSpells(true);
            this.isActive = false;
        }
    }
    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
