package skladRTO;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) throws Exception {
        logger.info("Происходит загрузка программы");

        App.main(args);
    }

}

