package bridge.domain.bridge;

import bridge.exception.BridgeException;
import java.util.List;

public class Bridge {
    //todo XXXInfo 라는 네이밍은 좋지 않다고 했는데
    private final List<String> bridgeInfo;

    public Bridge(List<String> bridge) {
        this.bridgeInfo = bridge;
    }

    public boolean isEndOfBridge(int position) {
        return position == bridgeInfo.size();
    }

    public boolean movable(int position, String input) {
        validatePosition(position);
        return bridgeInfo.get(position).equals(input);
    }

    private void validatePosition(int position) {
        if(position < 0 || position >= bridgeInfo.size()) {
            throw BridgeException.INVALID_POSITION.makeException();
        }
    }

    public List<String> getBridgeInfo() {
        return bridgeInfo;
    }
}
