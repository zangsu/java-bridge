package bridge.domain.gameState;

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
    private final TryNumber tryNumber;

    public GameState(final List<String> bridge) {
        this.bridge = new Bridge(bridge);
        this.userPath = new ArrayList<>();
        this.tryNumber = new TryNumber();
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
        tryNumber.nextTry();
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

    public int getTryRound() {
        return tryNumber.getTryNum();
    }
}