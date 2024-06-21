package textures;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

    public static BufferedImage[] gun;
    public static String[] weaponNames;
    
    public static void loadImages(){
        weaponNames = new String[]{"BigYellow", "Blue", "CPistol", "CWA", "Deagle1", "Flower"};
        gun = quickRead("weapons/",weaponNames);
    }


    public static BufferedImage[] quickRead(String prefix, String[] a){
        BufferedImage b[] = new BufferedImage[a.length];
        for (int i = 0; i < a.length; i++){
            try {
                System.out.println("Loading Images : " + (i + 1) + " / " + a.length);
                b[i] = ImageIO.read(Texture.class.getResourceAsStream(prefix + "" + a[i] + ".png"));
            } catch (IOException e){
                System.out.println(a[i]+" failed to load.");
            }
        }
        return b;
    }
}
