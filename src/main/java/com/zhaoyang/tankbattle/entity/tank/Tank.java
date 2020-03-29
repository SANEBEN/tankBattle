package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 昭阳
 * @date 2020/3/26 19:43
 */
@Log
@Getter
@Setter
public abstract class Tank extends BaseObject {

    //血量
    private int blood;
    //移动速度
    private int speed = 8;

    public Tank() {
        current_img = down;
        side_length = Game.UNIT_LENGTH;
        direction = Direction.DOWN;
        current_gc = Game.tank_canvas_gc;
    }

    private Image up;
    private Image down;
    private Image left;
    private Image right;

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        switch (direction) {
            case UP:
                current_img = up;
                break;
            case DOWN:
                current_img = down;
                break;
            case LEFT:
                current_img = left;
                break;
            case RIGHT:
                current_img = right;
                break;
        }
    }

    public boolean collisionDetection(BaseObject object) {
        double current_center_x = x + Game.UNIT_LENGTH / 2;
        double current_center_y = y + Game.UNIT_LENGTH / 2;
        double center_x = object.x + Game.UNIT_LENGTH / 2;
        double center_y = object.y + Game.UNIT_LENGTH / 2;
        //纵向判断
        return Math.abs(current_center_x - center_x) < Game.UNIT_LENGTH &&//横向判断
                Math.abs(current_center_y - center_y) < Game.UNIT_LENGTH;
    }

    public void move() {
        double backup_x, backup_y;
        backup_x = x;
        backup_y = y;
        current_gc.clearRect(x, y, side_length, side_length);
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }
        List<BaseObject> baseObjectList = new ArrayList<>();
//        baseObjectList.addAll(Game.playerTanks);
        baseObjectList.addAll(Game.enemyTanks);
        baseObjectList.addAll(Game.walls);
        for (BaseObject object : baseObjectList) {
            if (collisionDetection(object)) {
                x = backup_x;
                y = backup_y;
                draw();
                return;
            }
        }
        draw();
    }

    @Override
    public void beHit() {

    }

    public abstract Bullet fire();
}
