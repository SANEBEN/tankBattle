package com.zhaoyang.tankbattle.window.canvas;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.Canvas;

/**
 * @author 昭阳
 * @date 2020/3/28 17:21
 */
public class BulletCanvas extends Canvas {

    private double width, height;

    public BulletCanvas(double width, double height) {
        super(width, height);
        this.width = width;
        this.height = height;
        Game.bulletCanvas = this;
    }

    public void cleanCanvas(){
        getGraphicsContext2D().clearRect(0, 0, width, height);
    }

    public void draw(){
        Game.bullets.forEach(BaseObject::draw);
    }
}
