package com.example.springtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class JunitPracticesTest {

    @Test
    @DisplayName("Assert Equals 메소드 테스트")
    void assert_equals_test() {
        String expect = "Something";
        String actual = "Something";

        assertEquals(expect, actual);
    }

    @Test
    @DisplayName("Assert False 메소드 테스트")
    void assertNotEqulasTest() {
        String expect = "Something";
        String actual = "Hello";

        assertNotEquals(expect, actual);
    }

    @Test
    @DisplayName("Assert True 메소드 테스트")
    void assertTrueTest() {
        Integer a = 10;
        Integer b = 10;

        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("Assert False 메소드 테스트")
    void assertFalseTest() {
        Integer a = 10;
        Integer b = 20;

        assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("Assert Throws 메소드 테스트")
    void assertThrowsTest() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("임의로 발생시킨 에러");
        });
    }

    @Test
    @DisplayName("Assert NotNull 메소드 테스트")
    void assertNotNullTest() {
        String value = "Hello";

        assertNotNull(value);
    }

    @Test
    @DisplayName("Assert Null 메소드 테스트")
    void assertNullTest() {
        String value = null;
        assertNull(value);
    }

    @Test
    @DisplayName("Assert Iterable 메소드 테스트")
    void assertIterableEqualsTest() {
        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        assertIterableEquals(list1, list2);
    }

    @Test
    @DisplayName("Assert All 메소드 테스트")
    void assertAllTest() {
        String expect = "Something";
        String actual = "Something";

        List<Integer> list1 = List.of(1, 2);
        List<Integer> list2 = List.of(1, 2);

        assertAll(
                "Assert All",
                List.of(
                        () -> { assertEquals(expect, actual); },
                        () -> { assertIterableEquals(list1, list2); }
                )
        );
    }

}
