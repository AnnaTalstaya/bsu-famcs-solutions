/**
 * Created by Anna Talstaya on 29.04.2019.
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *<p>Main class of client application</p>
 * extends {@link Application}
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Notebook");
        primaryStage.setScene(new Scene(root, 828, 780));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
