package skladRTO;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import skladRTO.api.newModels.ModelsFX;
import skladRTO.api.newModels.StatusUserFX;
import skladRTO.api.newModels.UserFX;
import skladRTO.business.Manager;
import skladRTO.business.ModelManagerDAO;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("Происходит загрузка программы");

//      App.main(args);
//        OrdersDAO ordersDAO = new OrdersDAO();
//        System.out.println(ordersDAO.showListOfOrders());
        UserFX userFX = new UserFX();
        System.out.println(userFX);

        StatusUserFX statusFX = new StatusUserFX("sadas");
        System.out.println(statusFX);
        Manager manager = new ModelManagerDAO(ModelsFX.USER);
        manager.addObject(userFX);
        System.out.println("ID -> " + manager);
        manager = new ModelManagerDAO(ModelsFX.STATUS_USER);
        manager.addObject(statusFX);
        System.out.println("ID -> " + manager);


    }

}

