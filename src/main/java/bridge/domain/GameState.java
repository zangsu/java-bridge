package bridge.domain;

import bridge.domain.bridge.Bridge;
import bridge.exception.BridgeException;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Bridge bridge;
    private List<String> userPath;
    private int currentPosition;
    private int roundNum;

    public GameState(List<String> bridge) {
        this.bridge = new Bridge(bridge);
        this.roundNum = 1;
        initState();
    }

    private void initState() {
        this.currentPosition = 0;
        this.userPath = new ArrayList<>();
    }

    public boolean isFinished() {
        return bridge.isEndOfBridge(currentPosition);
    }

    public boolean move(String input) {
        validateGameContinue();
        userPath.add(input);
        if (bridge.movable(currentPosition,input)) {
            currentPosition++;
            return true;
        }
        return false;
    }

    private void validateGameContinue() {
        if (isFinished()) {
            throw BridgeException.GAME_FINISHED.makeException();
        }
    }

    public void retry() {
        initState();
        this.roundNum++;
    }

    public List<String> getBridge() {
        return bridge.getBridgeInfo();
    }

    public List<String> getUserPath() {
        return userPath;
    }

    public boolean isSuccess() {
        return bridge.isEndOfBridge(currentPosition);
    }

    public int getRoundNum() {
        return roundNum;
    }
}