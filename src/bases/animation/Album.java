package bases.animation;

import tklibs.SpriteUtils;

import java.awt.image.BufferedImage;

public class Album {
    private static BufferedImage spriteSheet;
    private static final int SIZE = 32;

    public static BufferedImage loadAlbum(String file){
        BufferedImage album = SpriteUtils.loadImage(file);
        //System.out.println(file);
        return album;
    }

    public static BufferedImage getAlbum(int x, int y) {
        return spriteSheet.getSubimage(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    // Get sprite from a folder and number sprite
    public static BufferedImage[] getAlbums(String file, int number){
        BufferedImage[] Albums = new BufferedImage[number];
        for (int i = 0; i < number ; i++) {
            Albums[i] = loadAlbum(file + Integer.toString(i) + ".png");
        }
        return Albums;
    }

}
