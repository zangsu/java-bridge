package bridge.view;

import bridge.domain.gameState.GameState;
import bridge.domain.bridge.BridgeLocation;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public static final String ERROR_PREFIX = "[ERROR] ";
    public static final String MATCH = "O";
    public static final String NOT_MATCH = "X";
    public static final String EMPTY = " ";
    public static final String SUCCESS = "성공";
    public static final String FAIL = "실패";

    public void startGame() {
        System.out.println("다리 건너기 게임을 시작합니다.");
        newLine();
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(final GameState gameState) {
        List<String> bridge = gameState.getBridge();
        List<String> userPath = gameState.getUserPath();
        printBridge(BridgeLocation.UP.getMessage(), bridge, userPath);
        printBridge(BridgeLocation.DOWN.getMessage(), bridge, userPath);
        newLine();
    }

    private void printBridge(final String location,
                             final List<String> bridge,
                             final List<String> userPath) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < userPath.size(); i++) {
            result.add(getLocationResult(location, userPath.get(i), bridge.get(i)));
        }
        System.out.println("[ " + String.join(" | ", result) + " ]");
    }

    private String getLocationResult(final String location,
                                     final String userLocation,
                                     final String bridgeLocation) {
        if (!location.equals(userLocation)) {
            return EMPTY;
        }
        if (userLocation.equals(bridgeLocation)) {
            return MATCH;
        }
        return NOT_MATCH;
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(final GameState gameResult) {
        System.out.println("최종 게임 결과");
        printMap(gameResult);
        System.out.println("게임 성공 여부: " + getSuccess(gameResult));
        System.out.println("총 시도한 횟수: " + gameResult.getTryRound());
    }

    private String getSuccess(final GameState gameResult) {
        if (gameResult.isSuccess()) {
            return SUCCESS;
        }
        return FAIL;
    }

    public void printException(IllegalArgumentException e) {
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void newLine() {
        System.out.println();
    }
}