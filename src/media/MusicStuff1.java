package media;

import javax.sound.sampled.*;
import java.io.File;

import static view.PlatformFrame.flag;

public class MusicStuff1 {
    static Clip clip;

    public void playMusic(String musicFilepath) {
        try {
            File file = new File(musicFilepath);
            if (file.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
             // value可以用来设置音量，从0-2.0
                double value = 2;
                float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
                volume.setValue(dB);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void endMusic() {
        clip.stop();



//    public static void playMusic() {// 背景音乐播放
//        try {
//            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("./resource/项斯华 - 高山流水 (古筝独奏).wav"));
//            AudioFormat aif = ais.getFormat();
//            final SourceDataLine sdl;
//            DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
//            sdl = (SourceDataLine) AudioSystem.getLine(info);
//            sdl.open(aif);
//            sdl.start();
//            FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
//            // value可以用来设置音量，从0-2.0
//            double value = 2;
//            float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
//            fc.setValue(dB);
//            int nByte = 0;
//            int writeByte = 0;
//            final int SIZE = 1024 * 64;
//            byte[] buffer = new byte[SIZE];
//            while (nByte != -1) {// 判断 播放/暂停 状态
//                 if(flag) {
//                 nByte = ais.read(buffer, 0, SIZE);
//                 sdl.write(buffer, 0, nByte);
//                 }else {
//                     nByte = ais.read(buffer, 0, 0);
//                 }
//            }
//            sdl.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    }
}
