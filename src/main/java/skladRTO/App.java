package skladRTO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import skladRTO.fx.sceneFX.CreateScene;

import java.io.IOException;
import java.util.Objects;


public class App extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        CreateScene createScene = new CreateScene();
        createScene.createScene("Authorization.fxml", 350, 250);

    }
}