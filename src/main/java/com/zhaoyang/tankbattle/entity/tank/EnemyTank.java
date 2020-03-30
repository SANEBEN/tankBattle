package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.ThreadFactory;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.util.game.Img;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 昭阳
 * @date 2020/3/26 19:43
 */
@Log
public class EnemyTank extends Tank {

    @Override
    public Bullet fire() {
        return new Bullet(Img.BULLET_NPC, x, y, getDirection());
    }

    public void findTheWay() {
        if (!move()) {
            Direction new_direction;
            do {
                new_direction = Direction.random(Direction.class);
            } while (new_direction == direction);
            setDirection(new_direction);
        }
    }

    @Override
    public BaseObject collisionDetection() {
        List<BaseObject> baseObjectList = new ArrayList<>();
        baseObjectList.add(Game.playerTank);
        baseObjectList.addAll(Game.walls);
        for (BaseObject baseObject : baseObjectList) {
            double current_center_x = x + Game.UNIT_LENGTH / 2;
            double current_center_y = y + Game.UNIT_LENGTH / 2;
            double center_x = baseObject.x + Game.UNIT_LENGTH / 2;
            double center_y = baseObject.y + Game.UNIT_LENGTH / 2;
            if (Math.abs(current_center_x - center_x) < Game.UNIT_LENGTH &&//横向判断
                    Math.abs(current_center_y - center_y) < Game.UNIT_LENGTH) {
                return baseObject;
            }
        }
        return null;
    }
}