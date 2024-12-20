package util;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menus {
    
    static JFrame window;

    public static void setWindow(JFrame win) {
        window = win;
    }
    public static JPanel getMenu(String name) {
        switch(name.toLowerCase()) {
            case "splash":
                return new util.menus._0(window);
            case "loading":
                return new util.menus._1(window);
            case "ingame":
                return new util.menus._2(window);
            default:
                return null;
        }
    }
}
