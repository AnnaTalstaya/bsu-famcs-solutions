import javax.swing.*;
import java.util.ArrayList;

public class LittleFish extends Fish {

    private double dx, dy;

    public LittleFish(int speed, ImageIcon imageIconLeft, ImageIcon imageIconRight, int width, int height, int x, int y) {
        super(speed, imageIconLeft, imageIconRight, width, height, x, y);

        double angle = Math.toRadians(Math.random()*360);
        dx = Math.sin(angle) * speed;
        dy = Math.cos(angle) * speed;
    }

    public void update() {

        if(dx >=0)
            imageIcon = imageIconRight;
        else if(dx < 0)
            imageIcon = imageIconLeft;

        x += dx;
        y += dy;

        //если вышел за границу
        if (x < -50 && dx < 0) dx = -dx;
        else if (x > WIDTH + 50 && dx > 0) dx = -dx;

        if (y < -100 && dy < 0) dy = -dy;
        else if (y > HEIGHT - 100 && dy > 0) dy = -dy;

    }
}
