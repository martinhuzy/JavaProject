package View;

import Component.Advisor;
import Component.Cannon;
import Component.Chariot;
import Component.Empty;
import Component.General;
import Component.Horse;
import Component.Minister;
import Component.Soldier;
import Component.Grid;
import Controller.ClickController;
import Component.Chess;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 这个类表示棋盘组建，其包含：
 * SquareComponent[][]: 4*8个方块格子组件
 */
public class Chessboard extends JComponent{

    private static final int ROW_SIZE = 8;

    private static final int COL_SIZE = 4;


    private final Grid[][] grids = new Grid[ROW_SIZE][COL_SIZE];
    //todo: you can change the initial player

    private ChessColor currentColor = ChessColor.RED;

    //这个棋盘中的所有组件只共享一个模型控制器

    public final ClickController clickController = new ClickController(this);

    private final int CHESS_SIZE;



    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width + 2, height);
        CHESS_SIZE = (height - 6) / 8;
        Grid.setSpacingLength(CHESS_SIZE / 12);
        System.out.printf("chessboard [%d * %d], chess size = %d\n", width, height, CHESS_SIZE);

        initAllChessOnBoard();
    }
    public Grid[][] getChessComponents() {
        return grids;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }


    /**
     * 将Grid 放置在 ChessBoard上。里面包含移除原有的component及放置新的component
     * @param grid
     */
    public void putChessOnBoard(Grid grid) {
        int row = grid.getChessboardPoint().getX(), col = grid.getChessboardPoint().getY();
        if (grids[row][col] != null) {
            remove(grids[row][col]);
        }
        add(grids[row][col] = grid);
    }


    public ClickController getClickController() {
        return clickController;
    }

    /**
     * 交换chess1 chess2的位置
     * @param chess1
     * @param chess2
     */


    public void SwapChessComponents(Grid chess1, Grid chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if(chess1.getLevel() >= chess2.getLevel()&& !(chess1.getLevel() == 6 && chess2.getLevel() == 1)) {
            if (!(chess2 instanceof Empty)) {
                remove(chess2);
                add(chess2 = new Empty(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE, chess2.getLevel()));
            }
        }
            chess1.swapLocation(chess2);
            int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
            grids[row1][col1] = chess1;
            int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
            grids[row2][col2] = chess2;


        //只重新绘制chess1 chess2，其他不变
        chess1.repaint();
        chess2.repaint();
    }


    //FIXME:   Initialize chessboard for testing only.
    private void initAllChessOnBoard() {
        Random random = new Random();
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                ChessColor color = random.nextInt(2) == 0 ? ChessColor.RED : ChessColor.BLACK;
                Grid grid;
                if (random.nextInt(2) == 0) {
                    grid = new Chariot(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,3);
                }
                else if (random.nextInt(2) == 0){
                    grid = new Advisor(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,5);
                }
                else if (random.nextInt(2) == 0){
                    grid = new Cannon(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,0);
                }
                else if (random.nextInt(2) == 0){
                    grid = new General(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,6);
                }
                else if (random.nextInt(2) == 0){
                    grid = new Horse(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,2);
                }
                else if (random.nextInt(2) == 0){
                    grid = new Minister(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,4);
                }
                else {
                    grid = new Soldier(new ChessboardPoint(i, j), calculatePoint(i, j), color, clickController,CHESS_SIZE,1);
                }
                grid.setVisible(true);
                putChessOnBoard(grid);
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
















































