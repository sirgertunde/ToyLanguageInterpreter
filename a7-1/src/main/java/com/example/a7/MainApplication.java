package com.example.a7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ControllerMain cm = fxmlLoader.getController();
        stage.setScene(scene);
        stage.show();
        FXMLLoader fxmlLoader2 = new FXMLLoader(MainApplication.class.getResource("selectProgram.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load());
        ControllerSelectProgram csp = fxmlLoader2.getController();
        csp.setProgramController(cm);
        Stage stage1 = new Stage();
        stage1.setScene(scene2);
        stage1.show();

    }

    public static void main(String[] args) {
        launch();
    }
}