import javax.swing.*;
import java.awt.*;

public class Player extends Fish {

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    private int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private int score;
    private int level = 1;
    private int lives;

    private ImageIcon img1L, img1R, img2L, img2R, img3L, img3R, img0L, img0R;
    public int grow = 0;

    public Player(int speed, ImageIcon imageIconLeft, ImageIcon imageIconRight, int width, int height, int x, int y, int lives,
                  ImageIcon img1L, ImageIcon img1R,
                  ImageIcon img2L, ImageIcon img2R,
                  ImageIcon img3L, ImageIcon img3R) {
        super(speed, imageIconLeft, imageIconRight, width, height, x, y);

        this.lives = lives;
        this.img0L = imageIconLeft;
        this.img0R = imageIconRight;
        this.img1L = img1L;
        this.img1R = img1R;
        this.img2L = img2L;
        this.img2R = img2R;
        this.img3L = img3L;
        this.img3R = img3R;

        up = false;
        down = false;
        left = false;
        right = false;

    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void fishIsEaten() {
        score += 5;
    }

    public void update(int newX, int newY) {
        int diff = x - newX;

        if (diff > 0) {
            imageIcon = imageIconLeft;
        } else if (diff < 0) {
            imageIcon = imageIconRight;
        }

        if (newX <= WIDTH - width)
            x = newX;
        else {
            x = WIDTH - width;
        }
        if (newY <= HEIGHT - height)
            y = newY;
        else {
            y = HEIGHT - height;
        }

    }

    public void grow0(){
        grow = 0;

        imageIconLeft = img0L;
        imageIconRight = img0R;
        if (imageIcon == imageIconLeft)
            imageIcon = img0L;
        else imageIcon = img0R;

        width = 140;
        height = 90;
    }

    public void grow1() {
        grow = 1;

        imageIconLeft = img1L;
        imageIconRight = img1R;
        if (imageIcon == imageIconLeft)
            imageIcon = img1L;
        else imageIcon = img1R;

        width += 50;
        height += 50;
    }

    public void grow2() {
        grow = 2;

        imageIconLeft = img2L;
        imageIconRight = img2R;
        if (imageIcon == imageIconLeft)
            imageIcon = img2L;
        else imageIcon = img2R;

        width += 50;
        height += 50;
    }

    public void grow3() {
        grow = 3;

        imageIconLeft = img3L;
        imageIconRight = img3R;
        if (imageIcon == imageIconLeft)
            imageIcon = img3L;
        else imageIcon = img3R;

        width += 50;
        height += 50;
    }
}
