package bridge.domain;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameStateTest {

    @DisplayName("생성 초기 상태")
    void 게임_초기_상태_테스트() {
        // given
        List<String> generatedBridge = List.of("D", "D", "U");

        // when
        GameState gameState = new GameState(generatedBridge);

        // then
        assertThat(gameState.isFinished()).isFalse();
        assertThat(gameState.getRoundNum()).isEqualTo(1);
        assertThat(gameState.isSuccess()).isFalse();
    }

    @Nested
    @DisplayName("다리 이동 테스트")
    class 다리_이동_테스트{
        private GameState gameState;
        @BeforeEach
        void setUp() {
            gameState = new GameState(List.of("D", "D", "U"));
        }

        @Test
        @DisplayName("다리 이동 성공 테스트")
        void 이동_성공_테스트() {
            // given
            List<String> moveInput = List.of("D", "D", "U");

            // when
            moveInput.forEach(move -> assertThat(gameState.move(move)).isTrue());

            // then
            assertThat(gameState.isFinished()).isTrue();
            assertThat(gameState.getRoundNum()).isEqualTo(1);
            assertThat(gameState.isSuccess()).isTrue();
            assertThat(gameState.getUserPath()).isEqualTo(moveInput);
        }

        @Test
        @DisplayName("다리 이동 실패 테스트")
        void 이동_실패_테스트() {
            // given
            // when
            assertThat(gameState.move("U")).isFalse();

            // then
            assertThat(gameState.isFinished()).isFalse();
            assertThat(gameState.getRoundNum()).isEqualTo(1);
            assertThat(gameState.isSuccess()).isFalse();
        }
    }

    @Nested
    @DisplayName("재시작 테스트")
    class 재시작_테스트{

        @Test
        @DisplayName("재시작 상태 테스트")
        void 재시작_테스트() {
            // given
            List<String> generatedBridge = List.of("D", "D", "U");
            GameState gameState = new GameState(generatedBridge);
            gameState.move("D");

            // when
            gameState.retry();
            gameState.retry();
            gameState.retry();

            // then
            assertThat(gameState.isFinished()).isFalse();
            assertThat(gameState.getRoundNum()).isEqualTo(4);
            assertThat(gameState.isSuccess()).isFalse();
            assertThat(gameState.getBridge()).isEqualTo(generatedBridge);
            assertThat(gameState.getUserPath()).isEqualTo(List.of());
        }
    }
}