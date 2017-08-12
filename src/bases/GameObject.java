package bases;

import bases.renderers.ImageRenderer;

import java.awt.*;
import java.util.Vector;
import java.awt.Rectangle;

public class GameObject {
    protected Vector2D position;
    protected int width;
    protected int height;
    protected ImageRenderer renderer;

    protected static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void runAll(){
        for(GameObject gameObject : gameObjects){
            gameObject.run();
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d){
        for(GameObject gameObject : gameObjects) {
            gameObject.render(g2d);
        }
    }

    public static void add(GameObject gameObject){
        newGameObjects.add(gameObject);
    }

    public GameObject(Vector2D,int width, int height){
        position = new Vector2D();
        this.width = renderer.image.getWidth();
        this.height = renderer.image.getHeight();



    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void run(){

    }

    public void render(Graphics2D g2d){
        if(renderer != null) {
            renderer.render(g2d, position);
        }
    }

    public Vector2D getPosition() {
        return position;
    }

    public ImageRenderer getRenderer() {
        return renderer;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setRenderer(ImageRenderer renderer) {
        if(renderer != null)
            this.renderer = renderer;
    }
    public Rectangle getBound(){
        return new Rectangle(position.x,position.y,width,height);
    }
}
