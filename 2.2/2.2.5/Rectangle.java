import java.awt.Dimension;
import java.awt.Point;

public class Rectangle extends Figure {
    
    Rectangle(int x, int y, int w, int h) {
        p = new Point(x, y);
        d = new Dimension(w, h);
    }

    public double pole() {
        return (d.width * d.height);
    }

    public double obwod() {
        return 2 * (d.width + d.height);
    }

    public void show() {
        System.out.println("Rectangle");
    }
}
