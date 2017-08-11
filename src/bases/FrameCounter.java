package bases;

/**
 * Created by huynq on 8/5/17.
 */
public class FrameCounter {
    private int count;
    private int countMax;

    public FrameCounter(int countMax) {
        this.countMax = countMax;
        this.count = 0;
    }

    public boolean run() {
        if (count >= countMax)
            return true;
        count++;
        return false;
    }

    public void reset() {
        this.count = 0;
    }

}
