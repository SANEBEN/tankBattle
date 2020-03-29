package com.zhaoyang.tankbattle.entity.bullet;

import com.zhaoyang.tankbattle.util.game.Game;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

/**
 * @author 昭阳
 * @date 2020/3/27 15:26
 */
public class BulletScheduledService extends ScheduledService<String> {
    @Override
    protected Task<String> createTask() {

        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                Game.bullets.forEach(bullet -> {
                    bullet.clean();
                    if (bullet.move()) {
                        Platform.runLater(bullet::draw);
                    }
                });
                Game.checkBullet();
                return null;
            }
        };
    }
}
