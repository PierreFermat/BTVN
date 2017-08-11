package touhou.background;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class Background extends GameObject {
    public Background(){
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"));
    }

    public void run() {
        if(position.y < 3109/2) {
            position.addUp(0, 1);
        }
    }
}
