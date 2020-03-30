package com.zhaoyang.tankbattle.util.game;

import com.zhaoyang.tankbattle.entity.*;
import com.zhaoyang.tankbattle.entity.bullet.Bullet;
import com.zhaoyang.tankbattle.entity.bullet.BulletScheduledService;
import com.zhaoyang.tankbattle.entity.tank.EnemyTank;
import com.zhaoyang.tankbattle.entity.tank.EnemyTankScheduleService;
import com.zhaoyang.tankbattle.entity.tank.PlayerTank;
import com.zhaoyang.tankbattle.entity.tank.TankScheduleService;
import com.zhaoyang.tankbattle.entity.wall.Base;
import com.zhaoyang.tankbattle.entity.wall.Wall;
import com.zhaoyang.tankbattle.window.canvas.BulletCanvas;
import com.zhaoyang.tankbattle.window.canvas.TankCanvas;
import com.zhaoyang.tankbattle.window.canvas.WallCanvas;
import javafx.concurrent.Worker;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 昭阳
 * @date 2020/3/26 17:24
 */

@Log
@Getter
@Setter
public class Game {

//    private static GraphicsContext gc;
//
//    public static GraphicsContext tank_canvas_gc;

    public static GraphicsContext wall_canvas_gc;

    public static BulletCanvas bulletCanvas;

    public static TankCanvas tankCanvas;

    public static WallCanvas wallCanvas;

    private static Base base;//玩家基地，被敌人击中则游戏结束

    public static boolean isRunning = true;

    public static double width;
    public static double height;

    public static final double UNIT_LENGTH = 32;

    public static int[][] map = {
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 4, 1, 3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
    };

    //地图中的障碍物
    public static List<Wall> walls = new ArrayList<>();

    //玩家坦克
    public static PlayerTank playerTank;

    //电脑坦克
    public static List<EnemyTank> enemyTanks = new ArrayList<>();

    //当前子弹
    public static List<Bullet> bullets = new ArrayList<>();

    private static BulletScheduledService bulletScheduledService = new BulletScheduledService();

    private static TankScheduleService tankScheduleService = new TankScheduleService();

    private static EnemyTankScheduleService enemyTankScheduleService = new EnemyTankScheduleService();

    static {
        bulletScheduledService.setDelay(Duration.seconds(0.02));
        bulletScheduledService.setPeriod(Duration.seconds(0.02));
        bulletScheduledService.start();

        tankScheduleService.setDelay(Duration.seconds(0.02));
        tankScheduleService.setPeriod(Duration.seconds(0.02));
        tankScheduleService.start();

        enemyTankScheduleService.setDelay(Duration.seconds(0.1));
        enemyTankScheduleService.setPeriod(Duration.seconds(0.02));
        enemyTankScheduleService.start();

        bulletScheduledService.valueProperty().addListener((observable, oldValue, newValue) -> {
            log.info(oldValue);
            log.info(newValue);
        });

        bulletScheduledService.lastValueProperty().addListener((observable, oldValue, newValue) -> {
            log.info("2333:" + oldValue);
            log.info("2333:" + newValue);
        });
    }

    public static void addBullet(Bullet bullet) {
        if (bullets.size() < 10) {
            bullets.add(bullet);
            if (bulletScheduledService.getState() == Worker.State.CANCELLED) {
                bulletScheduledService.restart();
            }
        }
    }

    public static void checkBullet() {
        if (bullets.size() == 0) {
            bulletScheduledService.cancel();
        }
    }

    public static void InitMap() {
        int height = map.length;
        int width = map[0].length;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                /*
                   参数解释：sx:源矩形的x坐标位置,sy:源矩形的y坐标位置,sw:源矩形的宽度,sh:源矩形的高度
                            dx:目标矩形的x坐标位置,dx:目标矩形的y坐标位置,dw:目标矩形的宽度,dh:目标矩形的高度
                */
                int mark = map[y][x];
                if (mark == 2) {
                    Wall wall = new Wall(x * UNIT_LENGTH, y * UNIT_LENGTH, Img.STEEL, false);
                    wall.draw();
                    walls.add(wall);
                } else if (mark == 1) {
                    Wall wall = new Wall(x * UNIT_LENGTH, y * UNIT_LENGTH, Img.WALL, true);
                    wall.draw();
                    walls.add(wall);
                } else if (mark == 3) {
                    //要实现草丛的效果就不把草的绘图加到障碍物的列表中了，这样就不会在碰撞检测里面被检测
                    Wall wall = new Wall(x * UNIT_LENGTH, y * UNIT_LENGTH, Img.GRASS, true);
                    wall.draw();
                } else if (mark == 4) {
                    Base base = new Base(x * UNIT_LENGTH, y * UNIT_LENGTH, Img.BASE, true);
                    base.draw();
                    Game.base = base;
                }
            }
        }
    }

    public static void InitNpc() {
        EnemyTank enemyTank = new EnemyTank();
        enemyTank.setBlood(10);
        enemyTank.setSpeed(2);
        enemyTank.setLocation(320, 320);
        enemyTank.setUp(Img.TANK_ENEMY_GREEN_TOP);
        enemyTank.setDown(Img.TANK_ENEMY_GREEN_BUTTON);
        enemyTank.setLeft(Img.TANK_ENEMY_GREEN_LEFT);
        enemyTank.setRight(Img.TANK_ENEMY_GREEN_RIGHT);
        enemyTank.setDirection(Direction.DOWN);
        enemyTanks.add(enemyTank);
    }

    public static void InitPlayer() {
        PlayerTank playerTank = new PlayerTank();
        playerTank.setBlood(10);
        playerTank.setSpeed(2);
        playerTank.setLocation(32, 64);
        playerTank.setUp(Img.TANK_PLAYER_YELLOW_TOP);
        playerTank.setDown(Img.TANK_PLAYER_YELLOW_BUTTON);
        playerTank.setLeft(Img.TANK_PLAYER_YELLOW_LEFT);
        playerTank.setRight(Img.TANK_PLAYER_YELLOW_RIGHT);
        playerTank.setDirection(Direction.LEFT);
        Game.playerTank = playerTank;
//        playerTank.draw();
    }
}
