package com.company;

import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //initial pixels
    final int originalTileSize = 16; // 16x16 pixel tile
    final int scaler = 3;

    //to simulate 8bit
    public final int tileSize = originalTileSize * scaler;

    //screen settings
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 60;

    //objects
    TileManager tileManager = new TileManager(this);
    KeyHandler handler = new KeyHandler();
    Thread gameThread;

    public CollisionChecker checker = new CollisionChecker(this);
    public Player player = new Player(this, handler);

    //world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(handler);
        this.setFocusable(true);
        }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        while (gameThread != null){
            double drawInterval = 1000000000/FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;


            //while running
            update();
            repaint();
            //System.out.println("anger, rage evn");



            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }


            }
        }



    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponents(g);

        Graphics2D g2 = (Graphics2D)g;

        tileManager.draw(g2);
        player.draw(g2);

        g2.dispose();

    }
}
