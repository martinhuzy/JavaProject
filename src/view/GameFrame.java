package view;

import ai.AI;
import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{

    private final int WIDTH;
    public boolean isCheating = false;
    private final int HEIGHT;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private static JLabel statusLabel;
    public Chessboard chessboard;
    public JLabel redPointLabel;
    public JLabel blackPointLabel;
    public int difficulty = 0;
    public int r6 = 0;
    public int r5 = 0;
    public int r4 = 0;
    public int r3 = 0;
    public int r2 = 0;
    public int r1 = 0;
    public int r0 = 0;
    public int b6 = 0;
    public int b5 = 0;
    public int b4 = 0;
    public int b3 = 0;
    public int b2 = 0;
    public int b1 = 0;
    public int b0 = 0;


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
        //addlabel1();
    }

    private void addBackGround() {
        ImageIcon icon=new ImageIcon(".\\resource\\22.png");
        JLabel label = new JLabel(icon);
        label.setBounds(-10,0,720,720);
        icon.setImage(icon.getImage().getScaledInstance(750,750,Image.SCALE_SMOOTH));
        JPanel pan = (JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(null);
        this.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));
        this.setVisible(true);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.ORANGE);
        g.fillOval(25, 55, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 55, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("将", 30, 95);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 140, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 140, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("士", 30, 180);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 225, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 225, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("相", 30, 265);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 310, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 310, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("炮", 30, 350);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 395, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 395, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("车", 30, 435);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 480, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 480, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("马", 30, 520);

        g.setColor(Color.ORANGE);
        g.fillOval(25, 565, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(25, 565, 50, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("兵", 30, 605);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 55, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 55, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("将", 435, 95);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 140, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 140, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("士", 435, 180);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 225, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 225, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("相", 435, 265);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 310, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 310, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("炮", 435, 350);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 395, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 395, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("车", 435, 435);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 480, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 480, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("马", 435, 520);

        g.setColor(Color.ORANGE);
        g.fillOval(430, 565, 50, 50);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(430, 565, 50, 50);
        g.setColor(Color.BLACK);
        g.setFont(new Font("楷体", Font.BOLD, 40));
        g.drawString("兵", 435, 605);

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
        g.drawOval(68, 85, 20, 20);
        g.drawOval(68, 170, 20, 20);
        g.drawOval(68, 255, 20, 20);
        g.drawOval(68, 340, 20, 20);
        g.drawOval(68, 425, 20, 20);
        g.drawOval(68, 510, 20, 20);
        g.drawOval(68, 595, 20, 20);
        g.drawOval(473, 85, 20, 20);
        g.drawOval(473, 170, 20, 20);
        g.drawOval(473, 255, 20, 20);
        g.drawOval(473, 340, 20, 20);
        g.drawOval(473, 425, 20, 20);
        g.drawOval(473, 510, 20, 20);
        g.drawOval(473, 595, 20, 20);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Rockwell", Font.BOLD, 15));
        g.drawString(String.format("%d",r6),74,100);
        g.drawString(String.format("%d",r5),74,185);
        g.drawString(String.format("%d",r4),74,270);
        g.drawString(String.format("%d",r3),74,355);
        g.drawString(String.format("%d",r2),74,440);
        g.drawString(String.format("%d",r1),74,525);
        g.drawString(String.format("%d",r0),74,610);
        g.drawString(String.format("%d",b6),479,100);
        g.drawString(String.format("%d",b5),479,185);
        g.drawString(String.format("%d",b4),479,270);
        g.drawString(String.format("%d",b3),479,355);
        g.drawString(String.format("%d",b2),479,440);
        g.drawString(String.format("%d",b1),479,525);
        g.drawString(String.format("%d",b0),479,610);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Rockwell", Font.BOLD, 25));
        g.drawString(String.format("%d",r0 + (r1 + r2 + r3 + r4) * 5 + r5 * 10 + r6 * 30),610,180);
        g.drawString(String.format("%d",b0 + (b1 + b2 + b3 + b4) * 5 + b5 *10 + b6 * 30),610,215);

    }

    private Point calculatePoint(int i, int j) {
        return null;
    }

    private void addChessboard() {
        chessboard = new Chessboard(CHESSBOARD_SIZE / 2, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(105, 35);
        add(chessboard);
    }

    private void addLabel() {
        statusLabel = new JLabel("红方先手");
        statusLabel.setLocation(500, HEIGHT / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 40));
        statusLabel.setForeground(Color.YELLOW);
        add(statusLabel);
    }

    public static JLabel getStatusLabel() {
        return statusLabel;
    }

    private void addBackButton() {
        JButton button = new JButton("返回菜单");
        button.addActionListener((e) -> {
            GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
            GameFrameHandle.gameFrame.setVisible(false);
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
        });
        button.setLocation(500, HEIGHT * 7/ 10);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\37.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }

    private void addCheatingModeButton() {
        JButton button = new JButton();
        button.setText("作弊模式：关");
        ImageIcon image1 = new ImageIcon(".\\resource\\33.png");
        Image temp2 = image1.getImage().getScaledInstance(200,60,image1.getImage().SCALE_SMOOTH);
        image1 = new ImageIcon(temp2);
        button.setIcon(image1);
        button.addActionListener((e) -> {
            GameFrameHandle.gameFrame.repaint();
            System.out.println(GameFrameHandle.gameFrame.isCheating);
            if (!isCheating) {
                isCheating = true;
                button.setText("作弊模式：开");
                button.repaint();
                button.setLocation(500, HEIGHT * 3/ 10);
                button.setSize(180, 60);
                button.setFont(new Font("Rockwell", Font.BOLD, 20));
                ImageIcon image = new ImageIcon(".\\resource\\32.png");
                Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
                image = new ImageIcon(temp1);
                button.setIcon(image);
            } else {
                isCheating = false;
                button.setText("作弊模式：关");
                button.repaint();
                button.setLocation(500, HEIGHT * 3/ 10);
                button.setSize(180, 60);
                button.setFont(new Font("Rockwell", Font.BOLD, 20));
                ImageIcon image = new ImageIcon(".\\resource\\33.png");
                Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
                image = new ImageIcon(temp1);
                button.setIcon(image);
            }
        });
        button.setLocation(500, HEIGHT * 3/ 10);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
//    private void addlabel1(){
//        JLabel label = new JLabel();
//        label.setLocation(68,25);
//        label.setSize(100,100);
//        label.setText(String.valueOf(1));
//        add(label);
//
//    }
    private void addRestartButton() {
        JButton button = new JButton("重新开始");
        button.addActionListener((e) -> {
            setVisible(false);
            SwingUtilities.invokeLater(() -> {
                //GameFrame mainFrame = new GameFrame(720, 720);
                GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
                setVisible(true);
                setVisible(false);
            });
        });
        button.setLocation(500, HEIGHT /2);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\35.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }

    //todo: finish it
    private void addSaveGameButton() {
        JButton button = new JButton("存储游戏");
        button.addActionListener((e) -> {
            String path = JOptionPane.showInputDialog(this, "请输入存档路径");
            gameController.saveGameToFile(path);
        });
        button.setLocation(500, HEIGHT * 3 / 5);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\36.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }

    //todo:finish it
    private void addRedPointLabel() {
        redPointLabel = new JLabel();
        //int p;
        //p = r0 + (r1 + r2 + r3 + r4) * 5 + r5 * 10 + r6 * 30;
        redPointLabel.setText("红方得分: ");
        redPointLabel.setLocation(500, HEIGHT / 5);
        redPointLabel.setSize(200, 60);
        redPointLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        redPointLabel.setForeground(Color.yellow);
        add(redPointLabel);
    }

    //todo:finish it
    private void addBlackPointLabel() {
        blackPointLabel = new JLabel();
        int p;
        p = b0 + (b1 + b2 + b3 + b4) * 5 + b5 *10 + b6 * 30;
        blackPointLabel.setText("黑方得分: ");
        blackPointLabel.setLocation(500, HEIGHT * 3 / 20);
        blackPointLabel.setSize(200, 60);
        blackPointLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        blackPointLabel.setForeground(Color.YELLOW);
        add(blackPointLabel);
    }

    //todo:finish it
    private void addRegretButton() {
        JButton button = new JButton("悔棋");
        button.addActionListener((e) -> {

        });
        button.setLocation(500, HEIGHT * 2 / 5);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\34.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }

}