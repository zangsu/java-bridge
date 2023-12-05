package bridge.domain;

import bridge.exception.BridgeException;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    //todo Bridge 클래스로 래핑
    private final List<String> bridge;
    private List<String> userPath;
    private int currentPosition;
    private int roundNum;

    public GameState(List<String> bridge) {
        this.bridge = bridge;
        initState();
    }

    private void initState() {
        this.currentPosition = 0;
        this.userPath = new ArrayList<>();
    }

    public boolean isFinished() {
        return currentPosition == bridge.size();
    }

    public boolean move(String input) {
        if (isFinished()) {
            throw BridgeException.GAME_FINISHED.makeException();
        }
        userPath.add(input);
        if (bridge.get(currentPosition).equals(input)) {
            currentPosition++;
            return true;
        }
        return false;
    }

    public void retry() {
        initState();
        this.roundNum++;
    }

    public List<String> getBridge() {
        return bridge;
    }

    public List<String> getUserPath() {
        return userPath;
    }
}