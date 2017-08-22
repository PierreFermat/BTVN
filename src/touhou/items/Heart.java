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

public class Heart extends GameObject implements PhysicalBody {
    private BoxCollider boxCollider;
    public final static int HEARTSPEED = 7;
    public Heart(){
        super();
        renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/items/Love-Heart-20.png"));
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
        position.addUp(0,HEARTSPEED);
    }

    private void deactiveIfNeeded() {
        if(this.screenPosition.y > 768){
            this.isActive = false;
        }
    }

    private void hitPlayer() {
        Player player = Physics.collideWith(this.boxCollider, Player.class);
        if(player != null){
            player.setHP(player.getHP() + 5 );
            this.isActive = false;
        }
    }

    @Override
    public BoxCollider getBoxCollider(){
        return this.boxCollider;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
