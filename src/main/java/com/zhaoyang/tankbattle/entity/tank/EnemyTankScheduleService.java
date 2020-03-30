package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import lombok.extern.java.Log;

/**
 * @author 昭阳
 * @date 2020/3/30 9:14
 */
@Log
public class EnemyTankScheduleService extends ScheduledService<String> {
    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() {
                Game.enemyTanks.forEach(EnemyTank::findTheWay);
                return null;
            }
        };
    }
}
