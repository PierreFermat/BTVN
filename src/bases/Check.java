package bases;

import touhou.enemies.Enemy;
import touhou.players.PlayerSpell;

public class Check {
    Enemy enemy = new Enemy();
    PlayerSpell playerSpell = new PlayerSpell();
    public boolean Check(){
        Rectangle x1 = new Rectangle(enemy.position , enemy.position.add(enemy.renderer.image.getWidth() , enemy.renderer.image.getHeight()) );
        Rectangle x2 = new Rectangle(playerSpell.position , playerSpell.position.add(playerSpell.renderer.image.getWidth() , playerSpell.renderer.image.getHeight()));
        if (x1.topLeft.x > x2.bottomRight.x || x1.bottomRight.x < x2.topLeft.x || x1.topLeft.y < x2.bottomRight.y || x1.bottomRight.y > x2.topLeft.y)
            return false;
        return true;
    }
}
