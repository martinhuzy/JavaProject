package controller;


import chessComponent.SquareComponent;
import chessComponent.EmptySlotComponent;
import media.MusicStuff2;
import model.ChessColor;
import view.Chessboard;
import view.GameFrame;

public class ClickController {
    private final Chessboard chessboard;
    private SquareComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(SquareComponent squareComponent) {
        //判断第一次点击
        if (first == null) {
            if (handleFirst(squareComponent)) {
                squareComponent.setSelected(true);
                first = squareComponent;
                first.repaint();
            }
        } else {
            if (first == squareComponent) { // 再次点击取消选取
                squareComponent.setSelected(false);
                SquareComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(squareComponent)) {
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, squareComponent);
                chessboard.clickController.swapPlayer();

                first.setSelected(false);
                first = null;
            }
        }
    }


    /**
     * @param squareComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(SquareComponent squareComponent) {
        if (!squareComponent.isReversal()) {
            if (!(squareComponent instanceof EmptySlotComponent)) {
                squareComponent.setReversal(true);
                String filepath = "./resource/翻开.wav";
                    MusicStuff2 reverseMusic = new MusicStuff2();
                reverseMusic.playMusic(filepath);
                System.out.printf("onClick to reverse a chess [%d,%d]\n", squareComponent.getChessboardPoint().getX(), squareComponent.getChessboardPoint().getY());
                squareComponent.repaint();
                chessboard.clickController.swapPlayer();
                return false;
            }
        }
        return squareComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param squareComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(SquareComponent squareComponent) {

        //没翻开或空棋子，进入if
        /**if (!squareComponent.isReversal()) {
         //没翻开且非空棋子不能走
         if (!(squareComponent instanceof EmptySlotComponent)) {
         return false;
         }
         }*/
        return //squareComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), squareComponent.getChessboardPoint());
    }

    public void swapPlayer() {
        chessboard.setCurrentColor(chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.RED : ChessColor.BLACK);
        GameFrame.getStatusLabel().setForeground((chessboard.getCurrentColor() == ChessColor.BLACK ? ChessColor.BLACK : ChessColor.RED).getColor());
        GameFrame.getStatusLabel().setText(chessboard.getCurrentColor() == ChessColor.BLACK ?String.format("轮到黑方"): String.format("轮到红方") );
    }
}
