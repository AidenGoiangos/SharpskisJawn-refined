package tiles;

import javax.imageio.ImageIO;

import com.company.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.File;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;


    public TileManager(GamePanel GP) {
        this.gp = GP;
        tile = new Tile[10];

        //init with height and width of map (could be replaced gp.maxWorldCol) etc
        mapTileNum = new int[50][50];
        getTileImage();
        try{
            loadMap();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/basicGrass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/basicGrass.png")));

            //grassFloor
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/basicGrass.png")));

            //grassFloor2
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/colorfulFlower.png")));

            //deepWater
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/deepWater.png")));
            tile[4].collision = true;

            //middle deep water
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/middleDeep.png")));

            //shallow
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/shallowWater.png")));

            //beach sand
            //shallow
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/beachSand.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(){
        String[] numbers;
        String line;
        int counter;
        InputStream is = getClass().getClassLoader().getResourceAsStream("maps/worldMap.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {


            line = br.readLine();
            System.out.println(line);
            numbers = line.split(" ");

            //copy array using this to avoid issue
            for(String k : line.split(" ")){

            }
            System.out.println(numbers.length);
            for (int j = 0; j < gp.maxWorldRow; j++ ) {
                for(int k = 0; k < gp.maxWorldCol; k++){
                    int num = Integer.parseInt(numbers[(j* gp.maxWorldRow) + k]);
                    mapTileNum[j][k] = num;

                }

            }
        } catch (Exception e) {
            e.getStackTrace();

        }
    }
    public void draw(Graphics2D g2) {



        for(int i = 0; i < gp.maxWorldRow; i++){
            for(int k = 0; k < gp.maxWorldCol; k++){
                int tileNum = mapTileNum[i][k];
                int worldX = k * gp.tileSize;
                int worldY = i * gp.tileSize;
                int screenX = worldX - gp.player.worldX + (gp.player.screenX);
                int screenY = worldY - gp.player.worldY + (gp.player.screenY);
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
            }

        }
