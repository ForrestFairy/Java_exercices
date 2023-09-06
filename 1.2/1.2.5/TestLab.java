import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TestLab extends JFrame {
    public static void main(String[] args) {
        String txt = "<html><B><CENTER>Witaj<BR>w swiecie<BR>" +
            "<font color=red> Javy</font></B></CENTER><html>";

        try {
            URL link = new URL("https://w7.pngwing.com/pngs/811/186/png-transparent-java-programming-programming-language-computer-programming-others-miscellaneous-text-logo-thumbnail.png");
            Icon icon = new ImageIcon(link, "");

            JLabel lab = new JLabel(txt, icon, JLabel.CENTER);

            lab.setFont(new Font("Dialog", Font.BOLD, 18));

            JFrame f = new JFrame("Przywitanie");

            Container cp = f.getContentPane();
            cp.add(lab);

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            f.pack();

            f.setVisible(true);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        

        
    }
}
