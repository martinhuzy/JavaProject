package ai;

import chessComponent.SquareComponent;

public class DefaultAI extends AI{
    public DefaultAI(SquareComponent squareComponent) {
        super(squareComponent);
    }

    @Override
    public void init(boolean redBlack) {

    }

    @Override
    //简单：能翻就翻，翻完全随机
    public void playChessEasy() {

    }

    @Override
    //普通：能翻就翻，翻完能吃就吃，不能吃就随机
    public void playChessMedium() {

    }

    @Override
    //困难：能吃就吃，不能吃能翻就翻，翻完就随机
    public void playChessHard() {

    }

}
