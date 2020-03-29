package com.zhaoyang.tankbattle.entity.wall;

import com.zhaoyang.tankbattle.entity.BaseObject;
import com.zhaoyang.tankbattle.util.game.Game;
import javafx.scene.image.Image;

/**
 * @author 昭阳
 * @date 2020/3/27 19:53
 */
public class Wall extends BaseObject {

    public Wall(double x, double y, Image img, boolean breakable) {
        side_length = Game.UNIT_LENGTH;
        this.x = x;
        this.y = y;
        this.current_img = img;
        this.breakable = breakable;
        this.current_gc = Game.wall_canvas_gc;
    }

    @Override
    public void beHit() {
        if (isBreakable()) {
            current_gc.clearRect(x, y, side_length, side_length);
            Game.walls.remove(this);
        }
    }
}
