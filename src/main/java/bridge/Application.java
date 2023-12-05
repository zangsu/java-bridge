package bridge;

import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.BridgeRandomNumberGenerator;

public class Application {

    public static void main(String[] args) {
        BridgeGame bridgeGame = new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator()));
        bridgeGame.run();
    }
}
