package bases.animation;

import bases.FrameCounter;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
    private final int totalFrame;
    private FrameCounter frameCounter;
    private FrameCounter currentFrame;
    private boolean resetFrame ;

    private ArrayList<Frame> frames = new ArrayList<Frame>();

    public Animation(BufferedImage[] frames, int countMax) {
        this.totalFrame = frames.length;
        this.currentFrame = new FrameCounter(totalFrame);
        this.frameCounter = new FrameCounter(countMax);
        for (int i = 0; i < frames.length; i++) {
            addFrame(frames[i], countMax);
        }
        this.resetFrame = false;
    }

    public FrameCounter getCurrentFrame() {
        return currentFrame;
    }

    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public void addFrame(BufferedImage frame, int duration){
        frames.add(new Frame(frame, duration));
    }

    public void reset(){
        frameCounter.reset();
        currentFrame.reset();
    }

    public BufferedImage getSprite(){
        return frames.get((int) currentFrame.getCount()).getFrame();
    }

    public boolean isResetFrame() {
        return resetFrame;
    }

    public void update(){
        if (frameCounter.run()){
            frameCounter.reset();
            currentFrame.coolDown();
        }
        if (frameCounter.getCount() == 0 && currentFrame.getCount() == 0)
            resetFrame = true;
    }
}
