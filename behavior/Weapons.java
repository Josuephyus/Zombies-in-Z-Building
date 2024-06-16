package behavior;

import java.io.File;
import 

import textures.Texture;

public class Weapons {

    public static String[] names;
    public static Weapons[] weapons;

    public static void start(){
        names = new String[0];
        File folder = new File(System.getProperty("user.dir")+"\\data\\weapons");
        for (File i : folder.listFiles()){
            if (i.getName().split("\\.")[1].equals("json")){
                String[] tempNames = names.clone();
                names = new String[names.length + 1];
                for (int o = 0; o < tempNames.length; o++){
                    names[o] = tempNames[o];
                }
                names[tempNames.length] = i.getName().split("\\.")[0];
            }
        }
        for (String i : names){
            Weapons[] tempWeapons = weapons.clone();
            weapons = new Weapons[tempWeapons.length + 1];
            Weapons thisOne = new Weapons();
        }
    }

    public Weapons(){

    }
}
