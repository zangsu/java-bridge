package bridge.domain.bridge;

import static org.junit.jupiter.api.Assertions.*;

import bridge.exception.BridgeException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BridgeTest {

    @Nested
    @DisplayName("다리 생성 테스트")
    static class 다리_생성_테스트 {

        @Test
        @DisplayName("정상 생성 테스트")
        void 정상_생성() {
            Assertions.assertThatNoException()
                    .isThrownBy(() -> new Bridge(List.of("D", "D", "U")));
        }

        @Test
        @DisplayName("다리의 길이가 3보다 작으면 예외 발생")
        void 다리의_길이가_3보다_작으면_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Bridge(List.of("D", "D")))
                    .withMessage(BridgeException.INVALID_BRIDGE_SIZE.getMessage());
        }

        @Test
        @DisplayName("다리의 길이가 20보다 크면 예외 발생")
        void 다리의_길이가_20보다_크면_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Bridge(List.of("D", "D", "D", "D", "D",
                            "D", "D", "D", "D", "D",
                            "D", "D", "D", "D", "D",
                            "D", "D", "D", "D", "D",
                            "D")))
                    .withMessage(BridgeException.INVALID_BRIDGE_SIZE.getMessage());
        }

        @Test
        @DisplayName("다리의 모양이 U, D가 아니면 예외 발생")
        void 다리의_모양이_U_D가_아니면_예외_발생() {
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> new Bridge(List.of("D", "D", "A")))
                    .withMessage(BridgeException.INVALID_INPUT_FORMAT.getMessage());
        }
    }

    @Nested
    @DisplayName("다리 기능 테스트")
    class 기능_테스트 {

        @Test
        @DisplayName("다리 끝인지 확인")
        void 다리_끝인지_확인() {
            Bridge bridge = new Bridge(List.of("D", "D", "U"));
            assertFalse(bridge.isEndOfBridge(0));
            assertFalse(bridge.isEndOfBridge(1));
            assertFalse(bridge.isEndOfBridge(2));
            assertTrue(bridge.isEndOfBridge(3));
        }

        @Test
        @DisplayName("이동 가능한지 확인")
        void 이동_가능한지_확인() {
            Bridge bridge = new Bridge(List.of("D", "D", "U"));
            assertTrue(bridge.movable(0, BridgeLocation.DOWN));
            assertTrue(bridge.movable(1, BridgeLocation.DOWN));
            assertTrue(bridge.movable(2, BridgeLocation.UP));
            assertFalse(bridge.movable(0, BridgeLocation.UP));
            assertFalse(bridge.movable(1, BridgeLocation.UP));
            assertFalse(bridge.movable(2, BridgeLocation.DOWN));
        }

        @Test
        @DisplayName("잘못된 위치로 이동하면 예외 발생")
        void 잘못된_위치로_이동하면_예외_발생() {
            Bridge bridge = new Bridge(List.of("D", "D", "U"));
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> bridge.movable(-1, BridgeLocation.DOWN))
                    .withMessage(BridgeException.INVALID_POSITION.getMessage());
            Assertions.assertThatIllegalArgumentException()
                    .isThrownBy(() -> bridge.movable(3, BridgeLocation.DOWN))
                    .withMessage(BridgeException.INVALID_POSITION.getMessage());
        }
    }

}