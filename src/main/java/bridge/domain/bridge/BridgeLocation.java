package bridge.domain.bridge;

import bridge.exception.BridgeException;
import java.util.Arrays;

public enum BridgeLocation {
    UP("U"),
    DOWN("D"),
    ;

    private final String inputMessage;

    BridgeLocation(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public static BridgeLocation from(String inputMessage) {
        return Arrays.stream(values())
                .filter(location -> location.inputMessage.equals(inputMessage))
                .findFirst()
                .orElseThrow(BridgeException.INVALID_INPUT_FORMAT::makeException);
    }

    public String getMessage() {
        return inputMessage;
    }
}
