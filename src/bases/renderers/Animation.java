package bases.renderers;

import bases.FrameCounter;
import bases.Vector2D;

import java.awt.Graphics2D;
import java.util.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation implements Renderer {

    private List<BufferedImage> images;
    private FrameCounter frameCounter;
    private int currentImageIndex;
    private boolean reverse;

    public  Animation(int frameDelay,boolean reverse, BufferedImage... images){
        this.images = Arrays.asList(images);
        this.frameCounter = new FrameCounter(frameDelay);
        this.currentImageIndex = 0;
        this.reverse = reverse;
    }
    public Animation(BufferedImage... images){
        this(12,false,images);
    }


    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        BufferedImage image = images.get(currentImageIndex);
        Vector2D renderPosition = position.subtract(image.getWidth()/2, image.getHeight()/2);
        g2d.drawImage(image,(int)renderPosition.x,(int)renderPosition.y,null );
        updateCurrentImage();
    }

    public void updateCurrentImage(){
        if(frameCounter.run()) {
            frameCounter.reset();
            if (!reverse) {
                currentImageIndex++;
                if (currentImageIndex >= images.size()) {
                    currentImageIndex = 0;
                }
            }
            else {
                currentImageIndex --;
                if(currentImageIndex < 0){
                    currentImageIndex = images.size();
                }

            }

        }
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}

