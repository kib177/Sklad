package skladrto.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import skladrto.project.Model.Order;
import skladrto.project.Model.User;
import skladrto.project.RequestsDB.Get.GetOrdersDAO;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

        User user = new User(1, "Кирилл", "Бриги", 1);
        Order order = new Order(14, "проверка метода", 1,"2020",2322);
        GetOrdersDAO getOrdersDAO = new GetOrdersDAO();
        getOrdersDAO.add(order);

       // launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Controllers/Authorization.fxml"));
        stage.setTitle("Склад РТО");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }


}