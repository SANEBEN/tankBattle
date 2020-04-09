package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

/**
 * @author 昭阳
 * @date 2020/4/9 8:22
 */
public class AutoFireScheduleService extends ScheduledService<String> {
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() {
                Game.enemyTanks.forEach(enemyTank -> Game.bullets.add(enemyTank.fire()));
                return null;
            }
        };
    }
}
