package sample.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class was made to refactor the code.
 * The class has only one method. it is static in order to call the method without creating and object.
 */
public class Open {
    /**
     * This method opens windows after pressing appropriate buttons.
     *
     * @param button   is a field which keeps information about the button that was pressed.
     * @param title    is a field which keeps a title of the window, which must be opened
     * @param location is a field which keeps a title of .fxml file of next window.
     */
    public static void PressedToOpen(Button button, String title, String location) {
        String Location = "/sample/Views/" + location + ".fxml";
        button.getScene().getWindow().hide();
        double x = button.getScene().getWindow().getX();
        double y = button.getScene().getWindow().getY();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Open.class.getResource(Location));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setX(x);
        stage.setY(y);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
