package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    private final int WIDTH;
    private boolean isCheating = false;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;

    public GameFrame(int width, int height) {
        setTitle("2022 CS109 Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.CHESSBOARD_SIZE = HEIGHT * 4 / 5;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addLabel();
        addChessboard();
        addBackButton();
        addRestartButton();
        addCheatingModeButton();
        addSaveGameButton();
        addRedPointLabel();
        addBlackPointLabel();
        addRegretButton();
    }

    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGHT / 10, HEIGHT / 10);
        add(chessboard);
    }

    private void addLabel() {
        statusLabel = new JLabel("BLACK's TURN");
        statusLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    private void addBackButton() {
        JButton button = new JButton("返回菜单");
        button.addActionListener((e) -> {
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT * 7/ 10);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addCheatingModeButton() {
        JButton button = new JButton();
        button.setText("作弊模式：关");
        button.addActionListener((e) -> {
            if (!isCheating) {
                isCheating = true;
                cheatingMode();
                button.setText("作弊模式：开");
                button.repaint();
            } else {
                isCheating = false;
                button.setText("作弊模式：关");
                button.repaint();
            }
        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT * 3/ 10);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    //todo: finish it
    private void addRestartButton() {
        JButton button = new JButton("重新开始");
        button.addActionListener((e) -> {

        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT /2);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    //todo: finish it
    private void cheatingMode() {

    }

    //todo: finish it
    private void addSaveGameButton() {
        JButton button = new JButton("存储游戏");
        button.addActionListener((e) -> {

        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT * 3 / 5);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    //todo:finish it
    private void addRedPointLabel() {
        JLabel redPointLabel = new JLabel();
        redPointLabel.setText(String.format("红方得分: %d",0));
        redPointLabel.setLocation(WIDTH * 3 / 5, HEIGHT / 5);
        redPointLabel.setSize(200, 60);
        redPointLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(redPointLabel);
    }

    //todo:finish it
    private void addBlackPointLabel() {
        JLabel blackPointLabel = new JLabel();
        blackPointLabel.setText(String.format("黑方得分: %d",0));
        blackPointLabel.setLocation(WIDTH * 3 / 5, HEIGHT * 3 / 20);
        blackPointLabel.setSize(200, 60);
        blackPointLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(blackPointLabel);
    }

    //todo:finish it
    private void addRegretButton() {
        JButton button = new JButton("悔棋");
        button.addActionListener((e) -> {

        });
        button.setLocation(WIDTH * 3 / 5, HEIGHT * 2 / 5);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
}