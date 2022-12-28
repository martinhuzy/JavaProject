package view;


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

        addBackGround();
        addResetButton();
        addDeleteAccountButton();
        addAddAccountButton();
    }

    private void addBackGround() {
        ImageIcon icon=new ImageIcon(".\\resource\\12.png");
        JLabel label = new JLabel(icon);
        label.setBounds(-10,0,720,720);
        icon.setImage(icon.getImage().getScaledInstance(750,750,Image.SCALE_SMOOTH));
        JPanel pan = (JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(null);
        this.getLayeredPane().add(label,Integer.valueOf(Integer.MIN_VALUE));
        this.setVisible(true);
    }
    private void addAddAccountButton() {
        JButton button = new JButton("添加账户");
        button.addActionListener((e) -> {
            String newAccount = JOptionPane.showInputDialog(this,"账户名","请输入想要添加的账号", JOptionPane.INFORMATION_MESSAGE);
            if (!accounts.contains(newAccount)) {
                accounts.add(newAccount);
            }
        });
        button.setLocation(WIDTH / 2 - 90, HEIGHT / 2 - 130);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        ImageIcon image = new ImageIcon(".\\resource\\23.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);

        //ImageIcon image = new ImageIcon();
        //Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        //image = new ImageIcon(temp1);
        //button.setIcon(image);

        add(button);
    }
    private void addDeleteAccountButton() {
        JButton button = new JButton("删除账户");
        button.addActionListener((e) ->
                JOptionPane.showMessageDialog(this, "敬请期待！")
        );
        button.setLocation(WIDTH / 2 - 90, HEIGHT / 2 - 30);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        ImageIcon image = new ImageIcon(".\\resource\\24.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);

        //ImageIcon image = new ImageIcon();
        //Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        //image = new ImageIcon(temp1);
        //button.setIcon(image);

        add(button);
    }
    private void addResetButton() {
        JButton button = new JButton("重置账户");
        button.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "敬请期待！");
        });
        button.setLocation(WIDTH / 2 - 90, HEIGHT / 2 + 70);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        ImageIcon image = new ImageIcon(".\\resource\\25.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
}
        //ImageIcon image = new ImageIcon();
        //Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        //image = new ImageIcon(temp1);
        //button.setIcon(image);

