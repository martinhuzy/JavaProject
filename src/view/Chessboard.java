package view;


import chessComponent.*;
import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent {


    private static final int ROW_SIZE = 8;
    private static final int COL_SIZE = 4;

    private final SquareComponent[][] squareComponents = new SquareComponent[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player
    private ChessColor currentColor = ChessColor.BLACK;

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


    private void initAllChessOnBoard() {
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
        chessData.forEach(System.out::println);
    }
}