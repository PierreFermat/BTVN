package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.pools.GameObjectPools;

import java.util.Random;

public class EnemySpawner extends GameObject {
    private FrameCounter spawnCounter;
    private Random random;
    public EnemySpawner(){
        spawnCounter = new FrameCounter(70);
        random = new Random();
    }

    public void  spawn(){
        Enemy enemy = new Enemy();
        if(spawnCounter.run()){
            spawnCounter.reset();
            enemy.getPosition().set(random.nextInt(384),40);
            GameObject.add(enemy);
        }
    }
}
