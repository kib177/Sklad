package skladRTO.api.FX.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductInfoFX {
    private SimpleIntegerProperty id;
    private SimpleStringProperty des;
    private SimpleStringProperty articul;
    private SimpleStringProperty date;

    public ProductInfoFX(int id, String des, String articul, String date) {
        this.id = new SimpleIntegerProperty(id);
        this.des = new SimpleStringProperty(des);
        this.articul = new SimpleStringProperty(articul);
        this.date = new SimpleStringProperty(date);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDes() {
        return des.get();
    }

    public SimpleStringProperty desProperty() {
        return des;
    }

    public void setDes(String des) {
        this.des.set(des);
    }

    public String getArticul() {
        return articul.get();
    }

    public SimpleStringProperty articulProperty() {
        return articul;
    }

    public void setArticul(String articul) {
        this.articul.set(articul);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    @Override
    public String toString() {
        return "ProductInfoFX{" +
                "id=" + id +
                ", des=" + des +
                ", articul=" + articul +
                ", date=" + date +
                '}';
    }


}
