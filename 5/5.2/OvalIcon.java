import java.awt.*;
import javax.swing.*;

public class OvalIcon implements Icon {
    
    private int size;
    private Color color;

    OvalIcon(Color c, int s) {
        color = c;
        size = s;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public int getIconWidth() {
        return size;
    }

    public int getIconHeight() {
        return size;
    }
}
