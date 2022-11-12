/* обьекте с параметрами соответствующие данным заказа из бд
стандартный набор get set и др методов
 */
package skladrto.project.Model;

import javafx.beans.property.SimpleStringProperty;
import java.util.Objects;

public class Tovar { // тип данных нужно наследовать от класса SimpleТИПProperty для работы с таблицами javaFX
    private SimpleStringProperty name;
    private SimpleStringProperty articul;
    private SimpleStringProperty opis;
    private SimpleStringProperty oboryd;
    private SimpleStringProperty status;
    private SimpleStringProperty kol;
    private SimpleStringProperty zakazchik;

    public Tovar(String name, String articul, String opis, String oboryd, String status, String kol, String zakazchik) {
        // конструктор принимает String и создает новый объект унаследованного типа, нужно делать ссылки слабые либо подумать как не создавать обьект дважды
        // но без этого типа таблицы не будут заполняться String они не понимают
        // можно подумать что  то вместо таблиц либбо просто слабую ссылку на запрос
        this.name = new SimpleStringProperty(name);
        this.articul = new SimpleStringProperty(articul);
        this.opis = new SimpleStringProperty(opis);
        this.oboryd = new SimpleStringProperty(oboryd);
        this.status = new SimpleStringProperty(status);
        this.kol = new SimpleStringProperty(kol);
        this.zakazchik = new SimpleStringProperty(zakazchik);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getOpis() {
        return opis.get();
    }

    public SimpleStringProperty opisProperty() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis.set(opis);
    }

    public String getOboryd() {
        return oboryd.get();
    }

    public SimpleStringProperty oborydProperty() {
        return oboryd;
    }

    public void setOboryd(String oboryd) {
        this.oboryd.set(oboryd);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getKol() {
        return kol.get();
    }

    public SimpleStringProperty kolProperty() {
        return kol;
    }

    public void setKol(String kol) {
        this.kol.set(kol);
    }

    public String getZakazchik() {
        return zakazchik.get();
    }

    public SimpleStringProperty zakazchikProperty() {
        return zakazchik;
    }

    public void setZakazchik(String zakazchik) {
        this.zakazchik.set(zakazchik);
    }

    @Override
    public String toString() {
        return "Tovar{" +
                "name=" + name +
                ", articul=" + articul +
                ", opis=" + opis +
                ", oboryd=" + oboryd +
                ", status=" + status +
                ", kol=" + kol +
                ", zakazchik=" + zakazchik +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tovar tovar = (Tovar) o;
        return Objects.equals(name, tovar.name) && Objects.equals(articul, tovar.articul) && Objects.equals(opis, tovar.opis) && Objects.equals(oboryd, tovar.oboryd) && Objects.equals(status, tovar.status) && Objects.equals(kol, tovar.kol) && Objects.equals(zakazchik, tovar.zakazchik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, articul, opis, oboryd, status, kol, zakazchik);
    }
}
