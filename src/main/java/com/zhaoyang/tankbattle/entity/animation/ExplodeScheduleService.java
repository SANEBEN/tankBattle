package com.zhaoyang.tankbattle.entity.animation;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

/**
 * @author 昭阳
 * @date 2020/4/2 9:24
 */
public class ExplodeScheduleService extends ScheduledService<String> {

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() {
                for (Explode explode : Game.explodes) {
                    if(!explode.next()){
                        explode.clean();
                        Game.removeExplode(explode);
                    }
                }
                return null;
            }
        };
    }
}
