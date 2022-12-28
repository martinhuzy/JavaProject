package accounts;

import view.ChangeAccountFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private ChangeAccountFrame changeAccountFrame;
    public List<Account> accounts= new ArrayList<>();
    public void addAccount() {
        String account = "aa";
        JOptionPane.showInputDialog(changeAccountFrame,2,"请输入想要添加的账号", JOptionPane.INFORMATION_MESSAGE);
    }
}