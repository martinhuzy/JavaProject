package media;

import javax.sound.sampled.*;
import java.io.File;

public class MusicStuff1 {
    public void playMusic(String musicFilepath) {
        try {
            File file = new File(musicFilepath);
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                double percent = -80;   // 这里调节你想要控制的音量的大小
                /* -80->6.206**/
                float dB = (float) (percent);
                volume.setValue(dB);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
