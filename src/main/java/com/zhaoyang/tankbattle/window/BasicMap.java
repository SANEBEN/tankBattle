package com.zhaoyang.tankbattle.window;

import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.entity.tank.PlayerTank;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.window.canvas.MainCanvas;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author 昭阳
 * @date 2020/3/27 8:32
 */
public class BasicMap extends Application {
    public void start(Stage primaryStage) {
        load(primaryStage);
        primaryStage.show();
    }

    public void load(Stage stage) {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/basic_map.fxml"));

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 960, 640); // 30 x 20
        MainCanvas mainCanvas = new MainCanvas(960, 640);//30x20
        root.getChildren().add(mainCanvas);
        scene.setFill(Color.BLACK);
        stage.setTitle("坦克大战2010————单人游戏");
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            PlayerTank playerTank = Game.playerTanks.get(0);
            switch (event.getCode()) {
                case W:
                    playerTank.setDirection(Direction.UP);
                    playerTank.move();
                    break;
                case S:
                    playerTank.setDirection(Direction.DOWN);
                    playerTank.move();
                    break;
                case A:
                    playerTank.setDirection(Direction.LEFT);
                    playerTank.move();
                    break;
                case D:
                    playerTank.setDirection(Direction.RIGHT);
                    playerTank.move();
                    break;
                case SPACE:
                    Bullet bullet = playerTank.fire();
                    Game.addBullet(bullet);
                    break;
            }
        });

//        stage.setResizable(false);
    }
}
