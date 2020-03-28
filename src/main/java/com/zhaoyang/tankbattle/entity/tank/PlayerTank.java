package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.game.Img;

/**
 * @author 昭阳
 * @date 2020/3/26 19:43
 */
public class PlayerTank extends Tank {

    @Override
    public Bullet fire() {
        return new Bullet(Img.BULLET_PLAYER, x, y, getDirection());
    }
}
