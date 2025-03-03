package com.zhaoyang.tankbattle.entity;

import com.zhaoyang.tankbattle.entity.animation.Explode;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.List;

/**
 * @author 昭阳
 * @date 2020/3/27 15:30
 * <p>
 * 该类为这个游戏中所有物体的基类，包含基础的属性。
 */
@Log
@Getter
@Setter
public abstract class BaseObject {
    public double x, y;
    public double side_length;
    public Direction direction;
    public boolean breakable = true;

    public List<BaseObject> baseObjectList;

    public Image current_img;

    public GraphicsContext current_gc;

    public BaseObject() {
    }

    public BaseObject(double x, double y, double side_length) {
        this.x = x;
        this.y = y;
        this.side_length = side_length;
    }

    //绘制图形到canvas上
    public void draw() {
        current_gc.drawImage(current_img,
                1, 1, current_img.getWidth(), current_img.getHeight(),
                x, y, side_length, side_length);
    }

    //触发物体爆炸动画的效果
    public void explode() {
        Game.addExplode(new Explode(x, y, side_length));
    }

    //物体碰撞检测的方法
    public BaseObject collisionDetection() {
        double current_center_x = x + side_length / 2;
        double current_center_y = y + side_length / 2;
        for (BaseObject baseObject : baseObjectList) {
            double center_x = baseObject.x + Game.UNIT_LENGTH / 2;
            double center_y = baseObject.y + Game.UNIT_LENGTH / 2;
            if (Math.abs(current_center_x - center_x) < side_length / 2 + baseObject.side_length / 2 &&//横向判断
                    Math.abs(current_center_y - center_y) < side_length / 2 + baseObject.side_length / 2) {
                return baseObject;
            }
        }
        return null;
    }

    //物体被击中的抽象方法，由子类实现。
    public abstract void beHit();
}
