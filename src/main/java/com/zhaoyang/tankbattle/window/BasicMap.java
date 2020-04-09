package com.zhaoyang.tankbattle.window;

import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.util.ThreadFactory;
import com.zhaoyang.tankbattle.util.WindowsManage;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.window.canvas.AnimationCanvas;
import com.zhaoyang.tankbattle.window.canvas.BulletCanvas;
import com.zhaoyang.tankbattle.window.canvas.TankCanvas;
import com.zhaoyang.tankbattle.window.canvas.WallCanvas;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import static com.zhaoyang.tankbattle.util.game.Game.playerTank;

/**
 * @author 昭阳
 * @date 2020/3/27 8:32
 */
@Log
public class BasicMap extends Application {

    static boolean isMove = false;

    public void start(Stage primaryStage) {
        load(primaryStage);
        primaryStage.setOnCloseRequest(event -> Main.reOpen());
        primaryStage.show();
    }

    public void load(Stage stage) {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 960, 640); // 30 x 20

        WallCanvas wallCanvas = new WallCanvas(960, 640);
        wallCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));//设置背景透明

        TankCanvas tankCanvas = new TankCanvas(960, 640);
        tankCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));

        BulletCanvas bulletCanvas = new BulletCanvas(960, 640);
        bulletCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));

        AnimationCanvas animationCanvas = new AnimationCanvas(960, 640);
        animationCanvas.getGraphicsContext2D().setFill(Color.color(1, 1, 1, 0));

        root.getChildren().addAll(bulletCanvas, tankCanvas, wallCanvas, animationCanvas);
        scene.setFill(Color.BLACK);
        stage.setTitle("坦克大战2010————单人游戏");
        stage.setScene(scene);

        //http://blog.sina.com.cn/s/blog_ed8cec680102vlxz.html

        scene.setOnKeyPressed(new KeyPressHandler());

        scene.setOnKeyReleased(new KeyReleaseHandler());

        stage.setOnCloseRequest(event -> ThreadFactory.stopAll());
    }
}

@Log
class KeyPressHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                playerTank.setDirection(Direction.UP);
                BasicMap.isMove = true;
                playerTank.move();
                break;
            case S:
                playerTank.setDirection(Direction.DOWN);
                BasicMap.isMove = true;
                playerTank.move();
                break;
            case A:
                playerTank.setDirection(Direction.LEFT);
                BasicMap.isMove = true;
                playerTank.move();
                break;
            case D:
                playerTank.setDirection(Direction.RIGHT);
                BasicMap.isMove = true;
                playerTank.move();
                break;
            case SPACE:
                Game.addBullet(playerTank.fire());
        }
    }
}

class KeyReleaseHandler implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case S:
            case A:
            case D:
                BasicMap.isMove = false;
        }
    }
}

class moveThread extends Thread {
    @Override
    public void run() {
        while (BasicMap.isMove) {
            playerTank.move();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
