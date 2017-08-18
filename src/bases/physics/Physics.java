package bases.physics;

import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.players.Player;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicalBody> bodies = new Vector<>();// Generics

    public static <T extends PhysicalBody> T collideWith(BoxCollider boxCollider, Class<T> classz){
        for(PhysicalBody body : bodies) {
            if (body.isActive()) {
                if (body.getClass().equals(classz) && body.getBoxCollider().intersects(boxCollider)) {
                    return (T) body;
                }
            }
        }

        return null;
    }

    public static void add(PhysicalBody body) {
        bodies.add(body);
    }
}

