package com.zhaoyang.tankbattle.window.canvas;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 昭阳
 * @date 2020/3/26 19:28
 */

@Getter
@Setter
public class MainCanvas extends Canvas {

    private GraphicsContext gc;

    public MainCanvas(double width, double height) {
        super(width, height);

        gc = getGraphicsContext2D();

        Game.setGc(gc);
        //初始化游戏地图
        Game.InitMap();

        Game.InitNpc(gc);

        Game.InitPlayer(gc);


    }

    public void draw() {

        gc.save();

        Game.InitNpc(gc);

        Game.playerTanks.forEach(playerTank -> playerTank.draw(gc));

        gc.restore();
    }

}
