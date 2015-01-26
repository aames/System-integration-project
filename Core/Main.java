package Core;
import GUI.MainForm;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Andrew
 */
public class Main extends JFrame {
    public static void main(String[] args){
        run(new Main(), 715,585);
    }
    public Main (){
        JPanel panel = new MainForm();
        Container container = getContentPane();
        container.add(panel);
    }
     public static void run(JFrame frame, int width, int height){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("SESI Students and Modules");
    }
}