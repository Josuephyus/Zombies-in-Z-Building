package behavior;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Weapons {

    public static ArrayList<String> names;
    public static ArrayList<Weapons> weapons;
    public createProjectile[] projectiles;
    public createLaser[] lasers;
    public createArea[] areas;

    public String type, image;
    public Integer width, height;


    public static void start(){

        names = new ArrayList<String>();
        weapons = new ArrayList<Weapons>();

        File folder = new File(System.getProperty("user.dir")+"\\data\\weapons");
        for (File i : folder.listFiles()){
            if (i.getName().split("\\.")[1].equals("weapon"))names.add(i.getName());
        }

        
        for (int i = 0; i < names.size(); i++){
            System.out.println("Weapons.java - Loading "+ names.get(i));
            Weapons thisOne = new Weapons();
            try {

                File wF = new File(System.getProperty("user.dir")+"\\data\\weapons\\" + names.get(i));
                FileReader wR = new FileReader(wF);


                int value = wR.read();
                ArrayList<Integer> listOfValues = new ArrayList<Integer>();
                while (value != -1){
                    listOfValues.add(value);
                    value = wR.read();
                } wR.close();


                ArrayList<String> values = new ArrayList<String>(); values.add("");
                for (int o = 0; o < listOfValues.size(); o++){
                    if (listOfValues.get(o) == 13 && listOfValues.get(o+1) == 10){
                        values.add(""); o += 2;
                    }
                    String setTo = values.removeLast() + (char)((int)listOfValues.get(o));
                    values.add(setTo);
                }


                //Check Type
                String a = values.get(0).split(":")[1];
                String b = a.charAt(0) + "";
                String[] c = a.split(b);
                thisOne.type = b.toUpperCase() + c[1];

                //Check Image
                thisOne.image = values.get(1).split(":")[1];

                //Check Width / Height
                thisOne.width = Integer.parseInt(values.get(2).split(":")[1]);
                thisOne.height = Integer.parseInt(values.get(3).split(":")[1]);

                //How many shots and their values;
                thisOne.projectiles = new createProjectile[Integer.parseInt(values.get(4).split(":")[1])];


                if (thisOne.type.equals("Projectile")){
                    for (int o = 0; o < thisOne.projectiles.length; o++){
                        Integer p = (o * 8) + 4;
                        thisOne.projectiles[o] = new createProjectile(
                            values.get(p + 1).split(":")[1],
                            values.get(p + 2).split(":")[1],
                            values.get(p + 3).split(":")[1],
                            values.get(p + 4).split(":")[1],
                            values.get(p + 5).split(":")[1],
                            values.get(p + 6).split(":")[1],
                            values.get(p + 7).split(":")[1],
                            values.get(p + 8).split(":")[1],
                            values.get(p + 9).split(":")[1]
                        );
                    }
                } else if (thisOne.type.equals("Laser")){

                } else { //Assume Area

                }

            } catch (java.io.IOException e){System.out.println(i +" file was invalid.");}

            weapons.add(thisOne);
        }
    }

    public Weapons(){}
    public static Integer parse(String a){return Integer.parseInt(a);}
    public static class createProjectile{
        public String image;
        public Integer speed, range, direction, damage;
        public Integer width, height, radius, pierce;
        public createProjectile(String img, String s, String ran, String dir, String d, String w, String h, String rad, String p){
            this.image = img;
            this.speed = parse(s); this.range = parse(ran);
            this.direction = parse(dir); this.damage = parse(d);
            this.width = parse(w); this.height = parse(h);
            this.radius = parse(rad); this.pierce = parse(p);
        }

        public String toString(){
            return
            ("Image: "+image) +
            (", Speed: "+speed) +
            (", Range: "+range) +
            (", Direction: "+direction) +
            (", Damage: "+damage) +
            (", Width: "+width) +
            (", Height: "+height) +
            (", Radius: "+radius) +
            (", Pierce: "+pierce);
        }
    }
    public static class createLaser{

    }
    public static class createArea{
        
    }
}
