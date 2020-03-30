package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

/**
 * @author 昭阳
 * @date 2020/3/30 9:05
 */
public class TankScheduleService extends ScheduledService<String> {
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() {
                Game.tankCanvas.cleanCanvas();
                Game.tankCanvas.draw();
                return null;
            }
        };
    }
}
