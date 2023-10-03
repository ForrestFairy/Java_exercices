import java.awt.Color;
import java.awt.Graphics;

class SnowFlake {
    
    private int xPos, yPos;
    private int size;
    private int height;

    public SnowFlake(int h, int w) {
        height = h;
        size = 5 + (int) (10 * Math.random());
        yPos = (int) (Math.random() * height);
        xPos = (int) (Math.random() * w);
    }

    public void paint(Graphics g) {
        int s = size / 2;

        g.setColor(Color.white);
        g.drawLine(xPos, yPos - s, xPos, yPos + s);
        g.drawLine(xPos - s, yPos, xPos + s, yPos);
        g.drawLine(xPos - s, yPos - s, xPos + s, yPos + s);
        g.drawLine(xPos + s, yPos - s, xPos - s, yPos + s);
        int t = size / 6;
        g.fillOval(xPos - t, yPos - t, 2 * t, 2 * t);
    }

    public void fall() {
        yPos = (yPos + 1) % height;
        int move = (int) (3 * Math.random());
        if (move == 0) {
            xPos++;
        }
        if (move == 1) {
            xPos--;
        }
    }
}
