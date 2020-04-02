package com.zhaoyang.tankbattle.entity.animation;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.util.game.Game;
import com.zhaoyang.tankbattle.util.game.Img;
import javafx.scene.image.Image;

/**
 * @author 昭阳
 * @date 2020/4/2 9:17
 */
public class Explode extends BaseObject {

    private Image[] blast = Img.blast;
    private int mark = 0;

    public Explode(double x, double y, double side_length) {
        super(x, y, side_length);
    }

    public boolean next() {
        Game.animationCanvas.getGraphicsContext2D().drawImage(blast[mark],
                1, 1, blast[mark].getWidth(), blast[mark].getHeight(),
                x, y, side_length, side_length);
        return ++mark != 8;
    }

    public void clean(){
        Game.animationCanvas.getGraphicsContext2D().clearRect(x, y, side_length, side_length);
    }


    @Override
    public void beHit() {

    }
}
