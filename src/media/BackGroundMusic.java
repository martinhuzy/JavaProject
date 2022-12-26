package media;

import javax.sound.sampled.*;
import java.io.File;

public class BackGroundMusic {
    public void playMusic(String musicFilepath) {
        try {
            File file = new File(musicFilepath);
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
