package bridge.domain.bridge;

import bridge.exception.BridgeException;
import java.util.List;
import java.util.stream.Collectors;

public class Bridge {
    //todo XXXInfo 라는 네이밍은 좋지 않다고 했는데
    private final List<BridgeLocation> bridgeInfo;

    public Bridge(List<String> bridge) {
        validateBridge(bridge);
        this.bridgeInfo = bridge.stream()
                .map(BridgeLocation::from)
                .collect(Collectors.toList());
    }

    private void validateBridge(List<String> bridge) {
        if(bridge.size() < BridgeMaker.MIN_BRIDGE_SIZE
                || bridge.size() > BridgeMaker.MAX_BRIDGE_SIZE) {
            throw BridgeException.INVALID_BRIDGE_SIZE.makeException();
        }
    }

    public boolean isEndOfBridge(int position) {
        return position == bridgeInfo.size();
    }

    public boolean isMovable(int position, BridgeLocation input) {
        validatePosition(position);
        return bridgeInfo.get(position).equals(input);
    }

    private void validatePosition(int position) {
        if(position < 0 || position >= bridgeInfo.size()) {
            throw BridgeException.INVALID_POSITION.makeException();
        }
    }

    public List<String> getBridgeInfo() {
        return bridgeInfo.stream()
                .map(BridgeLocation::getMessage)
                .collect(Collectors.toList());
    }
}
