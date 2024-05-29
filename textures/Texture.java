package textures;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

    public static BufferedImage[] gun;
    public static Integer selectedWeapon = 0;
    
    public static void loadImages(){
        gun = quickRead("weapons/",new String[]{
            "BigYellow", "Blue", "CPistol", "CWA", "Deagle1", "Flamethrower", "flammenwerfer2", "Flower"
        });
    }


    public static BufferedImage[] quickRead(String prefix, String[] a){
        BufferedImage b[] = new BufferedImage[a.length];
        for (int i = 0; i < a.length; i++){
            try {
                b[i] = ImageIO.read(Texture.class.getResourceAsStream(prefix + "" + a[i] + ".png"));
            } catch (IOException e){}
        }
        return b;
    }
}
