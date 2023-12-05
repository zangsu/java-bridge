package bridge.view;

import bridge.exception.BridgeException;
import bridge.exception.handler.RetryHandler;
import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Pattern;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    public static final Pattern MOVING_INPUT_PATTERN = Pattern.compile("[UD]");
    public static final String RESTART = "R";
    public static final String QUIT = "Q";

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println("다리의 길이를 입력해주세요.");
        return RetryHandler.getOrRetry(this::getInteger);
    }

    private int getInteger() {
        String input = Console.readLine();
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw BridgeException.INVALID_NUMBER_FORMAT.makeException();
        }
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        return RetryHandler.getOrRetry(this::getMovingInput);
    }

    private String getMovingInput() {
        String input = Console.readLine();
        if(MOVING_INPUT_PATTERN.matcher(input).matches()){
            return input;
        }
        throw BridgeException.INVALID_INPUT_FORMAT.makeException();
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public boolean restart() {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        return RetryHandler.getOrRetry(() -> getBoolean(RESTART, QUIT));
    }

    private boolean getBoolean(String trueInput, String falseInput){
        String input = Console.readLine();
        if(trueInput.equals(input)){
            return true;
        }
        if(falseInput.equals(input)){
            return false;
        }
        throw BridgeException.INVALID_INPUT_FORMAT.makeException();
    }
}
