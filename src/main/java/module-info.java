module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;
//    requires org.apache.logging.log4j.core;
//    requires com.google.gson;
//    requires log4j;
//    requires org.apache.log4j;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports logic;
    opens logic to javafx.fxml;
}