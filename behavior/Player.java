package behavior;



import util.Point;

public class Player extends Entity{
    public Player(){ //Assign Default Values

        mHP = cHP = 100;
        damage = 1.0; speed = 5.0;

        position = new Point(0,0);
    }

}
