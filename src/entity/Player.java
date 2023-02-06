package entity;

import com.company.GamePanel;
import com.company.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler handler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler ){
        this.gp = gamePanel;
        this.handler = keyHandler;

        screenX = gp.screenWidth/2 - (gp.tileSize);
        screenY = gp.screenHeight/2 - (gp.tileSize);

        solidArea = new Rectangle();
        solidArea.x =  8;
        solidArea.y =   16;
        solidArea.width = 32;
        solidArea.height = 32;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
      this.worldX = gp.tileSize * 10;
      this.worldY = gp.tileSize * 10;
      this.speed = 8;
      this.direction = "up";
    }

    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/upOne.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/upTwo.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/backOne.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/backTwo.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/leftOne.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/leftTwo.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/rightOne.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/rightTwo.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update() {

        if (handler.upPressed || handler.rightPressed || handler.leftPressed || handler.downPressed) {
            if (handler.upPressed) {
                direction = "up";
            } else if (handler.downPressed) {
                direction = "down";
            } else if (handler.leftPressed) {
                direction = "left";
            } else if (handler.rightPressed) {
                direction = "right";
            }


            // check tile collision
            collisionOn = false;
            gp.checker.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;

                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }
    public void draw(Graphics2D g2){
        Image image = null;
        switch(direction){
            case "up" :
                if(spriteNum == 1)image = up1;
                if(spriteNum==2)image = up2;
                break;

            case "down" :
                if(spriteNum == 1)image = down1;
                if(spriteNum==2)image = down2;
                break;
            case "left" :
                if(spriteNum == 1)image = left1;
                if(spriteNum==2)image = left2;
                break;
            case "right" :
                if(spriteNum == 1)image = right1;
                if(spriteNum==2)image = right2;
                break;
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.drawRect(solidArea.x +screenX, solidArea.y+screenY, solidArea.width, solidArea.height);

    }
}
