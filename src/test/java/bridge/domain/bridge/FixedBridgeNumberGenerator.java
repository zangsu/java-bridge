package bridge.domain.bridge;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class FixedBridgeNumberGenerator implements BridgeNumberGenerator {
    private final Queue<Integer> numbers;

    public FixedBridgeNumberGenerator(Collection<Integer> numbers) {
        this.numbers = new LinkedList<>(numbers);
    }

    @Override
    public int generate() {
        if(numbers.size() == 1){
            return numbers.peek();
        }
        return numbers.poll();
    }
}
