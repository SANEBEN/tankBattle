package com.zhaoyang.tankbattle.entity.bullet;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.entity.Direction;
import com.zhaoyang.tankbattle.entity.wall.Wall;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
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

    public boolean collisionDetection(BaseObject object) {
        double current_center_x = x + side_length / 2;
        double current_center_y = y + side_length / 2;
        double center_x = object.x + Game.UNIT_LENGTH / 2;
        double center_y = object.y + Game.UNIT_LENGTH / 2;
        //纵向判断
        return Math.abs(current_center_x - center_x) < side_length / 2 + Game.UNIT_LENGTH / 2 &&//横向判断
                Math.abs(current_center_y - center_y) < side_length / 2 + Game.UNIT_LENGTH / 2;
    }

    public boolean move() {
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
        List<BaseObject> baseObjectList = new ArrayList<>();
        baseObjectList.addAll(Game.playerTanks);
        baseObjectList.addAll(Game.enemyTanks);
        baseObjectList.addAll(Game.walls);
        for (BaseObject object : baseObjectList) {
            if (collisionDetection(object)) {
                x = backup_x;
                y = backup_y;
                this.clean(Game.getGc());
                Game.bullets.remove(this);
                Platform.runLater(object::beHit);
                return false;
            }
        }
        return true;
    }

    @Override
    public void beHit() {

    }
}
