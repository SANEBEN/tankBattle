package com.zhaoyang.tankbattle.entity.bullet;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.util.ThreadFactory;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.image.Image;
import lombok.extern.java.Log;

import java.util.ArrayList;

/**
 * @author 昭阳
 * @date 2020/3/27 10:15
 */
@Log
public class Bullet extends BaseObject {
    private BaseObject source;
    private int speed = 4;

    public Bullet(Image img, BaseObject source, double x, double y, Direction direction) {
        baseObjectList = new ArrayList<>();
        baseObjectList.add(Game.playerTank);
        baseObjectList.addAll(Game.enemyTanks);
        baseObjectList.addAll(Game.walls);
        side_length = Game.UNIT_LENGTH / 4;
        this.source = source;
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
            if ((object = collisionDetection()) != null && object != source) {
                x = backup_x;
                y = backup_y;
                Game.bullets.remove(this);
                log.info(object.x + "   " + object.y);
                log.info(object.getClass().getName());
                object.beHit();
            }
        });
        ThreadFactory.execute(thread);
    }

    @Override
    public void beHit() {

    }
}
