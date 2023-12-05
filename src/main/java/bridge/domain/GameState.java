package bridge.domain;

import bridge.domain.bridge.Bridge;
import bridge.domain.bridge.BridgeLocation;
import bridge.exception.BridgeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameState {
    private final Bridge bridge;
    private final List<BridgeLocation> userPath;
    private int currentPosition;
    private int roundNum;

    public GameState(final List<String> bridge) {
        this.bridge = new Bridge(bridge);
        this.userPath = new ArrayList<>();
        this.roundNum = 1;
        initState();
    }

    private void initState() {
        this.currentPosition = 0;
        this.userPath.clear();
    }

    public boolean isFinished() {
        return bridge.isEndOfBridge(currentPosition);
    }

    public boolean move(final BridgeLocation input) {
        validateGameContinue();
        userPath.add(input);
        if (bridge.isMovable(currentPosition, input)) {
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
        return bridge.getBridgeLocations();
    }

    public List<String> getUserPath() {
        return userPath.stream()
                .map(BridgeLocation::getMessage)
                .collect(Collectors.toList());
    }

    public boolean isSuccess() {
        return bridge.isEndOfBridge(currentPosition);
    }

    public int getRoundNum() {
        return roundNum;
    }
}