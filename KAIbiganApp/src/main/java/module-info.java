module ph.edu.dlsu.lbycpei.kaibiganapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.xml.crypto;


    opens ph.edu.dlsu.lbycpei.kaibiganapp.controllers to javafx.fxml;
    exports ph.edu.dlsu.lbycpei.kaibiganapp;
}