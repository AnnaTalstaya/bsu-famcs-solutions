import javax.swing.*;
import java.awt.*;

public class Fish {
    protected int speed;
    protected ImageIcon imageIcon;
    protected ImageIcon imageIconLeft;
    protected ImageIcon imageIconRight;
    protected int width, height;
    protected int x, y;

    protected int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    protected int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public Fish(int speed, ImageIcon imageIconLeft, ImageIcon imageIconRight, int width, int height, int x, int y) {
        this.speed = speed;
        this.imageIconLeft = imageIconLeft;
        this.imageIconRight = imageIconRight;
        this.imageIcon = imageIconRight;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Rectangle getRect(){
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.drawImage(imageIcon.getImage(), x, y, width, height, null);
    }

}
