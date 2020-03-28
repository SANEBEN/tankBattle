package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.game.Img;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 昭阳
 * @date 2020/3/26 19:43
 */
public class EnemyTank extends Tank {

    @Override
    public Bullet fire() {
        return new Bullet(Img.BULLET_NPC, x, y, getDirection());
    }

    private void findTheWay() {
        List<Point> open = new ArrayList<>();
        List<Point> close = new ArrayList<>();
    }
}

class Point {
    double x, y;
}
