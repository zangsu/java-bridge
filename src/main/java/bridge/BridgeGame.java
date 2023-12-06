package bridge;

import bridge.domain.gameState.GameState;
import bridge.domain.bridge.BridgeLocation;
import bridge.domain.bridge.BridgeMaker;
import bridge.exception.handler.RetryHandler;
import bridge.view.InputView;
import bridge.view.OutputView;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스 메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 */
public class BridgeGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private final BridgeMaker bridgeMaker;

    public BridgeGame(final BridgeMaker bridgeMaker) {
        this.bridgeMaker = bridgeMaker;
    }

    public void run() {
        outputView.startGame();
        GameState gameState = initGame();
        playGame(gameState);
        outputView.printResult(gameState);
    }

    private void playGame(final GameState gameState) {
        while (!gameState.isFinished()) {
            boolean moveSuccess = move(gameState);
            outputView.printMap(gameState);
            if (moveSuccess || retry(gameState)) {
                continue;
            }
            break;
        }
    }

    private GameState initGame() {
        List<String> bridge = RetryHandler.getOrRetry(this::getMakeBridge);
        return new GameState(bridge);
    }

    private List<String> getMakeBridge() {
        int bridgeSize = inputView.readBridgeSize();
        return bridgeMaker.makeBridge(bridgeSize);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    private boolean move(final GameState gameState) {
        String input = inputView.readMoving();
        BridgeLocation moving = BridgeLocation.from(input);
        return gameState.move(moving);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     *
     * @return
     */
    private boolean retry(final GameState gameState) {
        if (inputView.restart()) {
            gameState.retry();
            return true;
        }
        return false;
    }
}