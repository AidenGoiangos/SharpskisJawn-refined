package com.company;

import object.Coin;

public class AssetSetter {

    public GamePanel gp;
    public AssetSetter(GamePanel gamePanel){
        this.gp = gamePanel;
    }

    public void setObjects(){
        gp.obj[0] = new Coin();
        gp.obj[0].worldX = gp.tileSize * 10;
        gp.obj[0].worldY = gp.tileSize * 10;
    }
}
