package touhou.items;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPools;
import bases.pools.GameObjectPools;

import java.util.Random;

public class HeartSpawner extends GameObject {


    private FrameCounter heartCounter;
    private Random random;

    public HeartSpawner() {
        super();
        heartCounter = new FrameCounter(200);
        random = new Random();
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        spawnheart();
    }

    public void spawnheart() {
        if(heartCounter.run()){
            heartCounter.reset();
            Heart heart = GameObjectPools.recycle(Heart.class);
            heart.getPosition().set(random.nextInt(384),40);
        }
    }
}
