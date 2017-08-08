package touhou;

import tklibs.SpriteUtils;
import touhou.background.Background;
import touhou.bases.Constraints;
import touhou.bases.FrameCounter;
import touhou.enemies.Enemy;
import touhou.enemies.EnemySpell;
import touhou.inputs.InputManager;
import touhou.players.Player;
import touhou.players.PlayerSpell;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.awt.event.KeyEvent.*;


public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;

    private BufferedImage backbufferImage;
    private Graphics2D backbufferGraphics;

    Background background = new Background();
    Player player = new Player();

    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    //ArrayList<EnemySpell> enemySpells = new ArrayList<>();


    InputManager inputManager = new InputManager();
    private FrameCounter TimeAttack;



    public GameWindow() {
        pack();
        player.setInputManager(this.inputManager);
        player.setContraints(new Constraints(getInsets().top, 768, getInsets().left, 384));
        player.playerSpells = this.playerSpells;
        for (Enemy enemy:enemies){
            enemy.setConstraints(new Constraints(0,768,0,384));
        }
        //for(Enemy enemy: enemies){
            //enemy.enemySpells = this.enemySpells;
        //}


        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);

        this.setTitle("Touhou - Remade by CThanh-cntt-bkkstn");
        this.setVisible(true);

        this.backbufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backbufferGraphics = (Graphics2D) this.backbufferImage.getGraphics();

        this.windowGraphics = (Graphics2D) this.getGraphics();
        TimeAttack = new FrameCounter(30);

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
        player.run();

        for (PlayerSpell playerSpell : playerSpells) {
            playerSpell.run();
        }
        enemiesAttack();
        for (Enemy enemy : enemies){
            enemy.run();
            //for (EnemySpell enemySpell: enemySpells){
            //    enemySpell.run();
            //}
        }






    }

    public void update (Graphics windowGraphics ) {
        backbufferGraphics.setColor(Color.black);
        backbufferGraphics.fillRect(0, 0, 1024, 768);
        background.render(backbufferGraphics);
        player.render(backbufferGraphics);

        for (PlayerSpell playerSpell: playerSpells) {
            playerSpell.render(backbufferGraphics);
        }
        for (Enemy enemy :enemies){
            enemy.render(backbufferGraphics);
            //for (EnemySpell enemySpell : enemySpells){
            //    enemySpell.render(backbufferGraphics);
            //}
        }



        windowGraphics.drawImage(backbufferImage, 0, 0, null);
    }



    private void render(){
        repaint();
    }

    private void enemiesAttack() {

        if (TimeAttack.run()) {
            Enemy newenemies = new Enemy();
            enemies.add(newenemies);
            newenemies.position.set(background.getPosition().multiply(0,-1/5));
            TimeAttack.reset();
        }

    }

}
