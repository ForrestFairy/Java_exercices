import java.awt.Color;
import java.awt.Graphics;

class SnowMan {
    
    private int lower, middle, upper;
    private int xPos, yPos;
    private int sHeight;
    private int width, height;

    public SnowMan(int w, int h) {
        width = w;
        height = h;
        sHeight = h / 4 + (int) (Math.random() * h / 2);

        lower = sHeight / 2;
        middle = sHeight / 3;
        upper = sHeight / 6;

        yPos = h - (int) (Math.random() * (h - sHeight));
        xPos = lower / 2 + (int) (Math.random() * (w - lower));
    }

    public void paint(Graphics g) {
        // kule
        g.setColor(Color.white);
        int yBase = yPos - lower;
        g.fillOval(xPos - (lower / 2), yBase, lower, lower);
        yBase -= middle;
        g.fillOval(xPos - (middle / 2), yBase, middle, middle);
        yBase -= upper;
        g.fillOval(xPos - (upper / 2), yBase, upper, upper);

        // oczy
        int eye = 5;
        g.setColor(Color.cyan);
        yBase += ( (upper / 3) - eye / 2);
        g.fillOval(xPos - (upper / 6) - eye / 2, yBase, eye, eye);
        g.fillOval(xPos + (upper / 6) - eye / 2, yBase, eye, eye);

        // nos
        int nose = 8;
        g.setColor(Color.orange);
        yBase += ( (upper / 4) - nose / 2);
        g.fillOval(xPos - nose / 2, yBase, nose, nose);

        // usta
        g.setColor(Color.red);
        yBase = yPos - lower - middle - upper / 2;
        g.drawArc(xPos - (upper / 4), yBase, upper / 2, upper / 3, -20, -140);

        // miotła
        g.setColor(new Color(100, 100, 0));
        for (int i = 0; i < 5; i++) {
            g.drawLine( xPos + middle / 3, 
                        yPos - lower - middle / 2 + i, 
                        xPos + 2 * (middle / 3), 
                        yPos - lower - middle + i
                        );
        }

        // kapelusz
        g.setColor(Color.black);
        yBase = yPos - lower - middle - upper;
        g.drawLine(xPos - upper / 6, yBase, xPos + upper / 2, yBase);
        g.drawLine(xPos - upper / 6, yBase - upper / 2, upper / 3, upper / 2);

        // guziki
        int button = 6;
        g.setColor(Color.black);
        yBase = yPos - lower / 4 - button / 2;
        g.fillOval(xPos - button / 2, yBase, button, button);
        yBase -= lower / 4;
        g.fillOval(xPos - button / 2, yBase, button, button);
        yBase -= lower / 4;
        g.fillOval(xPos - button / 2, yBase, button, button);
        yBase = yPos - lower - middle / 3 - button / 2;
        g.fillOval(xPos - button / 2, yBase, button, button);
        yBase -= middle / 3;
        g.fillOval(xPos - button / 2, yBase, button, button);
    }
}
