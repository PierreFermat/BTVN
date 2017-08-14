package bases.physics;

import touhou.enemies.Enemy;
import touhou.enemies.EnemyBullet;
import touhou.players.Player;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicalBody> bodies = new Vector<>();

    public static Enemy collideWithEnemy(BoxCollider boxCollider) {
        for(PhysicalBody body : bodies){
            if(body.isActive()){   if(body instanceof Enemy && body.getBoxCollider().intersects(boxCollider)) {
                return (Enemy) body;
                }
            }
        }
        return null;
    }

    public static void add(PhysicalBody body) {
        bodies.add(body);
    }

    public static Player collideWithPlayer(BoxCollider boxCollider) {
        for(PhysicalBody body : bodies){
            if(body.isActive()) {
                if (body instanceof Player && body.getBoxCollider().intersects(boxCollider)) {
                    return (Player) body;
                }
            }
        }
        return null;
    }

    public static EnemyBullet collideWithEnemyBullet(BoxCollider boxCollider) {
        for(PhysicalBody body : bodies){
            if(body.isActive()) {
                if (body instanceof EnemyBullet && body.getBoxCollider().intersects(boxCollider)) {
                    return (EnemyBullet) body;
                }
            }
        }
        return null;
    }
}