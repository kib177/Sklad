package skladRTO.fx.sceneFX;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CreateScene {
    private Stage stage = new Stage();
    private FXMLLoader loader;

    public void createScene(String nameFXML, int width, int heigth, Boolean Resizable) {
//        int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
//        int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
//        int sceneWidth = (screenWidth*98)/100;
//        int sceneHeight = (screenHeight*90)/100;

        if(!stage.isShowing()){
            loader = new FXMLLoader(getClass().getResource(nameFXML));
            try {
                Parent root = loader.load();

                stage.setTitle("Делком 40");
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("1198294.png"))));
                stage.setResizable(Resizable);
                stage.setScene(new Scene(root, width, heigth));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            stage.toFront();
        }
    }

    public void close(){
        stage.close();
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
