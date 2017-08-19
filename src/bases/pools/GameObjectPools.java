package bases.pools;

import bases.GameObject;
import bases.Vector2D;
import touhou.players.PlayerSpell;

import java.util.Vector;

public class GameObjectPools {
    private static Vector<GameObject> pool = new Vector<>();
    public static <T extends GameObject> T recycle(Class<T> classz){
        for(GameObject gameObject : pool){
            if(gameObject.getClass().equals(classz)){
                if(!gameObject.getActive()){
                    gameObject.setActive(true);
                    return (T) gameObject;
                }
            }
        }

        try {
            T newGameObject = classz.newInstance();
            GameObject.add(newGameObject);
            pool.add(newGameObject);
            return newGameObject;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static class T {
    }
}
