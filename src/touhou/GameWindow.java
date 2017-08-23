package touhou;

import bases.Constraints;
import bases.FrameCounter;
import bases.GameObject;
import tklibs.SpriteUtils;
import touhou.background.Background;
import touhou.enemies.Boss;
import touhou.enemies.EnemySpawner;
//import touhou.explosion.Explosion;
import touhou.inputs.InputManager;
import touhou.items.*;
import touhou.players.Player;
import touhou.players.Shield;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.sin;
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
    public Boss boss;
    public String playerHP ;
    public String playerPower;
    public BufferedImage hearts;
    public  BufferedImage playerImage;
    public BufferedImage playerBanner;
    public BufferedImage Item1;
    public BufferedImage Item2;
    public BufferedImage BossBanner;
    public BufferedImage enemyBanner;
    public RedItemSpawners redItems = new RedItemSpawners();
    public BlueItemSpawners blueItems = new BlueItemSpawners();

    public GameWindow() {
        pack();
        addBackground();
        addPlayer();
        setupGameLoop();
        setupWindow();
        powercounter = new FrameCounter(50);
        GameObject.add(redItems);
        GameObject.add(blueItems);
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
        playerImage = SpriteUtils.loadImage("assets/images/hud/player.png");
        hearts = SpriteUtils.loadImage("assets/images/hud/Love-Heart-20.png");
        playerBanner = SpriteUtils.loadImage("assets/images/hud/picture.png");
        Item1 = SpriteUtils.loadImage("assets/images/items/power-up-blue.png");
        Item2 = SpriteUtils.loadImage("assets/images/items/power-up-red.png");
        BossBanner = SpriteUtils.loadImage("assets/images/hud/ring.png");
        enemyBanner = SpriteUtils.loadImage("assets/images/hud/enemy-banner.png");

        this.setTitle("Touhou - Remade by ThanhCNTTBK");
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
        if(powercounter.run()) {
            player.setExp(player.getExp()+1);
            powercounter.reset();
        }
        if(background.getPosition().y == 400) {
            enemySpawner.spawnBoss();
        }
        if(background.getPosition().y < 400) {
            enemySpawner.spawn();
        }

        playerHP = Float.toString(player.getHP());


    }

    public void update (Graphics windowGraphics ) {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        player.render(backbufferGraphics);
        GameObject.renderAll(backbufferGraphics);
        Font font = new Font("Serif", Font.ITALIC, 21);
        backbufferGraphics.setFont(font);
        backbufferGraphics.setColor(Color.white);
        backbufferGraphics.drawImage(playerImage,500,90,null);
        backbufferGraphics.drawString(playerHP, 620, 105);
        backbufferGraphics.drawImage(hearts,670,90,null);
        backbufferGraphics.drawImage(playerBanner,400,50,null);
        backbufferGraphics.drawImage(Item1,500,140,null);
        backbufferGraphics.drawImage(Item2,500,190,null);
        backbufferGraphics.drawImage(BossBanner,400,400,null);
        backbufferGraphics.drawImage(enemyBanner,600,480,null);
        windowGraphics.drawImage(backbufferImage, 0, 0, null);

    }
    private void render(){

        repaint();
    }
}
