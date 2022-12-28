package ai;

import chessComponent.SquareComponent;

public abstract class AI extends Thread{
    protected SquareComponent squareComponent;

    public AI(SquareComponent squareComponent) {
        this.squareComponent = squareComponent;
    }

    public abstract void init(boolean redBlack);
    public abstract void playChessEasy();
    public abstract void playChessMedium();
    public abstract void playChessHard();
}
