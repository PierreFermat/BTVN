package touhou;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import touhou.background.Background;
import touhou.enemies.EnemySpawner;
//import touhou.explosion.Explosion;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.Shield;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static touhou.players.Shield.*;

//https://github.com/qhuydtvt/ci1-huynq

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    Player player = new Player();
    Background background = new Background();
    EnemySpawner enemySpawner = new EnemySpawner();        //TODO: Sua thanh GameObject
    InputManager inputManager = new InputManager();
    public FrameCounter powercounter;


    public GameWindow() {
        pack();
        addBackground();
        addPlayer();
        setupGameLoop();
        setupWindow();
        powercounter = new FrameCounter(50);
    }

    private void addPlayer() {
        player.setInputManager(this.inputManager);
        player.setContraints(new Constraints(getInsets().top, 768, getInsets().left, 384));
        player.getPosition().set(192,600);
        GameObject.add(player);

    }

    private void addBackground(){
        background.getPosition().set(384/2,-800);
        GameObject.add(background);
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by QHuyDTVT");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.windowGraphics = (Graphics2D) this.getGraphics();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }

    public void loop() {
        while(true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.nanoTime();
            }
            currentTime = System.nanoTime();
            if (currentTime - lastTimeUpdate > 17000000) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }

        }
    }

    private void run() {
        GameObject.runAll();
        enemySpawner.spawn();
        if(powercounter.run()) {
            player.setPower(player.getPower()+1);
            powercounter.reset();
        }

    }

    public void update (Graphics windowGraphics ) {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        player.render(backbufferGraphics);
        GameObject.renderAll(backbufferGraphics);
        Font font = new Font("Serif", Font.ITALIC, 21);
        backbufferGraphics.setFont(font);
        backbufferGraphics.setColor(Color.white);
        backbufferGraphics.drawString(Float.toString(player.getHP()), 400, 90);
//        if (player.getHP() <= 0){
//            Font nfont = new Font("Serif", Font.ITALIC, 80);
//            backbufferGraphics.setFont(nfont);
//            backbufferGraphics.setColor(Color.white);
//            backbufferGraphics.drawString("GAME OVER", 100, 200);
//            //Thread.sleep(4000);
//            //System.exit(0);
//        }
        backbufferGraphics.setFont(font);
        backbufferGraphics.setColor(Color.red);
        backbufferGraphics.drawString(Float.toString(player.getPower()), 400, 150);



        windowGraphics.drawImage(backbufferImage, 0, 0, null);

    }
    private void render(){

        repaint();
    }
}
