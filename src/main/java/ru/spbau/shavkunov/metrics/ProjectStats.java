package ru.spbau.shavkunov.metrics;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import ru.spbau.shavkunov.metrics.exceptions.WrongExtensionException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static ru.spbau.shavkunov.metrics.Constants.JAVA_EXTENSION;

@Data
public class ProjectStats {
    private int methodsAmount;
    private int lengthOfAllMethods;
    private double averageMethodNameLength;

    private int localVarsAmount;
    private int lengthOfAllLocalVariables;
    private double averageLocalVarsNameLength;

    private int fieldsAmount;
    private int lengthOfAllFields;
    private double averageFieldLength;

    public static @NotNull List<Path> getJavaClassesFromSource(@NotNull Path pathToSource) throws IOException {
        List<Path> javaClasses = Files.walk(pathToSource)
                                      .filter(Files::isRegularFile)
                                      .filter(path -> !path.endsWith(JAVA_EXTENSION))
                                      .collect(Collectors.toList());

        return javaClasses;
    }

    public ProjectStats(Path pathToSource) throws IOException, WrongExtensionException {
        List<Path> javaClasses = getJavaClassesFromSource(pathToSource);

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

        localVarsAmount += stats.getLocalVarsAmount();
        lengthOfAllLocalVariables += stats.getSumLengthsOfLocalVars();

        fieldsAmount += stats.getFieldsAmount();
        lengthOfAllFields += stats.getSumLengthsOfFields();
    }

    private void evaluateAverageQuantities() {
        averageLocalVarsNameLength = lengthOfAllLocalVariables / localVarsAmount;
        averageMethodNameLength = lengthOfAllMethods / methodsAmount;
        averageFieldLength = lengthOfAllFields / fieldsAmount;
    }
}