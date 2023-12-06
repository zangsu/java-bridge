package bridge.view;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RegexTest {

    @Nested
    @DisplayName("입력_패턴_테스트")
    class 입력_패턴_테스트{
        @ParameterizedTest
        @ValueSource(strings = {"U", "D"})
        @DisplayName("")
        void 정상_입력_테스트(String input){
            boolean matches = InputView.MOVING_INPUT_PATTERN.matcher(input).matches();
            Assertions.assertThat(matches).isTrue();
        }

        @ParameterizedTest
        @ValueSource(strings = {"A", "1", "k", "ㄱ", "가"})
        @DisplayName("")
        void 다른_문자_입력_테스트(String input){
            boolean matches = InputView.MOVING_INPUT_PATTERN.matcher(input).matches();
            Assertions.assertThat(matches).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {"UU", "DD", "UD", "DU"})
        @DisplayName("")
        void 정상_문자_중복_입력_테스트(String input){
            boolean matches = InputView.MOVING_INPUT_PATTERN.matcher(input).matches();
            Assertions.assertThat(matches).isFalse();
        }
    }
}