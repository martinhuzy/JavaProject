package view;


import ai.AI;
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


    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.RED;

    //all chessComponents in this chessboard are shared only one model controller
    public final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private ChessType[][] initialChess = new ChessType[8][4];


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
        for (int i = 0; i < 17; i ++) {
            if (Objects.equals(chessData.get(16), "1")) {
                setCurrentColor(ChessColor.RED);
            } else if (Objects.equals(chessData.get(16), "2")) {
                setCurrentColor(ChessColor.BLACK);
            } else {
                JOptionPane.showMessageDialog(this,"104","Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        GameFrameHandle.gameFrame.repaint();
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
        if (redPoint >= 60) {
            JOptionPane.showMessageDialog(this, "红方胜!");
            GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
            GameFrameHandle.gameFrame.setVisible(false);
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        } else if (blackPoint >= 60) {
            JOptionPane.showMessageDialog(this, "黑方胜!");
            GameFrameHandle.gameFrame = new GameFrame(WIDTH,HEIGHT);
            GameFrameHandle.gameFrame.setVisible(false);
            StartGameFrame mainFrame = new StartGameFrame(720, 720);
            mainFrame.setVisible(true);
            setVisible(false);
        }
    }
}