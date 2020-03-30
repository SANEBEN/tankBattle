package com.zhaoyang.tankbattle.entity.bullet;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.wall.Base;
import com.zhaoyang.tankbattle.util.ThreadFactory;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 昭阳
 * @date 2020/3/27 10:15
 */
public class Bullet extends BaseObject {
    private int speed = 4;

    public Bullet(Image img, double x, double y, Direction direction) {
        side_length = Game.UNIT_LENGTH / 4;
        this.current_img = img;
        this.current_gc = Game.bulletCanvas.getGraphicsContext2D();
        //因为x,y的数值为坦克左上角的坐标，为了试子弹从坦克的炮管的位置打出，需要调整子弹绘制的坐标
        switch (direction) {
            case UP:
                this.x = x + Game.UNIT_LENGTH / 2 - side_length / 2;
                this.y = y - side_length;
                break;
            case DOWN:
                this.x = x + Game.UNIT_LENGTH / 2 - side_length / 2;
                this.y = y + Game.UNIT_LENGTH + side_length;
                break;
            case LEFT:
                this.x = x - side_length;
                this.y = y + Game.UNIT_LENGTH / 2 - side_length / 2;
                break;
            case RIGHT:
                this.x = x + Game.UNIT_LENGTH + side_length;
                this.y = y + Game.UNIT_LENGTH / 2 - side_length / 2;
                break;
        }

        this.direction = direction;
    }

    public void move() {
        double backup_x, backup_y;
        backup_x = x;
        backup_y = y;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case LEFT:
                x -= speed;
        }
        Thread thread = new Thread(() -> {
            BaseObject object;
            if ((object = collisionDetection()) != null) {
                x = backup_x;
                y = backup_y;
                Game.bullets.remove(this);
                object.beHit();
            }
        });
        ThreadFactory.execute(thread);
    }

    @Override
    public void beHit() {

    }

    @Override
    public BaseObject collisionDetection() {
        List<BaseObject> baseObjectList = new ArrayList<>();
        baseObjectList.add(Game.playerTank);
        baseObjectList.addAll(Game.enemyTanks);
        baseObjectList.addAll(Game.walls);
        for (BaseObject baseObject : baseObjectList) {
            double current_center_x = x + Game.UNIT_LENGTH / 2;
            double current_center_y = y + Game.UNIT_LENGTH / 2;
            double center_x = baseObject.x + Game.UNIT_LENGTH / 2;
            double center_y = baseObject.y + Game.UNIT_LENGTH / 2;
            if (Math.abs(current_center_x - center_x) < side_length / 2 + Game.UNIT_LENGTH / 2 &&//横向判断
                    Math.abs(current_center_y - center_y) < side_length / 2 + Game.UNIT_LENGTH / 2) {
                return baseObject;
            }
        }
        return null;
    }
}
