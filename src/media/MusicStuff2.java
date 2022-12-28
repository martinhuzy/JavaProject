package media;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicStuff2 {
    public void playMusic(String musicFilepath) {
        try {
            File file = new File(musicFilepath);
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
