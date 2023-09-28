package com.example.springtest.calculator.domain;

import com.example.springtest.calculator.exception.ZeroDivisionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyCalculatorTest {

    @Test
    @DisplayName("MyCalculator 덧셈 테스트")
    void addTest() {
        // AAA 패턴

        // Arrange - 준비
        MyCalculator myCalculator = new MyCalculator();

        // Act - 행동
        myCalculator.add(10.0);

        // Assert - 단언/검증
        Assertions.assertEquals(10.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 뺄셈 테스트")
    void minusTest() {
        // GWT 패턴

        // Given - 준비 - Arrange
        MyCalculator myCalculator = new MyCalculator(10.0);

        // When - 행동/연산 - Act
        myCalculator.minus(5.0);

        // Then - 검증/비교/단언 - Assert
        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 곱셈 테스트")
    void multiply() {
        MyCalculator myCalculator = new MyCalculator(2.0);

        myCalculator.multiply(2.0);

        Assertions.assertEquals(4.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 나눗셈 테스트")
    void divide() {
        MyCalculator myCalculator = new MyCalculator(10.0);

        myCalculator.divide(2.0);

        Assertions.assertEquals(5.0, myCalculator.getResult());
    }

    @Test
    @DisplayName("MyCalculator 사칙연산 테스트")
    void comlicatedCalculateTest() {
        MyCalculator myCalculator = new MyCalculator(0.0);

        Double result = myCalculator
                .add(10.0)
                .minus(4.0)
                .multiply(2.0)
                .divide(3.0)
                .getResult();

        Assertions.assertEquals(4.0, result);
    }

    @Test
    @DisplayName("MyCalculator 0으로 나누었을 때에는 ZeroDivisionException을 발생시켜야 함.")
    void dividedZeroTest() {
        MyCalculator myCalculator = new MyCalculator(10.0);

        Assertions.assertThrows(ZeroDivisionException.class, () -> {
            myCalculator.divide(0.0);
        });
    }

}
