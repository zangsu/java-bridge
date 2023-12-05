package bridge.view;

import bridge.domain.GameState;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public static final String ERROR_PREFIX = "[ERROR] ";

    public void startGame() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(GameState gameState) {
        List<String> bridge = gameState.getBridge();
        List<String> userPath = gameState.getUserPath();
        printBridge("U", bridge, userPath);
        printBridge("D", bridge, userPath);
    }

    private void printBridge(String location,List<String> bridge, List<String> userPath) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < userPath.size(); i++) {
            result.add(getResult(location, userPath.get(i), bridge.get(i)));
        }
        System.out.println("[ " + String.join(" | ", result) + " ]");
    }

    private String getResult(String location, String userLocation, String bridgeLocation) {
        if(!location.equals(userLocation)){
            return " ";
        }
        if(userLocation.equals(bridgeLocation)){
            return "O";
        }
        return "X";
    }

    private String filterLocation(String location, String s) {
        if(location.equals(s)){
            return s;
        }
        return " ";
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult() {
    }

    public void printException(IllegalArgumentException e){
        System.out.println(ERROR_PREFIX + e.getMessage());
    }

    public void newLine() {
        System.out.println();
    }
}
