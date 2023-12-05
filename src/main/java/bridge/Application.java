package bridge;

import bridge.domain.bridge.BridgeMaker;
import bridge.domain.bridge.BridgeRandomNumberGenerator;

public class Application {

    public static void main(String[] args) {
        BridgeMaker randomBridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        BridgeGame bridgeGame = new BridgeGame(randomBridgeMaker);
        bridgeGame.run();
    }
}
