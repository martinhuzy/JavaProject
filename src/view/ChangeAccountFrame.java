package view;

import accounts.Account;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChangeAccountFrame extends JFrame{
    private int WIDTH;
    private int HEIGHT;
    private static JLabel statusLabel;
    private List<String> accounts = new ArrayList<>();
    public ChangeAccountFrame(int WIDTH,int HEIGHT) {
        setTitle("DarkChess");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        //setBounds(200,200,500,500);


        addResetButton();
        addDeleteAccountButton();
        addBackButton();
        //addAddAccountButton();
        addLogInAccountButton();
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

    private void addBackButton() {
        JButton button = new JButton("返回主菜单");
        button.addActionListener((e) -> {
            PlatformFrame mainFrame = new PlatformFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        });
        button.setLocation(270, 520);
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


    private void addLogInAccountButton() {
        JButton button = new JButton("登录账户");
        button.addActionListener((e) -> {
            Creat l = new Creat();
            l.creatJf();
        });

            button.setLocation(WIDTH / 2 - 90, 100);
            button.setSize(180, 60);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            ImageIcon image = new ImageIcon(".\\resource\\38.png");
            Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
            image = new ImageIcon(temp1);
            button.setIcon(image);
            //button.requestFocus();
            //button.requestFocus();
            //button.revalidate();
            //button.repaint();
            add(button);
        }

//    private void addAddAccountButton() {
//        JButton button = new JButton("添加账户");
//        button.addActionListener((e) -> {
//            String newAccount = JOptionPane.showInputDialog(this,"账户名","请输入想要添加的账号", JOptionPane.INFORMATION_MESSAGE);
//            if (!accounts.contains(newAccount)) {
//                accounts.add(newAccount);
//            }
//
//        });
//        button.setLocation(WIDTH / 2 - 90, 170);
//        button.setSize(180, 60);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        ImageIcon image = new ImageIcon(".\\resource\\23.png");
//        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
//        image = new ImageIcon(temp1);
//        button.setIcon(image);
//        button.requestFocus();
//        button.requestFocus();
//        button.revalidate();
//        button.repaint();
//        add(button);
//    }
    private void addDeleteAccountButton() {
        JButton button = new JButton("删除账户");
        button.addActionListener((e) -> {
                    Object[] options = {"确认", "取消"};
            int m = JOptionPane.showOptionDialog(null,"确认要删除账户吗？","提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == JOptionPane.YES_OPTION) {

            }
        });
        button.setLocation(WIDTH / 2 - 90, 240);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        ImageIcon image = new ImageIcon(".\\resource\\24.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.requestFocus();
        button.revalidate();
        button.repaint();
        button.setIcon(image);


        add(button);
    }
    private void addResetButton() {
        JButton button = new JButton("重置账户");
        button.addActionListener((e) -> {
            Object[] options = {"确认", "取消"};
            int m = JOptionPane.showOptionDialog(null,"确认要重置账户吗？","提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == JOptionPane.YES_OPTION) {
                //将分数扣光？
            }
        });
        button.setLocation(WIDTH / 2 - 90, 380);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        ImageIcon image = new ImageIcon(".\\resource\\25.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        button.requestFocus();
        button.revalidate();
        button.repaint();
        add(button);
    }
}
