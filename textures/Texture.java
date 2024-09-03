package textures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

    public static BufferedImage[] allWeapons;
    public static String[] weaponNames;
    public static BufferedImage[] allEnemies;
    public static String[] enemyNames;
    public static BufferedImage[] allMaps;
    public static String[] mapNames;
    
    public static void loadImages(){

        java.util.ArrayList<String> tweaponNames = new java.util.ArrayList<String>();

        File weaponsFolder = new File(System.getProperty("user.dir")+"\\textures\\weapons");
        for (File i : weaponsFolder.listFiles()){
            if (i.getName().split("\\.")[1].equals("png"))tweaponNames.add(i.getName());
        } weaponNames = new String[tweaponNames.size()];
        for (int i = 0; i < tweaponNames.size(); i++){
            weaponNames[i] = tweaponNames.get(i);
        }


        allWeapons = quickRead("weapons",weaponNames);

        java.util.ArrayList<String> tenemyNames = new java.util.ArrayList<String>();
        File enemiesFolder = new File(System.getProperty("user.dir")+"\\textures\\Enemies");
        for (File i : enemiesFolder.listFiles()){
            if (i.getName().split("\\.")[1].equals("png"))tenemyNames.add(i.getName());
        } enemyNames = new String[tenemyNames.size()];
        for (int i = 0; i < tenemyNames.size(); i++){
            enemyNames[i] = tenemyNames.get(i);
        }

        allEnemies = quickRead("Enemies", enemyNames);


        java.util.ArrayList<String> tmapNames = new java.util.ArrayList<String>();
        File mapFolder = new File(System.getProperty("user.dir")+"\\textures\\Map");
        for (File i : mapFolder.listFiles()){
            if (i.getName().split("\\.")[1].equals("png"))tmapNames.add(i.getName());
        } mapNames = new String[tmapNames.size()];
        for (int i = 0; i < tmapNames.size(); i++){mapNames[i] = tmapNames.get(i);}

        allMaps = quickRead("Map", mapNames);

    }


    public static BufferedImage[] quickRead(String prefix, String[] a){
        if (a == null) return new BufferedImage[0];
        if (a.length < 1) return new BufferedImage[0];
        BufferedImage b[] = new BufferedImage[a.length];
        for (int i = 0; i < a.length; i++){
            try {
                System.out.println("Textures.java - Loading " + prefix + "/" + a[i] + ", " + (i + 1) + " / " + a.length);
                b[i] = ImageIO.read(Texture.class.getResourceAsStream(prefix + "/" + a[i]));
            } catch (IOException e){
                System.out.println(a[i]+" failed to load.");
            }
        }
        return b;
    }
}
