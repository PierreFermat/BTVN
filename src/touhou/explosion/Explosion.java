//package touhou.explosion;
//
//import bases.GameObject;
//import bases.Vector2D;
//import bases.animation.Album;
//import bases.animation.Animation;
//import bases.renderers.ImageRenderer;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//public class Explosion extends GameObject {
//    private BufferedImage[] images;
//    private Animation animation;
//
//    public Explosion() {
//        images = Album.getAlbums("assets/images/enemies/explosion/",8);
//        animation = new Animation(images, 3);
//        renderer = new ImageRenderer(animation.getSprite());
//    }
//    public void run(){
//        renderer.setImage(animation.getSprite());
//        animation.update();
//    }
//
//    public void setPosition(Vector2D position) {
//        this.position = position;
//    }
//
//    public Animation getAnimation() {
//        return animation;
//    }
//    public void render(Graphics2D g2d){
//        if (!animation.isResetFrame())
//            renderer.render(g2d, position);
//    }
//}
