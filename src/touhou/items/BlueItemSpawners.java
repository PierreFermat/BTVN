package touhou.items;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPools;
import bases.pools.GameObjectPools;

import java.util.Random;

public class BlueItemSpawners extends GameObject
{


    private FrameCounter doublespellsCounter;
    private Random random;

    public BlueItemSpawners(){
        super();
        doublespellsCounter = new FrameCounter(270);
        random = new Random();
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        spawn2spells();
    }

    public void spawn2spells() {
        if(doublespellsCounter.run()){
            doublespellsCounter.reset();
            BlueItems blueItems = GameObjectPools.recycle(BlueItems.class);
            blueItems.getPosition().set(random.nextInt(384),40);
        }
    }


}
