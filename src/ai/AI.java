package ai;

import chessComponent.CannonChessComponent;
import chessComponent.SquareComponent;
import model.ChessColor;
import view.GameFrame;
import view.GameFrameHandle;

import java.util.Random;

import static view.GameFrameHandle.*;


public class AI extends Thread {
    GameFrame mainFrame;
    private int mode = 1;//0还有没翻开的棋  1所有棋都翻开了
    public AI(GameFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    //简单：能翻就翻，翻完全随机
    public void playChessEasy() {
        while (gameFrame.chessboard.getCurrentColor() == ChessColor.BLACK) {
            System.out.println("I am working!");
            Random random = new Random();
            int randomX = random.nextInt(8);
            int randomY = random.nextInt(4);
            SquareComponent randomSquareComponent = gameFrame.chessboard.getChessComponents()[randomX][randomY];
            for (int i = 0; i < 8; i ++) {
                for (int j = 0; j < 4; j ++) {
                    if (!gameFrame.chessboard.getChessComponents()[i][j].isReversal()) {
                        mode = 0;
                    }
                }
            }
            while (mode == 0) {
                if (!randomSquareComponent.isReversal()) {
                    gameFrame.chessboard.clickController.onClick(randomSquareComponent);
                    break;
                } else {
                    randomX = random.nextInt(8);
                    randomY = random.nextInt(4);
                    randomSquareComponent = gameFrame.chessboard.getChessComponents()[randomX][randomY];
                }
            }
            while (mode == 1) {
                if (randomSquareComponent.getChessColor() != gameFrame.chessboard.getCurrentColor()) {
                    randomX = random.nextInt(8);
                    randomY = random.nextInt(4);
                    randomSquareComponent = gameFrame.chessboard.getChessComponents()[randomX][randomY];
                } else {
                    randomSquareComponent.randomMove();
                }
            }
        }
    }

    //普通：能翻就翻，翻完能吃就吃，不能吃就随机
    public void playChessMedium() {

    }

    //困难：能吃就吃，不能吃能翻就翻，翻完就随机
    public void playChessHard() {

    }


}
