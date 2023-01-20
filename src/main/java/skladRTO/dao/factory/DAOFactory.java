package skladRTO.dao.factory;

import skladRTO.api.newModels.ModelsFX;
import skladRTO.dao.modelDAO.newModelDAO.*;

public class DAOFactory {
    //String потом поменять
    //static потом поменять
    public static InterfaceDAOGeneric getInterfaceDAO(ModelsFX type) {
        InterfaceDAOGeneric toReturn = null;
        switch (type) {
            case AUTHENTICATION:
                toReturn = new AuthenticationDAOGenericNew();
                break;
            case MACHINE:
                toReturn = new MachineDAOGenericNew();
                break;
            case ORDER:
                toReturn = new OrderDAOGenericNew();
                break;
            case PRODUCT:
                toReturn = new ProductDAOGenericNew();
                break;
            case PRODUCT_INFO:
                toReturn = new ProductInfoDAONewGeneric();
                break;
            case STATUS:
                toReturn = new StatusDAOGenericNew();
                break;
            case STATUS_USER:
                toReturn = new StatusUserDAONewGeneric();
                break;
            case UNIT:
                toReturn = new UnitDAOGenericNew();
                break;
            case USER:
                toReturn = new UserDAOnew();
                break;

            default:
                throw new IllegalArgumentException("Wrong doughnut type:" + type);
        }
        return toReturn;
    }
}
