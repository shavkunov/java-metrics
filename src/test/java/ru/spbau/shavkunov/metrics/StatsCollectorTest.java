package ru.spbau.shavkunov.metrics;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class StatsCollectorTest {
    @Test
    public void statsTest() throws Exception {
        File file = new File("./src/test/java/ru/spbau/shavkunov/metrics/test/MockJavaClass.java");

        JavaClassStats stats = StatsCollector.getStats(file);
        assertEquals(2, stats.getFieldsAmount());
        assertEquals(2, stats.getMethodsAmount());
        assertEquals(1, stats.getLocalVarsAmount());
    }

    @Test
    public void projectStatsTest() throws Exception {
        File file = new File("./src/test/java/ru/spbau/shavkunov/metrics/test/MockJavaClass.java");

        ProjectStats stats = new ProjectStats(file.toPath());
        assertEquals(2, stats.getFieldsAmount());
        assertEquals(2, stats.getMethodsAmount());
        assertEquals(1, stats.getLocalVarsAmount());
        assertEquals(8, stats.getAverageLocalVarsNameLength(), 0);
        assertEquals(21, stats.getAverageMethodNameLength(), 0);
    }
}