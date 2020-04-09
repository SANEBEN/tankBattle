package com.zhaoyang.tankbattle.util;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 昭阳
 * @date 2020/3/25 9:22
 * 窗体管理类   暂时没用上
 */
public class WindowsManage {

    private static Map<String, Stage> activeStage = new HashMap<>();

    public static void addStage(String name, Stage stage) {
        activeStage.put(name, stage);
    }

    public static void removeStage(String name) {
        activeStage.remove(name);
    }

    public static Stage getByName(String name) {
        return activeStage.get(name);
    }
}
