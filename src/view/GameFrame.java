package view;

import chessComponent.SquareComponent;
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
        addBackGround();
    }
    private void addBackGround() {
        ImageIcon scaledImage=new ImageIcon(".\\resource\\22.png");
        scaledImage.setImage(scaledImage.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_AREA_AVERAGING));
        JLabel picture = new JLabel(scaledImage);
        picture.setSize(720,660);
        JPanel pan = (JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(null);
        add(picture,JLayeredPane.DEFAULT_LAYER);
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.ORANGE);
        g.fillOval(25, 55, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,55,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("将", 30,95);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 140, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,140,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("士", 30,180);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 225, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,225,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("相", 30,265);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 310, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,310,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("炮", 30,350);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 395, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,395,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("车", 30,435);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 480, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,480,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("马", 30,520);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 565, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25,565,50,50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("兵", 30,605);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 55, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,55,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("将", 435,95);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 140, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,140,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("士", 435,180);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 225, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,225,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("相", 435,265);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 310, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,310,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("炮", 435,350);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 395, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,395,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("车", 435,435);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 480, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,480,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("马", 435,520);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 565, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430,565,50,50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("兵", 435,605);

        g.setColor(Color.ORANGE);
        g.fillOval(68, 85, 20, 20);
        g.fillOval(68, 170, 20, 20);
        g.fillOval(68, 255, 20, 20);
        g.fillOval(68, 340, 20, 20);
        g.fillOval(68, 425, 20, 20);
        g.fillOval(68, 510, 20, 20);
        g.fillOval(68, 595, 20, 20);
        g.fillOval(473, 85, 20, 20);
        g.fillOval(473, 170, 20, 20);
        g.fillOval(473, 255, 20, 20);
        g.fillOval(473, 340, 20, 20);
        g.fillOval(473, 425, 20, 20);
        g.fillOval(473, 510, 20, 20);
        g.fillOval(473, 595, 20, 20);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(68,85,20,20);
        g.drawOval(68, 170, 20, 20);
        g.drawOval(68, 255, 20, 20);
        g.drawOval(68, 340, 20, 20);
        g.drawOval(68, 425, 20, 20);
        g.drawOval(68, 510, 20, 20);
        g.drawOval(68, 595, 20, 20);
        g.drawOval(473,85,20,20);
        g.drawOval(473, 170, 20, 20);
        g.drawOval(473, 255, 20, 20);
        g.drawOval(473, 340, 20, 20);
        g.drawOval(473, 425, 20, 20);
        g.drawOval(473, 510, 20, 20);
        g.drawOval(473, 595, 20, 20);
    }


    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(105, 35);
        add(chessboard);
    }

    private void addLabel() {
        statusLabel = new JLabel("决定先手");
        statusLabel.setLocation(500, HEIGHT / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 40));
        statusLabel.setForeground(Color.WHITE);
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
        button.setLocation(550, 504);
        button.setSize(100, 40);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\21.png");
        Image temp1 = image.getImage().getScaledInstance(260,200,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
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
        button.setLocation(550, 216);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    //todo: finish it
    private void addRestartButton() {
        JButton button = new JButton("重新开始");
        button.addActionListener((e) -> {

        });
        button.setLocation(550, HEIGHT / 2);
        button.setSize(100, 40);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\20.png");
        Image temp1 = image.getImage().getScaledInstance(260,200,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
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
        button.setLocation(550, HEIGHT * 3 / 5);
        button.setSize(100, 40);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\19.png");
        Image temp1 = image.getImage().getScaledInstance(260,200,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }

    //todo:finish it
    private void addRedPointLabel() {
        Chessboard chessboard = new Chessboard(720,720);
        JLabel redPointLabel = new JLabel();
        redPointLabel.setText(String.format("红方得分: %d",chessboard.getRedPoint()));
        redPointLabel.setLocation(500, HEIGHT / 5);
        redPointLabel.setSize(200, 60);
        redPointLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        redPointLabel.setForeground(Color.WHITE);
        add(redPointLabel);
    }

    //todo:finish it
    private void addBlackPointLabel() {
        JLabel blackPointLabel = new JLabel();
        blackPointLabel.setText(String.format("黑方得分: %d",0));
        blackPointLabel.setLocation(500, HEIGHT * 3 / 20);
        blackPointLabel.setSize(200, 60);
        blackPointLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        blackPointLabel.setForeground(Color.WHITE);
        add(blackPointLabel);
    }

    //todo:finish it
    private void addRegretButton() {
        JButton button = new JButton("悔棋");
        button.addActionListener((e) -> {

        });
        button.setLocation(550, HEIGHT * 2 / 5);
        button.setSize(100, 40);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\18.png");
        Image temp1 = image.getImage().getScaledInstance(260,200,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
}