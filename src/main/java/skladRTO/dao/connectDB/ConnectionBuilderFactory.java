package skladRTO.dao.connectDB;

public class ConnectionBuilderFactory {
    public static ConnectionBuilder getConnectionBuilder() {
        return new ComboConnectionBuilder();
    }
}
