package ru.spbau.shavkunov.metrics;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static ru.spbau.shavkunov.metrics.Constants.JAVA_EXTENSION;

@Data
public class ProjectStats {
    private int methodsAmount;
    private int lengthOfAllMethods;
    private double averageMethodNameLength;

    private int fieldsAmount;
    private int lengthOfAllLocalVariables;
    private int localVarsAmount;
    private double averageLocalVarsNameLength;

    public ProjectStats(Path pathToSource) throws IOException, WrongExtensionException {
        List<Path> javaClasses = Files.walk(pathToSource)
                                    .filter(Files::isRegularFile)
                                    .filter(path -> path.endsWith(JAVA_EXTENSION))
                                    .collect(Collectors.toList());

        handleClasses(javaClasses);
    }

    private void handleClasses(@NotNull List<Path> classes) throws WrongExtensionException, FileNotFoundException {
        for (Path javaClass : classes) {
            JavaClassStats stats = StatsCollector.getStats(javaClass.toFile());
            handleStats(stats);
        }

        evaluateAverageQuantities();
    }

    private void handleStats(@NotNull JavaClassStats stats) {
        methodsAmount += stats.getMethodsAmount();
        lengthOfAllMethods += stats.getSumLengthsOfMethods();
        fieldsAmount += stats.getFieldsAmount();
        lengthOfAllLocalVariables += stats.getSumLengthsOfLocalVars();
    }

    private void evaluateAverageQuantities() {
        averageLocalVarsNameLength = lengthOfAllLocalVariables / localVarsAmount;
        averageMethodNameLength = lengthOfAllMethods / methodsAmount;
    }
}
