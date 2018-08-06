import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Menu extends JPanel{
    private MyFrame frame;
    private Image imgMenu = new ImageIcon("images/menu.jpg").getImage();
    private ImageIcon imageButton1 = new ImageIcon("images/button.png");
    private ImageIcon imageButton2 = new ImageIcon("images/button_yellow.png");

    private JLabel buttonPlay = new JLabel(imageButton1);
    private JLabel buttonExit = new JLabel(imageButton1);

    private int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private static Sounds menuMusic;

    public Menu(MyFrame frame) {
        this.frame = frame;

        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weighty = 0;
        gbc.insets = new Insets(40, 0, 40, 0);
        gbc.gridwidth  = GridBagConstraints.REMAINDER;

        gbl.setConstraints(buttonPlay, gbc);
        gbl.setConstraints(buttonExit, gbc);
        add(buttonPlay);
        add(buttonExit);

        setTextOfButton(buttonPlay, "Play");
        //setTextOfButton(buttonRules, "Rules");
        setTextOfButton(buttonExit, "Exit");

        buttonPlay.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                menuMusic.stop();
                frame.setState(MyFrame.States.GAME);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonPlay.setIcon(imageButton2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonPlay.setIcon(imageButton1);
            }
        });


        buttonExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                buttonExit.setIcon(imageButton2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                buttonExit.setIcon(imageButton1);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        menuMusic = new Sounds("sounds/thomas-newman-field-trip-ost-finding-nemo.wav", 0.90);
        menuMusic.play();
        menuMusic.setVolume();
        menuMusic.repeat();

        setFocusable(true);
    }

    public void setTextOfButton(JLabel button, String text){
        Font font = new Font("Ravie", Font.ITALIC, 52);

        button.setFont(font);
        button.setText(text);
        button.setHorizontalTextPosition(JLabel.CENTER);
        button.setVerticalTextPosition(JLabel.CENTER);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imgMenu, 0, 0, WIDTH, HEIGHT, this);

    }

}
