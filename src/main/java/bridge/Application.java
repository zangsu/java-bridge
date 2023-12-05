package bridge;

public class Application {

    public static void main(String[] args) {
        BridgeGame bridgeGame = new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator()));
        bridgeGame.run();
    }
}
