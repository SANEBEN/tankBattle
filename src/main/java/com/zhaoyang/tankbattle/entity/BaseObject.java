package com.zhaoyang.tankbattle.entity;

import com.zhaoyang.tankbattle.util.ThreadFactory;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.util.game.Img;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

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

    public Image current_img;

    //绘制图形到canvas上
    public void draw(GraphicsContext gc) {
        gc.drawImage(current_img,
                1, 1, current_img.getWidth(), current_img.getHeight(),
                x, y, side_length, side_length);
    }

    //触发物体爆炸动画的效果
    public void explode() {
        Image[] blast = Img.blast;
        double object_center_x = this.getX() + this.side_length / 2;
        double object_center_y = this.getY() + this.side_length / 2;
        log.info(object_center_x + "   " + object_center_y);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 8; i++) {
                Game.getGc().drawImage(blast[i],
                        1, 1, blast[i].getWidth(), blast[i].getHeight(),
                        x, y, side_length, side_length);
//                try {
////                    Thread.sleep(50);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                }
            }
            Game.getGc().clearRect(x, y, side_length, side_length);

        });
        ThreadFactory.execute(thread);
    }

    //清除物体在canvas上的图像
    public void clean(GraphicsContext gc) {
        gc.clearRect(x, y, side_length, side_length);
    }

    //物体被击中的抽象方法，由子类实现。
    public abstract void beHit();
}
