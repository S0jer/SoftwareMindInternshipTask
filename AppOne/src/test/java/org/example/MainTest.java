package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void shouldCheckIllegalArgumentExceptionOfInput() {
        String expectedMessage = "Wrong number of arguments!";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> Main.main(new String[]{"One", "One", "One"}));
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}