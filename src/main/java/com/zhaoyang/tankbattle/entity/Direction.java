package com.zhaoyang.tankbattle.entity;

import java.util.Random;

/**
 * @author 昭阳
 * @date 2020/3/27 10:36
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    private static int random = (int) (Math.random() * 10);// 生成种子
    private static Random rand = new Random(random);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
