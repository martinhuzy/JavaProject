package view;

import chessComponent.*;
import media.MusicStuff2;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {
    int redPoint = 0;
    int blackPoint = 0;
    public static int score = 0;


    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.RED;

    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private ChessType[][] initialChess = new ChessType[8][4];
    private int rememberChess1;
    private int rememberChess2;
    private ChessboardPoint rememberChess1Point;
    private ChessboardPoint rememberChess2Point;
    private Point rememberChess1Location;
    private Point rememberChess2Location;
    private boolean rememberChess1IsReversal;
    private boolean rememberChess2IsReversal;

    private int rr6,rr5,rr4,rr3,rr2,rr1,rr0,rb6,rb5,rb4,rb3,rb2,rb1,rb0;


    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        SquareComponent.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);
        initAllChessOnBoard();
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 4; j ++) {
                initialChess[i][j] = new ChessType();
            }
        }
    }

    public SquareComponent[][] getChessComponents() {
        return squareComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    /**
     * 将SquareComponent 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     * @param squareComponent
     */
    public void putChessOnBoard(SquareComponent squareComponent) {
        int row = squareComponent.getChessboardPoint().getX(), col = squareComponent.getChessboardPoint().getY();
        if (squareComponents[row][col] != null) {
            remove(squareComponents[row][col]);
        }
        add(squareComponents[row][col] = squareComponent);
    }

    /**
     * 交换chess1 chess2的位置
     * @param chess1
     * @param chess2
     */
    public void swapChessComponents(SquareComponent chess1, SquareComponent chess2) {
        rememberChess1Point = chess1.getChessboardPoint();
        rememberChess1Location = chess1.getLocation();
        rememberChess1IsReversal = chess1.isReversal();
        rememberChess2Point = chess2.getChessboardPoint();
        rememberChess2Location = chess2.getLocation();
        rememberChess2IsReversal = chess2.isReversal();
        if (chess1.chessColor == ChessColor.BLACK) {
            if (chess1 instanceof GeneralChessComponent) {
                rememberChess1 = 0;
            } else if (chess1 instanceof AdvisorChessComponent) {
                rememberChess1 = 1;
            } else if (chess1 instanceof MinisterChessComponent) {
                rememberChess1 = 2;
            } else if (chess1 instanceof ChariotChessComponent) {
                rememberChess1 = 3;
            } else if (chess1 instanceof HorseChessComponent) {
                rememberChess1 = 4;
            } else if (chess1 instanceof CannonChessComponent) {
                rememberChess1 = 5;
            } else if (chess1 instanceof SoldierChessComponent) {
                rememberChess1 = 6;
            }
        } else if (chess1.chessColor == ChessColor.RED) {
            if (chess1 instanceof GeneralChessComponent) {
                rememberChess1 = 7;
            } else if (chess1 instanceof AdvisorChessComponent) {
                rememberChess1 = 8;
            } else if (chess1 instanceof MinisterChessComponent) {
                rememberChess1 = 9;
            } else if (chess1 instanceof ChariotChessComponent) {
                rememberChess1 = 10;
            } else if (chess1 instanceof HorseChessComponent) {
                rememberChess1 = 11;
            } else if (chess1 instanceof CannonChessComponent) {
                rememberChess1 = 12;
            } else if (chess1 instanceof SoldierChessComponent) {
                rememberChess1 = 13;
            }
        } else if (chess1 instanceof EmptySlotComponent) {
            rememberChess1 = 14;
        }
        if (chess2.chessColor == ChessColor.BLACK) {
            if (chess2 instanceof GeneralChessComponent) {
                rememberChess2 = 0;
            } else if (chess2 instanceof AdvisorChessComponent) {
                rememberChess2 = 1;
            } else if (chess1 instanceof MinisterChessComponent) {
                rememberChess2 = 2;
            } else if (chess1 instanceof ChariotChessComponent) {
                rememberChess2 = 3;
            } else if (chess1 instanceof HorseChessComponent) {
                rememberChess2 = 4;
            } else if (chess1 instanceof CannonChessComponent) {
                rememberChess2 = 5;
            } else if (chess1 instanceof SoldierChessComponent) {
                rememberChess2 = 6;
            }
        } else if (chess1.chessColor == ChessColor.RED) {
            if (chess2 instanceof GeneralChessComponent) {
                rememberChess2 = 7;
            } else if (chess1 instanceof AdvisorChessComponent) {
                rememberChess2 = 8;
            } else if (chess1 instanceof MinisterChessComponent) {
                rememberChess2 = 9;
            } else if (chess1 instanceof ChariotChessComponent) {
                rememberChess2 = 10;
            } else if (chess1 instanceof HorseChessComponent) {
                rememberChess2 = 11;
            } else if (chess1 instanceof CannonChessComponent) {
                rememberChess2 = 12;
            } else if (chess1 instanceof SoldierChessComponent) {
                rememberChess2 = 13;
            }
        } else if (chess1 instanceof EmptySlotComponent) {
            rememberChess2 = 14;
        }
        rr6 = GameFrameHandle.gameFrame.r6;
        rr5 = GameFrameHandle.gameFrame.r5;
        rr4 = GameFrameHandle.gameFrame.r4;
        rr3 = GameFrameHandle.gameFrame.r3;
        rr2 = GameFrameHandle.gameFrame.r2;
        rr1 = GameFrameHandle.gameFrame.r1;
        rr0 = GameFrameHandle.gameFrame.r0;
        rb6 = GameFrameHandle.gameFrame.b6;
        rb5 = GameFrameHandle.gameFrame.b5;
        rb4 = GameFrameHandle.gameFrame.b4;
        rb3 = GameFrameHandle.gameFrame.b3;
        rb2 = GameFrameHandle.gameFrame.b2;
        rb1 = GameFrameHandle.gameFrame.b1;
        rb0 = GameFrameHandle.gameFrame.b0;

        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
            if (chess2.getChessColor() == ChessColor.BLACK) {
                redPoint += chess2.points;
                System.out.println(redPoint);
            } else {
                blackPoint += chess2.points;
                System.out.println(blackPoint);
            }
            String filepath = "./resource/吃.wav";
            MusicStuff2 captureMusic = new MusicStuff2();
            captureMusic.playMusic(filepath);
            if (chess2.chessColor == ChessColor.BLACK) {
                if (chess2 instanceof GeneralChessComponent) {
                    GameFrameHandle.gameFrame.b6 ++;
                } else if (chess2 instanceof AdvisorChessComponent) {
                    GameFrameHandle.gameFrame.b5 ++;
                } else if (chess2 instanceof MinisterChessComponent) {
                    GameFrameHandle.gameFrame.b4 ++;
                } else if (chess2 instanceof CannonChessComponent) {
                    GameFrameHandle.gameFrame.b3 ++;
                } else if (chess2 instanceof ChariotChessComponent) {
                    GameFrameHandle.gameFrame.b2 ++;
                } else if (chess2 instanceof HorseChessComponent) {
                    GameFrameHandle.gameFrame.b1 ++;
                } else if (chess2 instanceof SoldierChessComponent) {
                    GameFrameHandle.gameFrame.b0 ++;
                }
            } else {
                if (chess2 instanceof GeneralChessComponent) {
                    GameFrameHandle.gameFrame.r6 ++;
                } else if (chess2 instanceof AdvisorChessComponent) {
                    GameFrameHandle.gameFrame.r5 ++;
                } else if (chess2 instanceof MinisterChessComponent) {
                    GameFrameHandle.gameFrame.r4 ++;
                } else if (chess2 instanceof CannonChessComponent) {
                    GameFrameHandle.gameFrame.r3 ++;
                } else if (chess2 instanceof ChariotChessComponent) {
                    GameFrameHandle.gameFrame.r2 ++;
                } else if (chess2 instanceof HorseChessComponent) {
                    GameFrameHandle.gameFrame.r1 ++;
                } else if (chess2 instanceof SoldierChessComponent) {
                    GameFrameHandle.gameFrame.r0 ++;
                }
            }
            GameFrameHandle.gameFrame.repaint();
            remove(chess2);
            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        squareComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        squareComponents[row2][col2] = chess2;

        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();
        checkWinner();

        String filepath = "./resource/移动.wav";
        MusicStuff2 moveMusic = new MusicStuff2();
        moveMusic.playMusic(filepath);


    }


    public static class ChessType {
        int priority;
        int chessColor;
        public ChessType(int priority,int chessColor) {
            this.priority = priority;
            this.chessColor = chessColor;
        }
        public ChessType() {}
    }

    private ChessType[][] randomChess() {
        ChessType[][] randomChess = new ChessType[8][4];
        ArrayList<ChessType> allChess = new ArrayList<>();
        for (int i = 0; i <= 1; i ++) {
            for (int j = 0; j < 5; j ++) {
                allChess.add(new ChessType(0,i));
            }
            for (int j = 0; j < 2; j ++) {
                allChess.add(new ChessType(1,i));
            }
            for (int j = 0; j < 2; j ++) {
                allChess.add(new ChessType(2,i));
            }
            for (int j = 0; j < 2; j ++) {
                allChess.add(new ChessType(3,i));
            }
            for (int j = 0; j < 2; j ++) {
                allChess.add(new ChessType(4,i));
            }
            for (int j = 0; j < 2; j ++) {
                allChess.add(new ChessType(5,i));
            }
            allChess.add(new ChessType(6,i));
        }
        Collections.shuffle(allChess);
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 4; j ++) {
                randomChess[i][j] = allChess.get(i * 4 + j);
            }
        }
        return randomChess;
    }


    public void initAllChessOnBoard() {
        initialChess = randomChess();
        for (int i = 0; i < squareComponents.length; i++) {
            for (int j = 0; j < squareComponents[i].length; j++) {
                SquareComponent squareComponent = null;
                if (initialChess[i][j].priority == 0 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 1 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 2 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 3 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 4 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 5 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 6 && initialChess[i][j].chessColor == 0) {
                    squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 0 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 1 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 2 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 3 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 4 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 5 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                } else if (initialChess[i][j].priority == 6 && initialChess[i][j].chessColor == 1) {
                    squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                }
                squareComponent.setVisible(true);
                putChessOnBoard(squareComponent);
            }
        }
    }


    /**
     * 绘制棋盘格子
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 将棋盘上行列坐标映射成Swing组件的Point
     * @param row 棋盘上的行
     * @param col 棋盘上的列
     * @return
     */
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE + 3, row * CHESS_SIZE + 3);
    }

    /**
     * 通过GameController调用该方法
     * @param chessData
     */
    public void loadGame(List<String> chessData) {

        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 4; j ++) {
                GameFrameHandle.gameFrame.chessboard.squareComponents[i][j].setVisible(false);
                GameFrameHandle.gameFrame.chessboard.squareComponents[i][j] = null;
            }
        }
        System.out.println("清零");
        if (chessData.size() != 19) {
            StartGameFrame mainFrame = new StartGameFrame(720,720);
            mainFrame.setVisible(true);
            JOptionPane.showMessageDialog(mainFrame, "102", "Error", JOptionPane.INFORMATION_MESSAGE);
            GameFrameHandle.gameFrame.setVisible(false);
        } else {
            for (int i = 0; i < 17; i++) {
                if (chessData.get(16).equals("1")) {
                    GameFrame.getStatusLabel().setForeground(Color.RED);
                    GameFrame.getStatusLabel().setText(String.format("轮到红方"));
                    GameFrameHandle.gameFrame.chessboard.setCurrentColor(ChessColor.RED);
                } else if (Objects.equals(chessData.get(16), "2")) {
                    GameFrameHandle.gameFrame.chessboard.setCurrentColor(ChessColor.BLACK);
                    GameFrame.getStatusLabel().setForeground(Color.BLACK);
                    GameFrame.getStatusLabel().setText(String.format("轮到黑方"));
                } else {
                    StartGameFrame mainFrame = new StartGameFrame(720,720);
                    mainFrame.setVisible(true);
                    JOptionPane.showMessageDialog(mainFrame, "104", "Error", JOptionPane.INFORMATION_MESSAGE);
                    GameFrameHandle.gameFrame.setVisible(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                String[] s = chessData.get(i).split(" ");
                for (int j = 0; j < 4; j++) {
                    SquareComponent squareComponent = null;
                    if (Objects.equals(s[j], "G"))
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "A"))
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "M"))
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "C"))
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "H"))
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "N"))
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "S"))
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.RED, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "g"))
                        squareComponent = new GeneralChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "a"))
                        squareComponent = new AdvisorChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "m"))
                        squareComponent = new MinisterChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "c"))
                        squareComponent = new ChariotChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "h"))
                        squareComponent = new HorseChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "n"))
                        squareComponent = new CannonChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "s"))
                        squareComponent = new SoldierChessComponent(new ChessboardPoint(i, j), calculatePoint(i, j), ChessColor.BLACK, clickController, CHESS_SIZE);
                    else if (Objects.equals(s[j], "0"))
                        squareComponent = new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController,  CHESS_SIZE);
                    else {
                        StartGameFrame mainFrame = new StartGameFrame(720,720);
                        mainFrame.setVisible(true);
                        JOptionPane.showMessageDialog(mainFrame, "103", "Error", JOptionPane.INFORMATION_MESSAGE);
                        GameFrameHandle.gameFrame.setVisible(false);
                    }
                    squareComponent.setVisible(true);
                    putChessOnBoard(squareComponent);
                }
            }

            for (int i = 8; i < 16; i++) {
                String[] s = chessData.get(i).split(" ");
                for (int j = 0; j < 4; j++) {
                    GameFrameHandle.gameFrame.chessboard.squareComponents[i - 8][j].setReversal(!Objects.equals(s[j], "0"));
                }
            }
            String[] s1 = chessData.get(17).split(" ");
            String[] s2 = chessData.get(18).split(" ");
            GameFrameHandle.gameFrame.r6 = Integer.parseInt(s1[0]);
            GameFrameHandle.gameFrame.r5 = Integer.parseInt(s1[1]);
            GameFrameHandle.gameFrame.r4 = Integer.parseInt(s1[2]);
            GameFrameHandle.gameFrame.r3 = Integer.parseInt(s1[3]);
            GameFrameHandle.gameFrame.r2 = Integer.parseInt(s1[4]);
            GameFrameHandle.gameFrame.r1 = Integer.parseInt(s1[5]);
            GameFrameHandle.gameFrame.r0 = Integer.parseInt(s1[6]);
            GameFrameHandle.gameFrame.b6 = Integer.parseInt(s2[0]);
            GameFrameHandle.gameFrame.b5 = Integer.parseInt(s2[1]);
            GameFrameHandle.gameFrame.b4 = Integer.parseInt(s2[2]);
            GameFrameHandle.gameFrame.b3 = Integer.parseInt(s2[3]);
            GameFrameHandle.gameFrame.b2 = Integer.parseInt(s2[4]);
            GameFrameHandle.gameFrame.b1 = Integer.parseInt(s2[5]);
            GameFrameHandle.gameFrame.b0 = Integer.parseInt(s2[6]);

            //GameFrameHandle.gameFrame.chessboard.setVisible(true);
            GameFrameHandle.gameFrame.repaint();
        }
    }

    public void saveGame() {
        String[][] allChess = new String[8][4];
        int[][] allChessReversal = new int[8][4];//0.hidden 1.reversal
        int colorNow = 0;//1.red 2.black
        if (getCurrentColor() == ChessColor.RED)
            colorNow = 1;
        if (getCurrentColor() == ChessColor.BLACK)
            colorNow = 2;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 4; j ++) {
                if (squareComponents[i][j].chessColor == ChessColor.RED) {
                    if (squareComponents[i][j] instanceof GeneralChessComponent)
                        allChess[i][j] = "G";
                    else if (squareComponents[i][j] instanceof AdvisorChessComponent)
                        allChess[i][j] = "A";
                    else if (squareComponents[i][j] instanceof MinisterChessComponent)
                        allChess[i][j] = "M";
                    else if (squareComponents[i][j] instanceof ChariotChessComponent)
                        allChess[i][j] = "C";
                    else if (squareComponents[i][j] instanceof HorseChessComponent)
                        allChess[i][j] = "H";
                    else if (squareComponents[i][j] instanceof CannonChessComponent)
                        allChess[i][j] = "N";
                    else if (squareComponents[i][j] instanceof SoldierChessComponent)
                        allChess[i][j] = "S";
                    else if (squareComponents[i][j] instanceof EmptySlotComponent)
                        allChess[i][j] = "0";
                }  else {
                    if (squareComponents[i][j] instanceof GeneralChessComponent)
                        allChess[i][j] = "g";
                    else if (squareComponents[i][j] instanceof AdvisorChessComponent)
                        allChess[i][j] = "a";
                    else if (squareComponents[i][j] instanceof MinisterChessComponent)
                        allChess[i][j] = "m";
                    else if (squareComponents[i][j] instanceof ChariotChessComponent)
                        allChess[i][j] = "c";
                    else if (squareComponents[i][j] instanceof HorseChessComponent)
                        allChess[i][j] = "h";
                    else if (squareComponents[i][j] instanceof CannonChessComponent)
                        allChess[i][j] = "n";
                    else if (squareComponents[i][j] instanceof SoldierChessComponent)
                        allChess[i][j] = "s";
                    else if (squareComponents[i][j] instanceof EmptySlotComponent)
                        allChess[i][j] = "0";
                }

                if (!(squareComponents[i][j].isReversal()))
                    allChessReversal[i][j] = 0;
                else
                    allChessReversal[i][j] = 1;
            }
        }
        try {
            String path = "./saves";
            String title = "存档";
            String content = String.format("%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%s %s %s %s\n%d\n%d %d %d %d %d %d %d\n%d %d %d %d %d %d %d\n",
                    allChess[0][0],allChess[0][1],allChess[0][2],allChess[0][3],allChess[1][0],allChess[1][1],allChess[1][2],allChess[1][3],
                    allChess[2][0],allChess[2][1],allChess[2][2],allChess[2][3],allChess[3][0],allChess[3][1],allChess[3][2],allChess[3][3],
                    allChess[4][0],allChess[4][1],allChess[4][2],allChess[4][3],allChess[5][0],allChess[5][1],allChess[5][2],allChess[5][3],
                    allChess[6][0],allChess[6][1],allChess[6][2],allChess[6][3],allChess[7][0],allChess[7][1],allChess[7][2],allChess[7][3],
                    allChessReversal[0][0],allChessReversal[0][1],allChessReversal[0][2],allChessReversal[0][3],allChessReversal[1][0],allChessReversal[1][1],allChessReversal[1][2],allChessReversal[1][3],
                    allChessReversal[2][0],allChessReversal[2][1],allChessReversal[2][2],allChessReversal[2][3],allChessReversal[3][0],allChessReversal[3][1],allChessReversal[3][2],allChessReversal[3][3],
                    allChessReversal[4][0],allChessReversal[4][1],allChessReversal[4][2],allChessReversal[4][3],allChessReversal[5][0],allChessReversal[5][1],allChessReversal[5][2],allChessReversal[5][3],
                    allChessReversal[6][0],allChessReversal[6][1],allChessReversal[6][2],allChessReversal[6][3],allChessReversal[7][0],allChessReversal[7][1],allChessReversal[7][2],allChessReversal[7][3],
                    colorNow,
                    GameFrameHandle.gameFrame.r6,GameFrameHandle.gameFrame.r5,GameFrameHandle.gameFrame.r4,GameFrameHandle.gameFrame.r3,GameFrameHandle.gameFrame.r2,GameFrameHandle.gameFrame.r1,GameFrameHandle.gameFrame.r0,
                    GameFrameHandle.gameFrame.b6,GameFrameHandle.gameFrame.b5,GameFrameHandle.gameFrame.b4,GameFrameHandle.gameFrame.b3,GameFrameHandle.gameFrame.b2,GameFrameHandle.gameFrame.b1,GameFrameHandle.gameFrame.b0
            );
            File save = new File(path);
            if (!save.exists()) {
                save.mkdirs();
            }
            File writeName = new File(path + "/" + title + ".txt");
            if (!writeName.exists()) {
                writeName.createNewFile();
            }
//            else {
//                String osName = System.getProperties().getProperty("os.name");
//                if (osName.equals("Linux")) {
//                    content = "\r" + content;
//                } else {
//                    content = "\r\n" + content;
//                }
//            }
            BufferedWriter out = new BufferedWriter(new FileWriter(writeName, false));
            out.write(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkWinner() {
        if (GameFrameHandle.gameFrame.r6 * 30 + GameFrameHandle.gameFrame.r5 * 10 + (GameFrameHandle.gameFrame.r4 + GameFrameHandle.gameFrame.r3 + GameFrameHandle.gameFrame.r2 + GameFrameHandle.gameFrame.r1) * 5 + GameFrameHandle.gameFrame.r0 >= 60) {
            JOptionPane.showMessageDialog(this, "黑方胜!");
            score += 1;
            GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
            GameFrameHandle.gameFrame.setVisible(false);
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        } else if (GameFrameHandle.gameFrame.b6 * 30 + GameFrameHandle.gameFrame.b5 * 10 + (GameFrameHandle.gameFrame.b4 + GameFrameHandle.gameFrame.b3 + GameFrameHandle.gameFrame.b2 + GameFrameHandle.gameFrame.b1) * 5 + GameFrameHandle.gameFrame.b0 >= 60) {
            JOptionPane.showMessageDialog(this, "红方胜!");
            score += 1;
            GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
            GameFrameHandle.gameFrame.setVisible(false);
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        }
    }

    public void withdraw() {
        SquareComponent squareComponent;
        if (rememberChess1 == 0) {
            squareComponent = new GeneralChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 1) {
            squareComponent = new AdvisorChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 2) {
            squareComponent = new MinisterChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 3) {
            squareComponent = new ChariotChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 4) {
            squareComponent = new HorseChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 5) {
            squareComponent = new CannonChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 6) {
            squareComponent = new SoldierChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 7) {
            squareComponent = new GeneralChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 8) {
            squareComponent = new AdvisorChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 9) {
            squareComponent = new MinisterChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 10) {
            squareComponent = new ChariotChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 11) {
            squareComponent = new HorseChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 12) {
            squareComponent = new CannonChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 13) {
            squareComponent = new SoldierChessComponent(rememberChess1Point,rememberChess1Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess1IsReversal);
        } else if (rememberChess1 == 14) {
            squareComponent = new EmptySlotComponent(rememberChess1Point, rememberChess1Location, clickController, CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
        }
        if (rememberChess2 == 0) {
            squareComponent = new GeneralChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 1) {
            squareComponent = new AdvisorChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 2) {
            squareComponent = new MinisterChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 3) {
            squareComponent = new ChariotChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 4) {
            squareComponent = new HorseChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 5) {
            squareComponent = new CannonChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 6) {
            squareComponent = new SoldierChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.BLACK,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 7) {
            squareComponent = new GeneralChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 8) {
            squareComponent = new AdvisorChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 9) {
            squareComponent = new MinisterChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 10) {
            squareComponent = new MinisterChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 11) {
            squareComponent = new HorseChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 12) {
            squareComponent = new CannonChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 13) {
            squareComponent = new SoldierChessComponent(rememberChess2Point,rememberChess2Location,ChessColor.RED,clickController,CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
            squareComponent.setReversal(rememberChess2IsReversal);
        } else if (rememberChess2 == 14) {
            squareComponent = new EmptySlotComponent(rememberChess2Point, rememberChess2Location, clickController, CHESS_SIZE);
            GameFrameHandle.gameFrame.chessboard.putChessOnBoard(squareComponent);
        }
        GameFrameHandle.gameFrame.r6 = rr6;
        GameFrameHandle.gameFrame.r5 = rr5;
        GameFrameHandle.gameFrame.r4 = rr4;
        GameFrameHandle.gameFrame.r3 = rr3;
        GameFrameHandle.gameFrame.r2 = rr2;
        GameFrameHandle.gameFrame.r1 = rr1;
        GameFrameHandle.gameFrame.r0 = rr0;
        GameFrameHandle.gameFrame.b6 = rb6;
        GameFrameHandle.gameFrame.b5 = rb5;
        GameFrameHandle.gameFrame.b4 = rb4;
        GameFrameHandle.gameFrame.b3 = rb3;
        GameFrameHandle.gameFrame.b2 = rb2;
        GameFrameHandle.gameFrame.b1 = rb1;
        GameFrameHandle.gameFrame.b0 = rb0;
        clickController.swapPlayer();
        GameFrameHandle.gameFrame.repaint();

    }
}