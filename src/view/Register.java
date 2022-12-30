package view;

import accounts.Account;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register {
    public static void reShow() {
        //创建JFrame实例
        JFrame frame = new JFrame("欢迎注册");
        //设置窗体宽高
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); //设置窗口居中显示
//        //设置窗体禁止调节大小
        frame.setResizable(false);
        //创建面板
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JLabel label = new JLabel("欢迎来到注册页面!");
        label.setBounds(230, 0, 400, 40);
        label.setFont(new Font("楷体", Font.BOLD, 40));
        label.setForeground(Color.BLACK);
        jPanel.add(label);
        //创建JLabel(用户名)
        JLabel user_label = new JLabel("创建您的用户名:");
        user_label.setFont(new Font("楷体", Font.PLAIN, 20));
        user_label.setForeground(Color.RED);
        //定义组件的位置和宽高
        user_label.setBounds(150, 110, 200, 25);
        //把组件添加到JPanel上
        jPanel.add(user_label);

        //创建文本域用于用户输入
        JTextField user_text = new JTextField(20);
        //设置文本域的位置和宽高
        user_text.setBounds(330, 110, 165, 25);
        //把文本域组件添加上
        jPanel.add(user_text);

        //创建JLabel(密码)
        JLabel password_label = new JLabel("设定您的新密码:");
        password_label.setFont(new Font("楷体", Font.PLAIN, 20));
        password_label.setForeground(Color.RED);
        //设置位置和大小
        password_label.setBounds(150, 160, 200, 25);
        //添加组件
        jPanel.add(password_label);

        //密码文本域输入
        JPasswordField password_text = new JPasswordField();  //密码输入框，输入密码自动隐藏
        //JTextField password_text = new JTextField(20);
        password_text.setBounds(330, 160, 165, 25);

        jPanel.add(password_text);

        //创建退出按钮
        JButton button = new JButton("确定创建");
        button.setBounds(350,260,100,30);
        button.repaint();
        button.setVisible(true);

        jPanel.add(button);


        //关闭窗口结束程序
        frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
        //显示窗口
        frame.setVisible(true);

        //添加面板
        frame.add(jPanel);
        //设置可见
        frame.setVisible(true);

//    public static class RegisterListener implements ActionListener {
//        private javax.swing.JTextField jt;//账号输入框对象
//        private javax.swing.JPasswordField jp;//密码输入框对象
//        private javax.swing.JFrame register;//定义一个窗体对象
//
//        public RegisterListener(javax.swing.JFrame register, javax.swing.JTextField jt, javax.swing.JPasswordField jp) {
//            this.register = register;
//            this.jt = jt;//获取登录界面中的账号输入框对象
//            this.jp = jp;//获取登录界面中的密码输入框对象
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            Account.accounts.add(String.valueOf(jt));
//            Account.roots.add(String.valueOf(jp));
//        }
    }
}

