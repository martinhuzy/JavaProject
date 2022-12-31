package view;

import accounts.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


public class Creat {
    public void creatJf() {
              /*  //使用awt工具箱
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = tk.getImage("com/qiku/work/work0926/img.jpg");*/
        //创建JFrame实例
        JFrame frame = new JFrame("登录账户");
        //设置窗体宽高
        frame.setSize(350, 200);
        frame.setLocationRelativeTo(null); //设置窗口居中显示
        //设置关闭窗口结束程序
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //设置窗体禁止调节大小
        frame.setResizable(false);
      /*  //改变窗口默认图标
        frame.setIconImage(image);*/
        //创建面板
        JPanel jPanel = new JPanel();

        jPanel.setLayout(null);

        //创建JLabel(用户名)
        JLabel user_label = new JLabel("用户名:");
        user_label.setFont(new Font("微软雅黑", Font.PLAIN, 13));
        //定义组件的位置和宽高
        user_label.setBounds(10, 20, 80, 25);
        //把组件添加到JPanel上
        jPanel.add(user_label);

        //创建文本域用于用户输入
        JTextField user_text = new JTextField();
        //设置文本域的位置和宽高
        user_text.setBounds(100, 20, 165, 25);
        //把文本域组件添加上
        jPanel.add(user_text);

        //创建JLabel(密码)
        JLabel password_label = new JLabel("密码:");
        password_label.setFont(new Font("微软雅黑", 0, 13));
        //设置位置和大小
        password_label.setBounds(10, 50, 80, 25);
        //添加组件
        jPanel.add(password_label);

        //密码文本域输入
        JPasswordField password_text = new JPasswordField();  //密码输入框，输入密码自动隐藏
        //JTextField password_text = new JTextField(20);
        password_text.setBounds(100, 50, 165, 25);

        jPanel.add(password_text);


        //登录按钮
        JButton login = new JButton("登录");
        login.setBounds(80, 100, 80, 25);
        login.repaint();


        //注册按钮
        JButton register = new JButton("注册");
        register.setBounds(200, 100, 80, 25);
        register.repaint();
        jPanel.add(register);
        jPanel.add(login);

        frame.add(jPanel);
        frame.setVisible(true);

        LoginListener ll = new LoginListener(frame, user_text, password_text);
        login.addActionListener(ll);

//        Register.RegisterListener lll = new Register.RegisterListener(frame,user_text,password_text);
//        register.addActionListener(lll);

        ReListener re = new ReListener();
        register.addActionListener(re);
    }

    public class ReListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Register.reShow();
        }
    }

    public class LoginListener implements ActionListener {
        private javax.swing.JTextField jt;//账号输入框对象
        private javax.swing.JPasswordField jp;//密码输入框对象
        private javax.swing.JFrame login;//定义一个窗体对象

        public LoginListener(javax.swing.JFrame login, javax.swing.JTextField jt, javax.swing.JPasswordField jp) {
            this.login = login;//获取登录界面
            this.jt = jt;//获取登录界面中的账号输入框对象
            this.jp = jp;//获取登录界面中的密码输入框对象
        }

        //Account.accounts.add(String.valueOf(jt));
        //Account.roots.add(String.valueOf(jp));

        public void actionPerformed(ActionEvent e) {
            //用户名密码判断
            for (int i = 0; i < Account.accounts.size(); i++) {
                //String myPass=String.valueOf(Account.roots.get(i));'
                String myPass = String.valueOf(jp.getPassword());
                if (jt.getText().equals(Account.accounts.get(i)) && Account.roots.get(i).equals(myPass)) {
                    //if (jt.getText().equals("12") && Arrays.equals(jp.getPassword(), "12".toCharArray())) {
                    JOptionPane.showMessageDialog(null, "登陆成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    Login.createShow();
                    login.setVisible(false);
                } else if (!(jt.getText().equals(Account.accounts.get(0)) && Account.roots.get(0).equals(myPass))
                        && (!jt.getText().equals(Account.accounts.get(Account.accounts.size()-1)) && Account.roots.get(Account.roots.size()-1).equals(myPass))) {
                    {
                        JOptionPane.showMessageDialog(null, "登录名或密码错误", "失败", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // }

            }
        }
    }
}

