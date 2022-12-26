package chessComponent;

import controller.ClickController;
import model.ChessColor;
import model.ChessboardPoint;

import java.awt.*;

public class SoldierChessComponent extends ChessComponent {

    public SoldierChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        if (this.getChessColor() == ChessColor.RED) {
            name = "兵";
        } else {
            name = "卒";
        }
        priority = 0;
        points = 1;
    }
    @Override
    public boolean canMoveTo(SquareComponent[][] chessboard, ChessboardPoint destination) {
        SquareComponent destinationChess = chessboard[destination.getX()][destination.getY()];
        if (!destinationChess.isReversal()) {
            //没翻开且非空棋子不能走
            if (!(destinationChess instanceof EmptySlotComponent)) {
                return false;
            }
        }
        return (Math.abs(this.getChessboardPoint().getX() - destination.getX()) + Math.abs(this.getChessboardPoint().getY() - destination.getY()) == 1) && ((destinationChess.isReversal && (destinationChess.getPriority() == 6 || destinationChess.getPriority() == 0)) || destinationChess instanceof EmptySlotComponent) && destinationChess.getChessColor() != this.getChessColor();
    }
}
