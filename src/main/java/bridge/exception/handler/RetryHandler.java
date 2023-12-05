package bridge.exception.handler;

import bridge.view.OutputView;
import java.util.function.Supplier;

public class RetryHandler {
    private static final OutputView outputView = new OutputView();

    public static <T> T getOrRetry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printException(e);
            }
        }
    }
}
