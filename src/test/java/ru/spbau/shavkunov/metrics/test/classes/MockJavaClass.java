package ru.spbau.shavkunov.metrics.test.classes;

import org.jetbrains.annotations.NotNull;

public class MockJavaClass {
    private int value;
    public static final String example = "bla";

    public MockJavaClass(int value) {
        this.value = value;
    }

    public MockJavaClass() {
    }

    private void testLongNameOfMethodExample() {}

    public int publicMethodTest(@NotNull String parameter) {
        Integer localVar = 4;

        return localVar;
    }
}