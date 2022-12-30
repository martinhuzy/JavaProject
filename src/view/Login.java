package view;

import javax.swing.*;
import java.awt.*;


public class Login {
    public static void createShow() {
        //创建JFrame实例
        JFrame frame = new JFrame("欢迎您");
        //设置窗体宽高
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); //设置窗口居中显示
        //设置窗体禁止调节大小
        frame.setResizable(false);
        //创建面板
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JLabel label = new JLabel("请开始游玩吧");
        label.setBounds(120, 70, 600, 200);
        label.setFont(new Font("楷体", Font.BOLD, 45));
        label.setForeground(Color.red);
        jPanel.add(label);

        //关闭窗口结束程序
        frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
        //显示窗口
        frame.setVisible(true);

        //添加面板
        frame.add(jPanel);
        //设置可见
        frame.setVisible(true);
    }
}

