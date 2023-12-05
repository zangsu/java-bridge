package bridge.exception.handler;

import bridge.exception.BridgeException;
import bridge.view.OutputView;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler{
    private static final OutputView outputView = new OutputView();

    public static <T> T getOrRetry(Supplier<T> supplier){
        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                outputView.printException(e);
            } finally {
                //outputView.newLine();
            }
        }
    }
    public static <T> T getOrConditionalRetry(Supplier<T> supplier, BridgeException ... expectedExceptions){
        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                checkExpectedException(expectedExceptions, e);
            } finally {
                //outputView.newLine();
            }
        }
    }
    public static void runOrRetry(Runnable runnable) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            } finally {
                //outputView.newLine();
            }
        }
    }
    public static void runOrConditionalRetry(Runnable runnable, BridgeException... expectedExceptions) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                checkExpectedException(expectedExceptions, e);
            } finally {
                //outputView.newLine();
            }
        }
    }

    private static void checkExpectedException(BridgeException[] expectedExceptions, IllegalArgumentException e) {
        if(!isExpectedException(expectedExceptions, e)){
            throw e;
        }
        outputView.printException(e);
    }

    private static boolean isExpectedException(BridgeException[] exceptions, IllegalArgumentException e) {
        return Arrays.stream(exceptions)
                .map(BridgeException::makeException)
                .anyMatch(e::equals);
    }
}
