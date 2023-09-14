import java.awt.Dimension;
import java.awt.Point;

public class Circle extends Figure {
    
    public Circle(int x, int y, int r) {
        p = new Point(x, y);
        d = new Dimension(2 * r, 2 * r);
    }

    public double pole() {
        return Math.PI * d.width * d.width / 4;
    }

    public double obwod() {
        return Math.PI * d.width;
    }

    public void show() {
        System.out.println("Circle");
    }
}
