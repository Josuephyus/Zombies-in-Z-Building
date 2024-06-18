package behavior;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.Projectile;
import textures.Texture;

public class Weapons {

    public static ArrayList<String> names;
    public static ArrayList<Weapons> weapons;

    public String type, image;
    public Integer width, height;
    public createProjectile[] projectiles;

    public static class createProjectile{
        public String image;
        public Integer speed, range, direction, width, height, radius, pierce;
    }

    public static void start(){
        names = new ArrayList<String>();
        File folder = new File(System.getProperty("user.dir")+"\\data\\weapons");
        for (File i : folder.listFiles()){
            if (i.getName().split("\\.")[1].equals("weapon")){
                names.add(i.getName());
            }
        }
        weapons = new ArrayList<Weapons>();
        for (int i = 0; i < names.size(); i++){
            Weapons thisOne = new Weapons();
            try{

                File weaponFile = new File(System.getProperty("user.dir")+"\\data\\weapons\\" + names.get(i));
                FileReader weaponReader = new FileReader(weaponFile);


                int value = weaponReader.read();
                ArrayList<Integer> listOfValues = new ArrayList<Integer>();
                while (value != -1){
                    listOfValues.add(value);
                    value = weaponReader.read();
                }

                ArrayList<String> values = new ArrayList<String>();
                for (int o = 0; o < listOfValues.size(); o++){
                    if (values.size() == 0){
                        char a = (char)((int)listOfValues.get(o));
                        values.add(""+a);
                    } else if (listOfValues.get(o) == 13 && listOfValues.get(o+1) == 10){
                        values.add("");o++;
                    } else {
                        String setTo = values.removeLast() + (char)((int)listOfValues.get(o));
                        values.add(setTo);
                    }
                }


                //Check Type
                if (values.get(0).split(":")[1].equals("projectile")){
                    thisOne.type = "Projectile";
                } else if (values.get(0).split(":")[1].equals("laser")){
                    thisOne.type = "Laser";
                } else if (values.get(0).split(":")[1].equals("area")){
                    thisOne.type = "Area";
                }

                //Check Image
                thisOne.image = values.get(1).split(":")[1];

                //Check Width / Height
                thisOne.width = Integer.parseInt(values.get(2).split(":")[1]);
                thisOne.height = Integer.parseInt(values.get(3).split(":")[1]);

                //How many shots
                Integer howManyValues = Integer.parseInt(values.get(4).split(":")[1]);
                thisOne.projectiles = new createProjectile[howManyValues];

                if (thisOne.type.equals("Projectile")){
                    for (int o = 0; o < howManyValues; o++){
                        Integer p = (o * 8) + 5;
                        System.out.println(values.get(p));
                        thisOne.projectiles[o].speed = Integer.parseInt(values.get(p + 1).split(":")[1]);
                        thisOne.projectiles[o].range = Integer.parseInt(values.get(p + 2).split(":")[1]);
                        thisOne.projectiles[o].direction = Integer.parseInt(values.get(p + 3).split(":")[1]);
                        thisOne.projectiles[o].width = Integer.parseInt(values.get(p + 4).split(":")[1]);
                        thisOne.projectiles[o].height = Integer.parseInt(values.get(p + 5).split(":")[1]);
                        thisOne.projectiles[o].radius = Integer.parseInt(values.get(p + 6).split(":")[1]);
                        thisOne.projectiles[o].pierce = Integer.parseInt(values.get(p + 7).split(":")[1]);
                    }
                }
                
                weaponReader.close();
            } catch (IOException e){System.out.println(i +" file was invalid.");}

            weapons.add(thisOne);
        }
    }

    public Weapons(){
    }
}
