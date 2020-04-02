package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.util.game.Img;

import java.util.ArrayList;

/**
 * @author 昭阳
 * @date 2020/3/26 19:43
 */
public class PlayerTank extends Tank {

    public PlayerTank() {
        baseObjectList = new ArrayList<>();
        baseObjectList.addAll(Game.enemyTanks);
        baseObjectList.addAll(Game.walls);
    }

    @Override
    public Bullet fire() {
        return new Bullet(Img.BULLET_PLAYER, this, x, y, getDirection());
    }

    @Override
    public void beHit() {
    }
}
