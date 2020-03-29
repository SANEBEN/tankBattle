package com.zhaoyang.tankbattle.window.canvas;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.Canvas;

/**
 * @author 昭阳
 * @date 2020/3/28 17:21
 */
public class TankCanvas extends Canvas {

    public TankCanvas(double width, double height) {
        super(width, height);
        Game.tank_canvas_gc = getGraphicsContext2D();

        Game.InitPlayer();
        Game.InitNpc();
    }
}
