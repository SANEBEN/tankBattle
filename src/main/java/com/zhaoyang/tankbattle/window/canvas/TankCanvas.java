package com.zhaoyang.tankbattle.window.canvas;

import com.zhaoyang.tankbattle.entity.tank.Tank;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.Canvas;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 昭阳
 * @date 2020/3/28 17:21
 */
@Log
public class TankCanvas extends Canvas {

    private double width, height;

    public TankCanvas(double width, double height) {
        super(width, height);

        this.width = width;
        this.height = height;

        Game.tankCanvas = this;

        Game.InitPlayer();
        Game.InitNpc();
    }

    public void cleanCanvas(){
        getGraphicsContext2D().clearRect(0, 0, width, height);
    }

    public void draw(){
        List<Tank> tanks = new ArrayList<>(Game.enemyTanks);
        tanks.add(Game.playerTank);
        for (Tank tank : tanks) {
            tank.draw();
        }
    }
}
