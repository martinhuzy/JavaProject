import media.BGM;
import view.PlatformFrame;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        String filepath = "./resource/项斯华 - 高山流水 (古筝独奏).wav";
        BGM backGroundMusic = new BGM();
        backGroundMusic.playMusic(filepath);
        SwingUtilities.invokeLater(() -> {
            PlatformFrame mainFrame = new PlatformFrame(720, 720);
            mainFrame.setVisible(true);
        });
    }
}