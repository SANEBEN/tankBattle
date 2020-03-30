package com.zhaoyang.tankbattle.entity.wall;

import javafx.scene.image.Image;

/**
 * @author 昭阳
 * @date 2020/3/29 21:41
 */
public class Base extends Wall {

    public Base(double x, double y, Image img, boolean breakable) {
        super(x, y, img, breakable);
    }

    @Override
    public void beHit() {

    }
}
