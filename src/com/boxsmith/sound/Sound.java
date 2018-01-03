package com.boxsmith.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    public static synchronized Clip playSound(final String url) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(url));
            clip.open(inputStream);
            clip.start();
            return clip;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void setLoop(){

    }

    public void stopSound(){

    }
}
