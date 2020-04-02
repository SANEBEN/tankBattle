package com.zhaoyang.tankbattle.window.canvas;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.Canvas;

/**
 * @author 昭阳
 * @date 2020/4/2 8:56
 */
public class AnimationCanvas extends Canvas {

    private double width, height;

    public AnimationCanvas(double width, double height) {
        super(width, height);
        this.width = width;
        this.height = height;
        Game.animationCanvas = this;
    }

    public void cleanCanvas() {
        getGraphicsContext2D().clearRect(0, 0, width, height);
    }
}
