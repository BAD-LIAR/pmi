module ru.itis.les1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires commons.io;


    opens ru.itis.les1 to javafx.fxml;
    exports ru.itis.les1;
}