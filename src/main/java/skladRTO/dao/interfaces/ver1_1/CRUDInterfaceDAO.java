package skladRTO.dao.interfaces.ver1_1;

import javafx.collections.ObservableList;

import java.util.List;

public interface CRUDInterfaceDAO<K extends Number, T> {

    public List<T> findAll();

    public void create(T entity);

    public void delete(K id);

    public void update(T entity);

}
