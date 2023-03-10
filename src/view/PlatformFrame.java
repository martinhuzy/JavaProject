package view;

import media.MusicStuff1;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;

public class PlatformFrame extends JFrame {
    private int WIDTH;
    private int HEIGHT;
    private static JLabel statusLabel;
    public static boolean flag = true;

    public PlatformFrame(int WIDTH,int HEIGHT) {
        setTitle("DarkChess");
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        addLabelButton();
        addStartGameButton();
        addRegisterButton();
        addRankButton();
        addSettingsButton();
        addExitButton();
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
    private void addLabelButton() {
        statusLabel = new JLabel("中国象棋暗棋");
        statusLabel.setLocation(WIDTH / 10, 80);
        statusLabel.setSize(600, 85);
        statusLabel.setFont(new Font("楷体", Font.BOLD, 50));
        statusLabel.setForeground(Color.YELLOW);
        add(statusLabel);
    }

    private void addStartGameButton() {
        JButton button = new JButton("开始游戏");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                StartGameFrame mainFrame = new StartGameFrame(720, 720);
                mainFrame.setVisible(true);
                setVisible(false);
            });
        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 120);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\27.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addRegisterButton() {
        JButton button = new JButton("修改本地账户");
        button.addActionListener((e) -> SwingUtilities.invokeLater(() -> {
            ChangeAccountFrame mainFrame = new ChangeAccountFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        }));
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 200);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\28.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addRankButton() {
        JButton button = new JButton("本地玩家排行榜");
        button.addActionListener((e) -> {
            SwingUtilities.invokeLater(() -> {
                RankingFrame mainFrame = new RankingFrame(720, 720);
                mainFrame.setVisible(true);
                setVisible(false);
            });
        }); button.setLocation(WIDTH / 10, HEIGHT / 10 + 280);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\29.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addSettingsButton() {
        JButton button = new JButton("设置");
        button.addActionListener((e) -> {
            Object[] options ={"打开","关闭","调节"};
            int m = JOptionPane.showOptionDialog(null,"调整背景音乐","音乐",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            String filepath = "./resource/项斯华 - 高山流水 (古筝独奏).wav";
            MusicStuff1 backGroundMusic = new MusicStuff1();

            if (m == JOptionPane.YES_OPTION) {
                backGroundMusic.playMusic(filepath);
            }
            if(m == JOptionPane.NO_OPTION) {
                backGroundMusic.endMusic();

            }
            if(m == JOptionPane.CANCEL_OPTION){
                String s = JOptionPane.showInputDialog(this, "请输入音量大小(0-100)");
                try {
                    File file = new File("./resource/项斯华 - 高山流水 (古筝独奏).wav");
                    Clip clip;
                    if (file.exists()) {
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);
                        clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                        // value可以用来设置音量，从0-100.0
                        double value = Double.parseDouble(s);
                        float dB = (float) (-40.0 *  (1 - value / 100));
                        volume.setValue(dB);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 360);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\30.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
    private void addExitButton() {
        JButton button = new JButton("退出游戏");
        button.addActionListener((e) -> {
            Object[] options ={"确认","取消"};
            int m = JOptionPane.showOptionDialog(null,"确认要退出游戏吗？","提示",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (m == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        button.setLocation(WIDTH / 10, HEIGHT / 10 + 440);
        button.setSize(180, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        ImageIcon image = new ImageIcon(".\\resource\\31.png");
        Image temp1 = image.getImage().getScaledInstance(200,60,image.getImage().SCALE_SMOOTH);
        image = new ImageIcon(temp1);
        button.setIcon(image);
        add(button);
    }
}
