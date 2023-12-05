package bridge.domain.bridge;


import static org.assertj.core.api.Assertions.*;

import bridge.exception.BridgeException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class BridgeMakerTest {

    @Nested
    @DisplayName("다리 생성 테스트")
    static class 다리_생성_테스트 {

        @ParameterizedTest
        @MethodSource("bridgeSizeAndExpected")
        @DisplayName("")
        void 다리_생성_확인(List<Integer> generatedValus, int size, List<String> expected) {
            BridgeMaker bridgeMaker = new BridgeMaker(new FixedBridgeNumberGenerator(generatedValus));
            List<String> actual = bridgeMaker.makeBridge(size);
            assertThat(expected).isEqualTo(actual);
        }

        static Stream<Arguments> bridgeSizeAndExpected() {
            return Stream.of(
                    Arguments.of(
                            List.of(0, 0, 1), 3, List.of("D", "D", "U")
                    ),
                    Arguments.of(
                            List.of(1, 1, 1), 3, List.of("U", "U", "U")
                    ),
                    Arguments.of(
                            List.of(0, 0, 1, 1, 0), 5, List.of("D", "D", "U", "U", "D")
                    )
            );
        }

        @ParameterizedTest
        @CsvSource(value = {"2", "21"})
        @DisplayName("다리의 길이가 3보다 작거나 20보다 크면 예외 발생")
        void 다리의_길이가_3보다_작거나_20보다_크면_예외_발생(int size) {
            BridgeMaker bridgeMaker = new BridgeMaker(new FixedBridgeNumberGenerator(Arrays.asList(0, 0, 1)));
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> bridgeMaker.makeBridge(size))
                    .withMessage(BridgeException.INVALID_BRIDGE_SIZE.getMessage());
        }
    }
}