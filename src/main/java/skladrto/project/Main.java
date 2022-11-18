package skladrto.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

//        проверка методов
//        OrdersDAO ordersDAO = new OrdersDAO();
//        ordersDAO.showListOfOrders();
//        System.out.println();
//        ordersDAO.searchName("пила");
//        System.out.println();
//        ordersDAO.searchArticle("222");
//        System.out.println();
//        ordersDAO.searchSubdivision(2);
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Controllers/Authorization.fxml"));
        stage.setTitle("Склад РТО");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


}