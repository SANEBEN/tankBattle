package com.zhaoyang.tankbattle.util.game;

import javafx.scene.media.AudioClip;

import java.net.URISyntaxException;

/**
 * @author 昭阳
 * @date 2020/3/24 20:15
 */
public class Music {

    public static AudioClip start() throws URISyntaxException {
        AudioClip ac;
        ac = new AudioClip(Music.class.getResource("/music/start.wav").toURI().toString());
        ac.setCycleCount(50);  //设置循环次数
//        ac.play();   //开始播放
        return ac;
    }

    public static AudioClip ready() throws URISyntaxException {
        AudioClip ac;
        ac = new AudioClip(Music.class.getResource("/music/add.wav").toURI().toString());
        return ac;
    }
}
