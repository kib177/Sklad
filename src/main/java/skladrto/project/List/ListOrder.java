package skladrto.project.List;

import skladrto.project.Model.Tovar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListOrder {
    private static ObservableList<Tovar> usersData = FXCollections.observableArrayList(); // тип листа хуй знает какой, но таблица понимает толлько его агрегирует Tovar

    public static void create(String name, String articul, String opis, String oboryd, String status, String kol, String zakazchik) {
        usersData.add(new Tovar(name, articul, opis, oboryd, status, kol, zakazchik)); // так как тип String не подходит намшему листу наши параметры передаем и создаем обьект котоырй закидываем в лист,
        // монжон улучшить но стоит ли?

    }
    public static ObservableList<Tovar> getUsersData() {
        return usersData;
    }

    public static void setUsersData(ObservableList<Tovar> usersData) {
        ListOrder.usersData = usersData;
    }
}
