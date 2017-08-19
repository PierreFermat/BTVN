package touhou.players;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPools;
import touhou.players.BallSpell;
import touhou.enemies.Enemy;

import java.util.Random;

public class BulletSpawners extends GameObject {
    private FrameCounter spawnCounter;

    public BulletSpawners() {
        super();
        spawnCounter = new FrameCounter(10);
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if (spawnCounter.run()){
            spawnCounter.reset();
            cast();
        }
    }

    private void cast(){
        BallSpell ballSpell = GameObjectPools.recycle(BallSpell.class);
        ballSpell.getPosition().set(this.screenPosition);
    }
}
