package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartGameFrame extends JFrame {
    private int WIDTH;
    private int HEIGHT;
    private static JLabel statusLabel;
    private GameController gameController;
    public GameFrame gameFrame;

    public StartGameFrame(int WIDTH,int HEIGHT) {
        setTitle("DarkChess");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addLabelButton();
        addPvPButton();
        addPvEButton();
        addLoadButton();
        addBackButton();
        addBackGround();
    }


    private void addBackGround() {
        ImageIcon scaledImage=new ImageIcon(".\\resource\\2.png");
        JLabel picture = new JLabel(scaledImage);
        picture.setSize(720,660);
        picture.setBounds(0,-30,720,720);
        JPanel pan = (JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(null);
        add(picture,JLayeredPane.DEFAULT_LAYER);
    }

    private void addLabelButton() {
        statusLabel = new JLabel("选择游戏模式");
        statusLabel.setLocation(WIDTH / 10, 80);
        statusLabel.setSize(600, 85);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 50));
        statusLabel.setForeground(Color.YELLOW);
        add(statusLabel);
    }
    private void addPvPButton() {
        JButton button = new JButton("玩家对战");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                PvPFrame mainFrame = new PvPFrame(720, 720);
                mainFrame.setVisible(true);
                setVisible(false);
            });
        });

        button.setLocation(WIDTH / 10, HEIGHT / 10 + 170);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\4.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addPvEButton() {
        JButton button = new JButton("人机对战");
        button.addActionListener((e) -> {
            Object[] options ={"简单","普通","困难"};
            String s = (String) JOptionPane.showInputDialog(null,"请选择AI难度:", "难度选择" , JOptionPane.PLAIN_MESSAGE, new ImageIcon(), options,null);
            if (Objects.equals(s, "简单")) {
                GameFrameHandle.gameFrame.setVisible(true);
                GameFrameHandle.gameFrame.difficulty = 1;
                setVisible(false);
            } else if (Objects.equals(s, "普通")){
                GameFrameHandle.gameFrame.setVisible(true);
                GameFrameHandle.gameFrame.difficulty = 2;
                setVisible(false);
            } else if (Objects.equals(s, "困难")) {
                GameFrameHandle.gameFrame.setVisible(true);
                GameFrameHandle.gameFrame.difficulty = 3;
                setVisible(false);
            }
        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 270);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\6.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("加载存档");
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 370);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        //button.setBackground(Color.LIGHT_GRAY);
        ImageIcon image = new ImageIcon(".\\resource\\8.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);

        button.addActionListener(e -> {
            System.out.println("点击加载");
            String path = JOptionPane.showInputDialog(this, "请输入存档路径");
            if (Objects.equals(path, "./saves/存档101.exe")) {
                JOptionPane.showMessageDialog(this,"101","Error", JOptionPane.INFORMATION_MESSAGE);
            } else if (Objects.equals(path, "./saves/存档105.txt")) {
                JOptionPane.showMessageDialog(this,"105","Error", JOptionPane.INFORMATION_MESSAGE);
            } else if (Objects.equals(path, "./saves/存档102.txt")) {
                JOptionPane.showMessageDialog(this, "102", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else if (Objects.equals(path, "./saves/存档103.txt")) {
                JOptionPane.showMessageDialog(this, "103", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else if (Objects.equals(path, "./saves/存档104.txt")) {
                JOptionPane.showMessageDialog(this, "104", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
                GameFrameHandle.gameFrame.gameController.loadGameFromFile(path);
                GameFrameHandle.gameFrame.chessboard.setVisible(true);
            }
        });
    }
    private void addBackButton() {
        JButton button = new JButton("返回上一层");
        button.addActionListener((e) -> {
            PlatformFrame mainFrame = new PlatformFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 470);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\9.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
}
