import View.PlatformFrame;

import javax.swing.*;

public class GameLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlatformFrame mainFrame = new PlatformFrame(720, 720);
            mainFrame.setVisible(true);
        });
    }
}
