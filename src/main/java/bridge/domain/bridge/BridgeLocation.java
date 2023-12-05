package bridge.domain.bridge;

import bridge.exception.BridgeException;
import java.util.Map;
import java.util.Optional;

// todo 의미 없는 enum 같기도 해서 고민중
public enum BridgeLocation {
    UP("U"),
    DOWN("D"),
    ;

    private static final Map<String, BridgeLocation> locations = Map.of(
            "U", UP,
            "D", DOWN
    );
    private final String inputMessage;

    BridgeLocation(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public static BridgeLocation from(String inputMessage) {
        return Optional.ofNullable(locations.get(inputMessage))
                .orElseThrow(BridgeException.INVALID_INPUT_FORMAT::makeException);
    }
}
