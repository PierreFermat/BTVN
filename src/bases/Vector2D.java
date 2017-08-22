package bases;

import static java.lang.Math.sqrt;

/**
 * Created by huynq on 8/2/17.
 */
public class Vector2D {
    public double x;
    public double y;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void addUp(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D other) {
        set(other.x, other.y);
    }

//    public Vector2D clone() {
//
//    }

    public void addUp(Vector2D other) {
        addUp(other.x, other.y);
    }

    public void subtractBy(double dx, double dy) {
        this.x -= dx;
        this.y -= dy;
    }

    public void subtractBy(Vector2D other) {
        subtractBy(other.x, other.y);
    }

    public Vector2D add(double dx, double dy) {
//        Vector2D newVector = new Vector2D();
//        newVector.x = this.x + dx;
//        newVector.y = this.y + dy;
//        return newVector;
        return new Vector2D(this.x + dx, this.y + dy);
    }

    public Vector2D add(Vector2D other) {
        return add(other.x, other.y);
    }

    public Vector2D multiply(float f) {
        return new Vector2D(this.x * f, this.y * f);
    }

    public void multiplyBy(double x, double y){
        this.x *= x;
        this.y *= y;
    }

    public double magnitude() {
        return sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D normalize() {
        double mag = magnitude();
        return new Vector2D(this.x / mag, this.y / mag);
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Vector2D subtract(double dx, double dy) {
        return new Vector2D(x-dx,y-dy);

    }
    public Vector2D subtract(Vector2D vector2D){
        return new Vector2D(x- vector2D.x,y - vector2D.y);
    }
    public double distance(Vector2D vector2D){
        return (this.subtract(vector2D).magnitude());
    }
}
