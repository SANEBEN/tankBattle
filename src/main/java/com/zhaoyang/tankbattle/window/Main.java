package com.zhaoyang.tankbattle.window;

import com.zhaoyang.tankbattle.util.game.Music;
import com.zhaoyang.tankbattle.util.WindowsManage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        load(primaryStage);
        WindowsManage.addStage("Main", primaryStage);
    }

    public void load(Stage stage) throws IOException, URISyntaxException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        stage.setTitle("坦克大战2010");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
        stage.setResizable(false);
        AudioClip audioClip = Music.start();
        audioClip.setCycleCount(50);
        stage.setOnHiding(event -> audioClip.stop());
    }

    public static void reOpen() {
        if (WindowsManage.getByName("Main") != null) {
            Stage main = WindowsManage.getByName("Main");
            try {
                main.show();
                AudioClip audioClip = Music.start();
                audioClip.setCycleCount(50);
                main.setOnHiding(e -> audioClip.stop());
                main.setResizable(false);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Main main = new Main();
                Stage stage = new Stage();
                main.load(stage);
                WindowsManage.addStage("Main", stage);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
