package bases;

import bases.physics.PhysicalBody;
import bases.physics.Physics;
import bases.renderers.ImageRenderer;
import bases.renderers.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class GameObject {
    protected Vector2D position;
    protected Vector2D screenPosition;
    protected Renderer renderer;
    protected ArrayList<GameObject> children;
    protected boolean isActive;

    protected static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public static void runAll(){
        for(GameObject gameObject : gameObjects){
            if(gameObject.isActive)
                gameObject.run(new Vector2D(0,0));//TODO: optimize
        }
        for(GameObject newgameObject: newGameObjects){
            if(newgameObject instanceof PhysicalBody){
                Physics.add((PhysicalBody)newgameObject);
            }
        }
        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();
    }

    public static void renderAll(Graphics2D g2d){
        for(GameObject gameObject : gameObjects) {
            if (gameObject.isActive)
                gameObject.render(g2d);
        }
    }
    public boolean IsActive(){
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getActive(){
        return isActive;
    }


    public static void add(GameObject gameObject){
        newGameObjects.add(gameObject);
    }

    public GameObject(){
        position = new Vector2D();
        screenPosition = new Vector2D();
        children = new ArrayList<>();
        isActive = true;
    }

    public void run(Vector2D parentPosition){
        screenPosition = parentPosition.add(position);
        for ( GameObject child : children){
            if(child.isActive)
                child.run(screenPosition);
        }

    }

    public void render(Graphics2D g2d){
        if(renderer != null) {
            renderer.render(g2d, screenPosition);
        }
        for(GameObject child : children){
            if(child.isActive){
                child.render(g2d);
            }
        }
    }

    public Vector2D getPosition() {
        return position;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }


}
