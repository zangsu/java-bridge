package bridge.exception;

public enum BridgeException {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    NO_SUCH_MENU("잘못된 메뉴 입력입니다."),
    INVALID_BRIDGE_SIZE("다리의 길이는 3이상 20이하의 숫자여야 합니다."),
    INVALID_BRIDGE_SHAPE("다리 생성 인자는 0 또는 1이어야 합니다."),
    GAME_FINISHED("게임이 종료되었습니다.");

    private final String message;
    private final IllegalArgumentException exception;

    BridgeException(String message) {
        this.message = message;
        this.exception = new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException makeException(){
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
