package com.company;

import entity.Entity;

import java.sql.SQLOutput;

public class CollisionChecker {

    GamePanel gp;


    public CollisionChecker(GamePanel yes){
        this.gp = yes;
    }

    public void checkTile(Entity w){

        int closeSideWorldX = w.worldX + w.solidArea.x;
        int farSideWorldX = w.worldX + w.solidArea.x + w.solidArea.width;
        int topSideWorldY = w.worldY + w.solidArea.y;
        int bottomSideWorldY = w.worldY + w.solidArea.y + w.solidArea.height;

        int closeSideTileCol = closeSideWorldX/gp.tileSize;
        int farSideTileCol = farSideWorldX/gp.tileSize;
        int topSideTileRow = topSideWorldY/gp.tileSize;
        int bottomSideTileRow = bottomSideWorldY/ gp.tileSize;

        int tile1, tile2;
        System.out.println(w.worldX + " , " + w.worldY);
        switch(w.direction){
            case "up":

                tile1 = gp.tileManager.mapTileNum[topSideTileRow][closeSideTileCol];
                tile2 = gp.tileManager.mapTileNum[topSideTileRow][farSideTileCol];

                if(gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision){
                    w.collisionOn = true;
                }
                break;
            case "down":
                tile1 = gp.tileManager.mapTileNum[bottomSideTileRow][closeSideTileCol];
                tile2 = gp.tileManager.mapTileNum[bottomSideTileRow][farSideTileCol];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    w.collisionOn = true;

                }
                break;
            case "left":
                tile1 = gp.tileManager.mapTileNum[bottomSideTileRow][closeSideTileCol];
                tile2 = gp.tileManager.mapTileNum[topSideTileRow][closeSideTileCol];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    w.collisionOn = true;
                }
                break;
            case "right":
                tile1 = gp.tileManager.mapTileNum[bottomSideTileRow][farSideTileCol];
                tile2 = gp.tileManager.mapTileNum[topSideTileRow][farSideTileCol];
                if (gp.tileManager.tile[tile1].collision || gp.tileManager.tile[tile2].collision) {
                    w.collisionOn = true;
                }
                break;

        }
    }

    public void checkObject(Entity w, boolean player){

    }

        }


