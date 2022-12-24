package View;

import javax.swing.*;
import java.awt.*;

public class PlatformFrame extends JFrame {
    private int WIDTH;
    private int HEIGHT;
    private static JLabel statusLabel;

    public PlatformFrame(int WIDTH,int HEIGHT) {
        setTitle("DarkChess");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addLabelButton();
        addStartGameButton();
        addRegisterButton();
        addRankButton();
        addSettingsButton();
        addExitButton();
    }
    private void addLabelButton() {
        statusLabel = new JLabel("中国象棋暗棋");
        statusLabel.setLocation(WIDTH / 10, HEIGHT / 10);
        statusLabel.setSize(600, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 50));
        add(statusLabel);
    }

    private void addStartGameButton() {
        JButton button = new JButton("开始游戏");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                StartGameFrame mainFrame = new StartGameFrame(720, 720);
                mainFrame.setVisible(true);
                setVisible(false);
            });
        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 120);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addRegisterButton() {
        JButton button = new JButton("修改本地账户");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "敬请期待！"));
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 200);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addRankButton() {
        JButton button = new JButton("本地玩家排行榜");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "敬请期待！"));
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 280);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addSettingsButton() {
        JButton button = new JButton("设置");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "敬请期待！"));
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 360);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addExitButton() {
        JButton button = new JButton("退出游戏");
        button.addActionListener((e) -> {
            Object[] options ={"确认","取消"};
            int m = JOptionPane.showOptionDialog(null,"确认要退出游戏吗？","提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 440);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
}
