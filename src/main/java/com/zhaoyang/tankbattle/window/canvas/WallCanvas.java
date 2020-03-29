package com.zhaoyang.tankbattle.window.canvas;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.Canvas;

/**
 * @author 昭阳
 * @date 2020/3/28 17:20
 */
public class WallCanvas extends Canvas {

    public WallCanvas(double width, double height) {
        super(width, height);
        Game.wall_canvas_gc = getGraphicsContext2D();

        Game.InitMap();
    }
}
