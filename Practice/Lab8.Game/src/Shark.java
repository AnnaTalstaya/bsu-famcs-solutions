import javax.swing.*;

public class Shark extends Fish {
    public Shark(int speed, ImageIcon imageIconLeft, ImageIcon imageIconRight, int width, int height, int x, int y) {
        super(speed, imageIconLeft, imageIconRight, width, height, x, y);
    }

    public void update(){
        x+=speed;
    }
}
