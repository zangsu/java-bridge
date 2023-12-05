package bridge.domain.bridge;

import bridge.exception.BridgeException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    //상수는 private? public?
    public static final int MIN_BRIDGE_SIZE = 3;
    public static final int MAX_BRIDGE_SIZE = 20;
    public static final int DOWN_VALUE = 0;
    public static final int UP_VALUE = 1;
    public static final String DOWN = "D";
    public static final String UP = "U";
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(final int size) {
        validateSize(size);
        return IntStream.range(0, size)
                .map(i -> bridgeNumberGenerator.generate())
                .mapToObj(this::convertToBridgeShape)
                .collect(Collectors.toList());
    }

    private String convertToBridgeShape(final int generatedValue) {
        if (generatedValue == DOWN_VALUE) {
            return DOWN;
        }
        if (generatedValue == UP_VALUE) {
            return UP;
        }
        throw BridgeException.INVALID_BRIDGE_SHAPE.makeException();
    }

    private void validateSize(final int size) {
        if (size < MIN_BRIDGE_SIZE || size > MAX_BRIDGE_SIZE) {
            throw BridgeException.INVALID_BRIDGE_SIZE.makeException();
        }
    }
}
