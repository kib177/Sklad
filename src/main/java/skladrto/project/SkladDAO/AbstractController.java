//package skladrto.project.SkladDAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//
//public abstract class AbstractController<E, K> {
//    private final Connection connection;
//    private final ConnectionPool connectionPool;
//
//    public AbstractController() {
//        connectionPool = ConnectionPool.getConnectionPool();
//        connection = connectionPool.getConnection();
//    }
//
//    public abstract List<E> getUser();
//
//    public abstract List<E> getOrder();
//
//    public abstract List<E> addOrder();
//
//    public abstract List<E> addUser();
//
//    public abstract E getEntityById(K id);
//
//    public abstract E update(E entity);
//
//    public abstract boolean delete(K id);
//
//    public abstract boolean create(E entity);
//
//    public void returnConnectionInPool() {
//        connectionPool.return Connection(connection);
//    }
//
//    public PreparedStatement getPrepareStatement(String sql) {
//        PreparedStatement ps = null;
//        try {
//            ps = connection.prepareStatement(sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return ps;
//    }
//
//    public void closePrepareStatement(PreparedStatement ps) {
//        if (ps != null) {
//            try {
//                ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//}
//}

