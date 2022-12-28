package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class CannonChessComponent extends ChessComponent {

    public CannonChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() == ChessColor.RED) {
            name = "炮";
        } else {
            name = "砲";
        }
        priority = 1;
        points = 5;
    }
    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        int sum = 0;
        if (this.getChessboardPoint().getX() == destination.getX()) {
            for (int i = Math.min(this.getChessboardPoint().getY(),destination.getY()); i <= Math.max(this.getChessboardPoint().getY(),destination.getY()); i++){
                if (!(chessboard[destination.getX()][i] instanceof EmptySlotComponent)) {
                    sum ++;
                }
            }
        } else if (this.getChessboardPoint().getY() == destination.getY()) {
            for (int i = Math.min(this.getChessboardPoint().getX(),destination.getX()); i <= Math.max(this.getChessboardPoint().getX(),destination.getX()); i++){
                if (!(chessboard[i][destination.getY()] instanceof EmptySlotComponent)) {
                    sum ++;
                }
            }
        }
        if (destinationChess.isReversal()) {
            if (destinationChess.getChessColor() == this.getChessColor()) {
                return false;
            }
        }
        return sum == 3 && !(destinationChess instanceof EmptySlotComponent);
    }
}