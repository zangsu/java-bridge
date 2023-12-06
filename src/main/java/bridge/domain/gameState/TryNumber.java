package bridge.domain.gameState;

public class TryNumber {
    private int roundNum;

    public TryNumber() {
        this.roundNum = 1;
    }

    public void nextTry() {
        roundNum++;
    }

    public int getTryNum() {
        return roundNum;
    }
}
