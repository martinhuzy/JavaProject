package view;

import javax.swing.*;
import java.awt.*;

public class PvPFrame extends JFrame {
    private int WIDTH;
    private int HEIGHT;
    private static JLabel statusLabel;
    public PvPFrame(int WIDTH, int HEIGHT) {
        setTitle("DarkChess");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addLocalGameButton();
        addNetworkButton();
        addBackButton();
        addBackGround();
    }

    private void addBackGround() {
        ImageIcon scaledImage=new ImageIcon(".\\resource\\2.png");
        JLabel picture = new JLabel(scaledImage);
        picture.setSize(720,660);
        JPanel pan = (JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(null);
        add(picture,JLayeredPane.DEFAULT_LAYER);
    }
    private void addLocalGameButton() {
        JButton button = new JButton("本地对战");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                //GameFrameHandle.gameFrame = new GameFrame(720, 720);
                GameFrameHandle.gameFrame.setVisible(true);
                setVisible(false);
            });
        });
        button.setLocation(WIDTH / 2 - 90, HEIGHT / 2 - 130);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\10.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addNetworkButton() {
        JButton button = new JButton("局域网联机对战");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "敬请期待！"));
        button.setLocation(WIDTH / 2 - 90, HEIGHT / 2 - 30);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\11.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addBackButton() {
        JButton button = new JButton("返回上一层");
        button.addActionListener((e) -> {
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        });
        button.setLocation(WIDTH / 2 - 90, HEIGHT / 2 + 70);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\9.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
}


