package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPools;


public class BASpawners extends GameObject{
    private  FrameCounter frameCounter;
    public BASpawners(){
        super();
        frameCounter = new FrameCounter(100);
    }
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        if(frameCounter.run()){
            frameCounter.reset();
            cast();
        }
    }

    private void cast() {
        BossAssistant bossAssistant = GameObjectPools.recycle(BossAssistant.class);
        bossAssistant.getPosition().set(this.screenPosition);
    }
}
