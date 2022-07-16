package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
public class logIn extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        loadLogin(stage);
    }
    public static Logger logger = LogManager.getLogger(logIn.class);
    public static void main(String[] args) throws IOException {
        BuildGson.buildGson();
        logger.info("application Start");
        launch();
    }
    public static void loadLogin(Stage stage) throws IOException {
        logger.info("logging page Loaded");
        FXMLLoader fxmlLoader = new FXMLLoader(logIn.class.getResource("logInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("EDU");
        stage.setScene(scene);
        logInController loginCntr = fxmlLoader.getController();
        loginCntr.backgroundImage.setImage(new Image("C:\\Users\\Asus\\Desktop\\demo\\src\\main\\java\\image\\loginBackground.jpeg"));
        loginCntr.setCaptch();
        stage.show();
    }
}