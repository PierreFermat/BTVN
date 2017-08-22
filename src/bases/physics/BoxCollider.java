package bases.physics;

import bases.GameObject;

public class BoxCollider extends GameObject {
    protected float width;
    protected float height;

    public BoxCollider(float x, float y, float width, float height){
        super();
        this.position.set(x,y);
        this.width = width;
        this.height = height;
    }
    public BoxCollider(float width, float height){
        this(0,0,width,height);
    }
    public double left(){
        return this.screenPosition.x - this.width/2;
    }
    public double right(){ return this.screenPosition.x + this.width / 2;}
    public double bottom(){
        return this.screenPosition.y + this.height/2;
    }
    public double top(){
        return this.screenPosition.y - this.height/2;
    }


    public boolean intersects(BoxCollider other){
        if(this.bottom() >= other.top() && this.top() <= other.bottom() ){
            if (this.left() <= other.right() && this.right() >= other.left()){
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BoxCollider{" +
                "width=" + width +
                ", height=" + height +
                ", screenPosition=" + screenPosition +
                '}';
    }
}
