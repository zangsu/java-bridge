package bridge;

import bridge.domain.GameState;

/**
 * 다리 건너기 게임을 관리하는 클래스
 *  메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 */
public class BridgeGame {

    public void run(){
        /*GameState gameState = new GameState();
        while(!gameState.isFinished()){
            boolean moveSuccess = move(gameState);
            if(!moveSuccess){
                retry(gameState);
            }
        }
        outputView.printResult(gameState);*/
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move() {
        //사용자 입력 받는다.
        /*return gameState.move(입력);*/
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        //사용자 입력 받는다.
        /*if(재시도){
            gameState.retry();
        }*/
    }
}
