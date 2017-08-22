package touhou.items;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPools;
import bases.pools.GameObjectPools;

import java.util.Random;

public class RedItemSpawners extends GameObject {

    private FrameCounter tripperspellsCounter;
    private Random random;
    public RedItemSpawners() {


        super();
        tripperspellsCounter = new FrameCounter(360);
        random = new Random();
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        spawnRed();
    }

    private void spawnRed() {
        if(tripperspellsCounter.run()){
            tripperspellsCounter.reset();
            RedItems redItems = GameObjectPools.recycle(RedItems.class);
            redItems.getPosition().set(random.nextInt(384),40);
        }
    }
}
