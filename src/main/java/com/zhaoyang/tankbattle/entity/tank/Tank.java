package com.zhaoyang.tankbattle.entity.tank;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

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
        current_gc = Game.tankCanvas.getGraphicsContext2D();
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

    public boolean move() {
        double backup_x, backup_y;
        backup_x = x;
        backup_y = y;
//        current_gc.clearRect(x, y, side_length, side_length);
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
        if (collisionDetection() != null) {
            x = backup_x;
            y = backup_y;
            return false;
        }
        return true;
    }

    @Override
    public void beHit() {

    }

    public abstract Bullet fire();
}
