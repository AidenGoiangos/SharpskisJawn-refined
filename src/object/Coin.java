package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coin extends SuperObject{
    public Coin(){
        name = "coin";

        try{
            this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/coin.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
