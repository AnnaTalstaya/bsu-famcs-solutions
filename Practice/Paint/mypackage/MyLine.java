package mypackage;

import java.awt.*;
import java.util.ArrayList;

public class MyLine {
    private Color color;

    ArrayList<Point> points = new ArrayList<>();

    public MyLine(Color color) {
        this.color = color;
    }

    public void addPoint(Point point){
        points.add(point);
    }

    public int getX(int i){
        return (int)points.get(i).getX();
    }

    public int getY(int i){
        return (int)points.get(i).getY();
    }

    public void draw(Graphics g){
        for (int i = 0; i < points.size() - 1; i++) {
            g.setColor(color);
            g.drawLine((int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i + 1).getX(), (int)points.get(i + 1).getY());
        }
    }
}
