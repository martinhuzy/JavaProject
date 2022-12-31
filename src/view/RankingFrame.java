package view;

import accounts.Account;
import controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class RankingFrame extends JFrame {
    private int WIDTH;
    private int HEIGHT;
    private static JLabel statusLabel;
    private GameController gameController;
    public RankingFrame rankingFrame;

    public RankingFrame (int WIDTH,int HEIGHT) {
        setTitle("排行榜");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addRankingTitle();
        addRanking();
        addUserID();
        addScore();
        addBackButton();
        addBackGround();

    }
    private void addBackGround() {
        ImageIcon scaledImage=new ImageIcon(".\\resource\\40.png");
        JLabel picture = new JLabel(scaledImage);
        picture.setSize(700,660);
        picture.setBounds(-8,-10,720,720);
        JPanel pan = (JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(null);
        add(picture,JLayeredPane.DEFAULT_LAYER);
    }

    private void addRankingTitle(){
        JLabel rankingTitle = new JLabel("本地玩家排行榜");
        rankingTitle.setFont((new Font("微软雅黑", Font.PLAIN, 35)));
        rankingTitle.setBounds(235,33,500,100);
        rankingTitle.setVisible(true);
        add(rankingTitle);
    }

    private void addRanking(){
        JLabel rankingTitle = new JLabel("排行");
        rankingTitle.setFont((new Font("微软雅黑", Font.PLAIN, 30)));
        rankingTitle.setBounds(85,209,500,100);
        rankingTitle.setVisible(true);
        add(rankingTitle);
    }

    private void addUserID(){
        JLabel rankingTitle = new JLabel("用户名");
        rankingTitle.setFont((new Font("微软雅黑", Font.PLAIN, 30)));
        rankingTitle.setBounds(300,209,500,100);
        rankingTitle.setVisible(true);
        add(rankingTitle);
    }
    private void addScore(){
        JLabel rankingTitle = new JLabel("总得分");
        rankingTitle.setFont((new Font("微软雅黑", Font.PLAIN, 30)));
        rankingTitle.setBounds(500,209,500,100);
        rankingTitle.setVisible(true);
        add(rankingTitle);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("宋体", Font.BOLD, 40));
        g.drawString(Account.now, 330, 375);
        g.drawString(String.format(String.valueOf(Chessboard.score)),550,375);
    }
    private void addBackButton() {
        JButton button = new JButton("返回主菜单");
        button.addActionListener((e) -> {
            PlatformFrame mainFrame = new PlatformFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        });
        button.setLocation(260, 152);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\26.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        button.requestFocus();
        button.revalidate();
        button.repaint();
        add(button);

    }
}