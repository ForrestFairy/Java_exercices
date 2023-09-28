import java.awt.*;
import javax.swing.*;

public class Okna {
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Primary window");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension sd = f.getToolkit().getScreenSize();
        System.out.println("width= " + sd.width + " height= " + sd.height);

        Dimension fd = new Dimension(400, 400);
        Point fp = new Point((sd.width-fd.width)/2, (sd.height-fd.height)/2);
        System.out.println("frameX= " + fp.x + " frameY= " + fp.y);

        f.setSize(fd);
        f.setLocation(fp);
        f.setResizable(false);
        f.show();

        JDialog dialog = new JDialog(f, "Secondary window", false);

        Dimension dd = new Dimension(200, 200);
        dialog.setSize(dd);

        fp = f.getLocation();
        fd = f.getSize();

        dialog.setLocation(
            fp.x + (fd.width - dd.width)/2,
            fp.y + (fd.height - dd.height)/2
        );
        dialog.setResizable(false);
        dialog.show();
    }
}
