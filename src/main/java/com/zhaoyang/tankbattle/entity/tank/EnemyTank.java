package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.util.game.Img;
import lombok.extern.java.Log;

import java.util.ArrayList;

/**
 * @author 昭阳
 * @date 2020/3/26 19:43
 */
@Log
public class EnemyTank extends Tank {

    public EnemyTank() {
        this.setUp(Img.TANK_ENEMY_GREEN_TOP);
        this.setDown(Img.TANK_ENEMY_GREEN_BUTTON);
        this.setLeft(Img.TANK_ENEMY_GREEN_LEFT);
        this.setRight(Img.TANK_ENEMY_GREEN_RIGHT);
        this.setDirection(Direction.DOWN);
        baseObjectList = new ArrayList<>();
        ArrayList<EnemyTank> enemyTanks = new ArrayList<>(Game.enemyTanks);
        enemyTanks.remove(this);
        baseObjectList.addAll(enemyTanks);
        baseObjectList.add(Game.playerTank);
        baseObjectList.addAll(Game.walls);
    }

    @Override
    public Bullet fire() {
        return new Bullet(Img.BULLET_NPC, this, x, y, getDirection());
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

}