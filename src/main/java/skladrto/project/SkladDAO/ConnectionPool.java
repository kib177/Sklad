//package skladrto.project.SkladDAO;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class ConnectionPool {
//
//    private ConnectionPool() {
//        //private constructor
//    }
//
//    private static ConnectionPool instance = null;
//
//    public static ConnectionPool getInstance() {
//        if (instance == null)
//            instance = new ConnectionPool();
//        return instance;
//    }
//
//    public Connection getConnection() {
//        Context ctx;
//        Connection c = null;
//        try {
//            ctx = new InitialContext();
//            DataSource ds = (DataSource) ctx.lookup("jdbc:mysql://localhost:3306/sklad");
//            c = ds.getConnection();
//        } catch (NamingException | SQLException e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
//}