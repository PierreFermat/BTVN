package touhou.background;

import tklibs.SpriteUtils;
import touhou.bases.FrameCounter;
import touhou.bases.Vector2D;
import touhou.bases.renderers.ImageRenderer;
import touhou.enemies.Enemy;
import touhou.inputs.InputManager;

import java.awt.*;
import java.awt.image.BufferedImage;



public class Background {
    private BufferedImage image;
    public Vector2D position;
    public static final int BackgroundSpeed = 1;


    public Background(){
        position = new Vector2D(0,-2000);
        image = SpriteUtils.loadImage("assets/images/background/0.png");

    }
    public void movebackground(){
        position.addUp(0,BackgroundSpeed);
        if (position.y > 0){
            position.y = 0;
        }
    }
    public Vector2D getPosition() {
        return position;
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image, (int)position.x, (int)position.y, null);
        movebackground();

    }


}
