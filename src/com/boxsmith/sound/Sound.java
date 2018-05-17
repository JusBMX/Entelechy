package com.boxsmith.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    private Clip clip;

    public Sound (final String url){
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(url));
            clip.open(inputStream);
        } catch (Exception e) {
            clip = null;
        }
    }

    public synchronized void playSound() {
        clip.start();
    }

    public void setLoop(int count){
        clip.loop(count);
    }

    public synchronized void stop(){
        clip.stop();
    }

    public void setVolumne (){

    }
}
