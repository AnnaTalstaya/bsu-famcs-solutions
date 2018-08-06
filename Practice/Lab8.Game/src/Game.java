import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.security.Key;
import java.util.ArrayList;
import java.util.TimerTask;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Game extends JPanel implements ActionListener, KeyListener, MouseMotionListener {
    private MyFrame frame;
    private Image imgGame = new ImageIcon("images/game.jpg").getImage();
    private Image imgLives = new ImageIcon("images/live.png").getImage();

    private int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private Timer mainTimer = new Timer(30, this); //таймер  this- инициализация таймер в момет создания класса Panel //запускает actionEvent через определенные промежутки
    private java.util.Timer sharkTimer = new java.util.Timer();

    private Player player;
    private ArrayList<LittleFish> littleFishes = new ArrayList<>();

    private Sounds gameMusic;
    private Sounds eatSound = new Sounds("sounds/eat.wav", 0.80);
    private Sounds oupsSound = new Sounds("sounds/loozer.wav", 0.90);
    private Sounds gameOverSound = new Sounds("sounds/zvuk-neudachi-ua-ua-ua-uaaa.wav", 0.80);

    private final int NUMBER_FISH = 15;
    private final int NUMBER_FISH2 = 9;

    private final int MIN_NUMBER_FISH = 10;

    private Shark shark = new Shark(2, new ImageIcon("images/shark3_left.png"),
            new ImageIcon("images/shark3_right.png"), 500, 300,
            0,
            300 + (int) (Math.random() * (HEIGHT - 300)));
    private ArrayList<Shark> sharks = new ArrayList<>();

    public Game(MyFrame frame) {
        this.frame = frame;

        mainTimer.start();
        player = new Player(15, new ImageIcon("images/fish1_left.png"), new ImageIcon("images/fish1_right.png"),
                140, 90,
                (int) MouseInfo.getPointerInfo().getLocation().getX(),
                (int) MouseInfo.getPointerInfo().getLocation().getY(),
                3,
                new ImageIcon("images/fish4_left.png"), new ImageIcon("images/fish4_right.png"),
                new ImageIcon("images/fish2_left.png"), new ImageIcon("images/fish2_right.png"),
                new ImageIcon("images/fish3_left.png"), new ImageIcon("images/fish3_right.png")
        );

        int from = 10, to = 12;
        for (int i = 0; i < NUMBER_FISH; i++) {
            littleFishes.add(new LittleFish(from + ((int) Math.random() * to), new ImageIcon("images/fish10_left.png"),
                    new ImageIcon("images/fish10_right.png"), 60, 50,
                    (int) (Math.random() * WIDTH),
                    (int) (Math.random() * HEIGHT)));
        }


        //music
        gameMusic = new Sounds("sounds/thomas-newman-wow-finding-nemo.wav", 0.90);
        gameMusic.play();
        gameMusic.setVolume();
        gameMusic.repeat();

        //transparent cursor
        setCursor(this.getToolkit().createCustomCursor(
                new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                new Point(0, 0), "null"));

        addKeyListener(this);
        addMouseMotionListener(this);

        setFocusable(true);
    }


    public void generateFish() {
        int from = 10, to = 12;
        for (int i = 0; i < NUMBER_FISH - MIN_NUMBER_FISH; i++) {
            double x = Math.random();
            int a = 1;
            if (x < 0.5)
                a = -1;

            littleFishes.add(new LittleFish(from + ((int) Math.random() * to), new ImageIcon("images/fish10_left.png"),
                    new ImageIcon("images/fish10_right.png"), 60, 60,
                    a * WIDTH,
                    a * (HEIGHT + (int) (Math.random() * 5))));
        }
    }

    public void generateFish2() {
        int from = 13, to = 15;
        for (int i = 0; i < NUMBER_FISH2; i++) {
            double x = Math.random();
            int a = 1;
            if (x < 0.5)
                a = -1;

            if (a > 0) {
                littleFishes.add(new LittleFish(from + ((int) Math.random() * to), new ImageIcon("images/fish11_left.png"),
                        new ImageIcon("images/fish11_right.png"), 200, 100,
                        a * WIDTH,
                        a * HEIGHT));
            } else {
                littleFishes.add(new LittleFish(from + ((int) Math.random() * to), new ImageIcon("images/fish10_left.png"),
                        new ImageIcon("images/fish10_right.png"), 60, 60,
                        a * WIDTH,
                        a * (HEIGHT + (int) (Math.random() * 5))));
            }

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < littleFishes.size(); i++)
            littleFishes.get(i).update();

        if (player.getLevel() == 1) {
            level1();
            if (player.getScore() == 120) {
                player.setLevel(2);
                player.grow0();
            }
        } else if (player.getLevel() == 2) {
            level2();
            if (player.getScore() == 240) {
                player.setLevel(3);
                player.grow0();

            }
        } else if (player.getLevel() == 3) {
            level3();
            if (player.getScore() == 360) {
                mainTimer.stop();
                JOptionPane.showMessageDialog(this, "Level : " + player.getLevel()+ "\n"+
                        "Score : " + player.getScore() + "\n" +
                        "Rest of lives : " + player.getLives(), "Victory!", INFORMATION_MESSAGE, new ImageIcon("images/victory.png"));

                gameMusic.stop();
                frame.setState(MyFrame.States.MENU);
            }
        }

        if (player.getScore() % 120 == 90 && player.grow != 1)
            player.grow1();
        else if (player.getScore() % 120 == 60 && player.grow != 2)
            player.grow2();
        else if (player.getScore() % 120 == 30 && player.grow != 3)
            player.grow3();

        repaint();
        if (player.getLives() == 0) {
            gameMusic.stop();
            gameOverSound.play();
            mainTimer.stop();

            JOptionPane.showMessageDialog(this, "Level : " + player.getLevel()+ "\n"+
            "Score : " + player.getScore() + "\n" +
            "Rest of lives : " + player.getLives(), "Game Over", INFORMATION_MESSAGE, new ImageIcon("images/sad.png"));

            frame.setState(MyFrame.States.MENU);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgGame, 0, 0, WIDTH, HEIGHT, this);
        requestFocus();

        Font font = new Font("Ravie", Font.ITALIC, 30);
        g.setFont(font);

        int x = 40;
        for (int i = 0; i < player.getLives(); i++) {
            g.drawImage(imgLives, x, 13, 50, 40, this);
            x += 55;
        }

        g.drawString("Level : " + player.getLevel(), 350, 40);
        g.drawString("Score : " + player.getScore(), 630, 40);

        player.draw(g);
        for (LittleFish lf : littleFishes)
            lf.draw(g);


        for (int i = 0; i < sharks.size(); i++) {
            sharks.get(i).update();
            sharks.get(i).draw(g);
        }
        for (int i = 0; i < sharks.size(); i++) {
            if (sharks.get(i).getX() > WIDTH)
                sharks.remove(i);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); //получить код нажатой клавиши

        if (key == KeyEvent.VK_ESCAPE) {  //переход из игры в меню
            gameMusic.stop();
            frame.setState(MyFrame.States.MENU);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        player.update(e.getX(), e.getY());
    }

    public void eatFish() {
        for (LittleFish lf : littleFishes) {
            if (player.getRect().intersects(lf.getRect())) {
                //if size of eaten fish is biggest
                if (lf.width > player.width && lf.height > player.height) {
                    oupsSound.play();
                    int n = player.getLives();
                    player.setLives(n - 1);
                    littleFishes.remove(lf);
                    break;

                } else {
                    eatSound.play();
                    littleFishes.remove(lf);
                    player.fishIsEaten();
                    break;
                }
            }
            else{
                for(int i =0; i < sharks.size(); i++){
                    if (player.getRect().intersects(sharks.get(i).getRect())){
                        oupsSound.play();
                        int n = player.getLives();
                        player.setLives(n - 1);
                        sharks.remove(i);
                        break;
                    }
                }
            }
        }
    }

    public void level1() {
        eatFish();
        if (littleFishes.size() < MIN_NUMBER_FISH)
            generateFish();
    }

    public void level2() {
        eatFish();
        if (littleFishes.size() < MIN_NUMBER_FISH)
            generateFish2();

    }

    public void level3() {
        eatFish();
        sharkTimer.scheduleAtFixedRate(new TimerTask() {
                                           @Override
                                           public void run() {
                                               if (sharks.size() < 1)
                                                   sharks.add(new Shark(20, new ImageIcon("images/shark3_left.png"),
                                                           new ImageIcon("images/shark3_right.png"), 500, 300,
                                                           0,
                                                           (int) (Math.random() * (HEIGHT - 300))));
                                           }

                                       },
                10000,
                600000);

        if (littleFishes.size() < MIN_NUMBER_FISH)
            generateFish2();

    }

}
