package bridge.domain;

import java.util.List;

public class GameState {
    //todo Bridge 클래스로 래핑
    private final List<String> bridge;

    public GameState(List<String> bridge) {
        this.bridge = bridge;
    }
}
