package com.zhaoyang.tankbattle.util.game;

import javafx.scene.image.Image;

/**
 * @author 昭阳
 * @date 2020/3/24 16:03
 */
public class Img {

    /*
    子弹素材
     */
    public static final Image BULLET_BOMB = new Image("/img/bullet/bomb.gif");
    public static final Image BULLET_PLAYER = new Image("/img/bullet/tankmissile.gif");
    public static final Image BULLET_NPC = new Image("/img/bullet/enemymissile.gif");


    /*
    障碍物贴图素材
     */
    public static final Image WALL = new Image("/img/wall/walls.gif");
    public static final Image WALL_SMALL = new Image("/img/wall/wall.gif");
    public static final Image STEEL = new Image("/img/wall/steels.gif");
    public static final Image STEEL_SMALL = new Image("/img/wall/steel.gif");
    public static final Image GRASS = new Image("/img/wall/grass.png");
    public static final Image WATER = new Image("/img/wall/water.gif");

    /*
    坦克贴图素材
     */
    public static final Image TANK_ENEMY_YELLOW_BOTTON = new Image("/img/tank/enemy3D.gif");
    public static final Image TANK_ENEMY_YELLOW_LEFT = new Image("/img/tank/enemy3L.gif");
    public static final Image TANK_ENEMY_YELLOW_RIGHT = new Image("/img/tank/enemy3R.gif");
    public static final Image TANK_ENEMY_YELLOW_TOP = new Image("/img/tank/enemy3U.gif");

    public static final Image TANK_ENEMY_WHITE_BOTTON = new Image("/img/tank/enemy1D.gif");
    public static final Image TANK_ENEMY_WHITE_LEFT = new Image("/img/tank/enemy1L.gif");
    public static final Image TANK_ENEMY_WHITE_RIGHT = new Image("/img/tank/enemy1R.gif");
    public static final Image TANK_ENEMY_WHITE_TOP = new Image("/img/tank/enemy1U.gif");
    public static final Image Tank_ENEMY_WHITE_SMALL = new Image("/img/tank/mintank.gif");

    public static final Image TANK_ENEMY_GREEN_BUTTON = new Image("/img/tank/enemy2D.gif");
    public static final Image TANK_ENEMY_GREEN_LEFT = new Image("/img/tank/enemy2L.gif");
    public static final Image TANK_ENEMY_GREEN_RIGHT = new Image("/img/tank/enemy2R.gif");
    public static final Image TANK_ENEMY_GREEN_TOP = new Image("/img/tank/enemy2U.gif");

    public static final Image TANK_PLAYER_GREEN_BUTTON = new Image("/img/tank/p1tankD.gif");
    public static final Image TANK_PLAYER_GREEN_LEFT = new Image("/img/tank/p1tankL.gif");
    public static final Image TANK_PLAYER_GREEN_RIGHT = new Image("/img/tank/p1tankR.gif");
    public static final Image TANK_PLAYER_GREEN_TOP = new Image("/img/tank/p1tankU.gif");

    public static final Image TANK_PLAYER_YELLOW_BUTTON = new Image("/img/tank/p2tankD.gif");
    public static final Image TANK_PLAYER_YELLOW_LEFT = new Image("/img/tank/p2tankL.gif");
    public static final Image TANK_PLAYER_YELLOW_RIGHT = new Image("/img/tank/p2tankR.gif");
    public static final Image TANK_PLAYER_YELLOW_TOP = new Image("/img/tank/p2tankU.gif");

    public static Image[] blast = new Image[8];

    static {
        blast[0] = new Image("/img/explode/blast1.gif");
        blast[1] = new Image("/img/explode/blast2.gif");
        blast[2] = new Image("/img/explode/blast3.gif");
        blast[3] = new Image("/img/explode/blast4.gif");
        blast[4] = new Image("/img/explode/blast5.gif");
        blast[5] = new Image("/img/explode/blast6.gif");
        blast[6] = new Image("/img/explode/blast7.gif");
        blast[7] = new Image("/img/explode/blast8.gif");
    }

}
