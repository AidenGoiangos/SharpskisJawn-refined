package tiles;

import javax.imageio.ImageIO;

import com.company.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

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
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/floorTile.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/backTile.png")));

            //grassFloor
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grassFloor.png")));

            //grassFloor2
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/grassFloorTwo.png")));

            //deepWater
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/deepWater.png")));
            tile[4].collision = true;

            //shoreWater
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("tiles/shoreWater.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

        String[] numbers;
        String line;
        InputStream is = getClass().getClassLoader().getResourceAsStream("maps/worldMap.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {


            line = br.readLine();
            numbers = line.split(" ");
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
