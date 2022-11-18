package skladrto.project.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateScene {
private Stage stage;
private FXMLLoader loader;
private Parent root;

    public void createScene(String nameFXML,String title, int width, int heigth) {
        loader = new FXMLLoader(getClass().getResource(nameFXML));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        root = loader.getRoot();
        stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root, width, heigth));
        stage.show();
    }

    public void closeStage(){
        stage.close();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }
}
