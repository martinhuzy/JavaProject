import media.MusicStuff1;
import view.PlatformFrame;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        String filepath = "./resource/项斯华 - 高山流水 (古筝独奏).wav";
        MusicStuff1 backGroundMusic = new MusicStuff1();
        backGroundMusic.playMusic(filepath);
        SwingUtilities.invokeLater(() -> {
            PlatformFrame mainFrame = new PlatformFrame(720, 720);
            mainFrame.setVisible(true);
        });
    }
}