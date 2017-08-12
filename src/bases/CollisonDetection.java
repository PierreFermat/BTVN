package bases;

import java.awt.*;

public class CollisonDetection {
    private final GameObject gameObject_1;
    private final GameObject gameObject_2;

    public CollisonDetection( GameObject gameObject_1,GameObject gameObject_2){
        this.gameObject_1 = gameObject_1;
        this.gameObject_2 = gameObject_2;
        checkCollison();

    }

    private boolean checkCollison() {
        Rectangle Bound_1 = gameObject_1.getBound();
        Rectangle Bound_2 = gameObject_2.getBound();
        if(Bound_1.intersects(Bound_2)) {
            return true;
        }
        return false;
    }
}
