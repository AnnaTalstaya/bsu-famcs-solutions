import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public enum States {MENU, GAME, RULES};
    private States state = States.MENU;

    private Component component;
    private Menu menu;
    private Game game;

    public MyFrame(){
        super("Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setUndecorated(true);

        menu = new Menu(this);
        add(menu);
        component = menu;

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setState(States state) {
        this.state = state;
        stateChanged();
    }

    public void stateChanged(){
        if(state.equals(States.MENU)){
            remove(component);
            menu = new Menu(this);
            add(menu);
            component = menu;
            revalidate();
        }
        else if(state.equals(States.GAME)){
            remove(component);
            game = new Game(this);
            add(game);
            component = game;
            revalidate();
        }
        else if(state.equals(States.RULES)){

        }
    }
}
