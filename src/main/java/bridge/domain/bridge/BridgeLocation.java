package bridge.domain.bridge;

import bridge.exception.BridgeException;
import java.util.Arrays;

// todo 의미 없는 enum 같기도 해서 고민중
public enum BridgeLocation {
    UP("U", 1),
    DOWN("D", 0),
    ;

    private final String inputMessage;
    private final int inputNumber;

    BridgeLocation(String inputMessage, int inputNumber) {
        this.inputMessage = inputMessage;
        this.inputNumber = inputNumber;
    }

    public static BridgeLocation from(String inputMessage) {
        return Arrays.stream(values())
                .filter(location -> location.inputMessage.equals(inputMessage))
                .findFirst()
                .orElseThrow(BridgeException.INVALID_INPUT_FORMAT::makeException);
    }

    public static BridgeLocation from(int generatedNumber) {
        return Arrays.stream(values())
                .filter(location -> location.inputNumber == generatedNumber)
                .findFirst()
                .orElseThrow(BridgeException.INVALID_BRIDGE_SHAPE::makeException);
    }

    public String getMessage() {
        return inputMessage;
    }
}
