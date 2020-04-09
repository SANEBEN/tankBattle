package com.zhaoyang.tankbattle.controller;

import com.zhaoyang.tankbattle.util.WindowsManage;
import com.zhaoyang.tankbattle.window.BasicMap;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainController {

    @FXML
    ImageView singleGame;

    @FXML
    ImageView doubleGame;

    @FXML
    Pane root;

    @FXML
    public void startNewSingleGame() throws Exception {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.hide();
        BasicMap basicMap = new BasicMap();
        basicMap.start(new Stage());
    }

    @FXML
    public void startNewDoubleGame() {

    }

    @FXML
    public void test(KeyEvent event) {
        System.out.println(event.getCode());
    }
}
