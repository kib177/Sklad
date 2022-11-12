module brigi.skladrto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;
    requires java.naming;

    opens skladrto.project to javafx.fxml;
    exports skladrto.project;
    exports skladrto.project.Model;
    opens skladrto.project.Model to javafx.fxml;
    exports skladrto.project.List;
    opens skladrto.project.List to javafx.fxml;
}