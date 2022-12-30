module skladrto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    requires java.naming;
    requires org.apache.logging.log4j;
    requires junit;
    requires java.desktop;
    requires org.apache.poi.ooxml;


    opens skladRTO to javafx.fxml;
    exports skladRTO;


    exports skladRTO.api.lists;
    opens skladRTO.api.lists to javafx.fxml;
    exports skladRTO.fx.Controllers;
    opens skladRTO.fx.Controllers to javafx.fxml;

    exports skladRTO.fx.sceneFX;
    opens skladRTO.fx.sceneFX to javafx.fxml;
    exports skladRTO.api.FX.models;
    opens skladRTO.api.FX.models to javafx.fxml;
    exports skladRTO.api.FX.lists;
    opens skladRTO.api.FX.lists to javafx.fxml;
    exports skladRTO.api.models;
    opens skladRTO.api.models to javafx.fxml;


}