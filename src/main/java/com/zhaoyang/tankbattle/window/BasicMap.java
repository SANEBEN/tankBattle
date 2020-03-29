package com.zhaoyang.tankbattle.window;

import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.entity.tank.PlayerTank;
import com.zhaoyang.tankbattle.util.ThreadFactory;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.window.canvas.BulletCanvas;
import com.zhaoyang.tankbattle.window.canvas.TankCanvas;
import com.zhaoyang.tankbattle.window.canvas.WallCanvas;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.util.*;

/**
 * @author 昭阳
 * @date 2020/3/27 8:32
 */
@Log
public class BasicMap extends Application {
    public void start(Stage primaryStage) {
        load(primaryStage);
        primaryStage.show();
    }

    public void load(Stage stage) {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/basic_map.fxml"));

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 960, 640); // 30 x 20
//        MainCanvas mainCanvas = new MainCanvas(960, 640);//30x20
//        mainCanvas.getGc().setFill(Color.color(255, 255, 255, 0));

        WallCanvas wallCanvas = new WallCanvas(960, 640);
        wallCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));//设置背景透明

        TankCanvas tankCanvas = new TankCanvas(960, 640);
        tankCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));

        BulletCanvas bulletCanvas = new BulletCanvas(960, 640);
        bulletCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));

        root.getChildren().addAll(tankCanvas, bulletCanvas, wallCanvas);
        scene.setFill(Color.BLACK);
        stage.setTitle("坦克大战2010————单人游戏");
        stage.setScene(scene);

        //http://blog.sina.com.cn/s/blog_ed8cec680102vlxz.html

        Set<KeyCode> press_key = Collections.synchronizedSet(new HashSet<>());

        scene.setOnKeyPressed(event -> {
//            log.info("press   " + event.getCode().getName());
//            if (press_key.contains(event.getCode())) {
//                press_key.add(event.getCode());
//            }
            PlayerTank playerTank = Game.playerTank;
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
                    Game.addBullet(playerTank.fire());
            }
        });

//        scene.setOnKeyReleased(event -> {
//            log.info("release   " + event.getCode().getName());
//            press_key.remove(event.getCode());
//        });

//        Thread executeThread = new Thread(() -> {
//            PlayerTank playerTank = Game.playerTank;
//            while (true){
//                for (KeyCode code : press_key) {
//                    log.info("execute   " + code.getName());
//                    switch (code) {
//                        case W:
//                            playerTank.setDirection(Direction.UP);
//                            playerTank.move();
//                            break;
//                        case S:
//                            playerTank.setDirection(Direction.DOWN);
//                            playerTank.move();
//                            break;
//                        case A:
//                            playerTank.setDirection(Direction.LEFT);
//                            playerTank.move();
//                            break;
//                        case D:
//                            playerTank.setDirection(Direction.RIGHT);
//                            playerTank.move();
//                            break;
//                        case SPACE:
//                            Game.addBullet(playerTank.fire());
//                    }
//                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

//        ThreadFactory.setThreads(executeThread);
//
//        ThreadFactory.execute(executeThread);


        stage.setOnCloseRequest(event -> ThreadFactory.stopAll());
    }
}
