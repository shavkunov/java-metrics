package ru.spbau.shavkunov.metrics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.jetbrains.annotations.NotNull;
import ru.spbau.shavkunov.metrics.visitors.StatsVisitor;

import java.io.File;
import java.io.FileNotFoundException;

import static ru.spbau.shavkunov.metrics.Constants.JAVA_EXTENSION;

public class StatsCollector {
    public static @NotNull JavaClassStats getStats(@NotNull File file) throws WrongExtensionException, FileNotFoundException {
        if (!file.getPath().endsWith(JAVA_EXTENSION)) {
            throw new WrongExtensionException();
        }

        CompilationUnit fileAst = JavaParser.parse(file);

        JavaClassStats stats = new JavaClassStats();
        fileAst.accept(new StatsVisitor(stats), null);

        return stats;
    }

    public static @NotNull String getMethodAst(@NotNull String methodName, @NotNull File file) throws WrongExtensionException, FileNotFoundException {
        CompilationUnit fileAst = JavaParser.parse(file);
        fileAst.accept(new StatsVisitor(methodName), null);

        return "";
    }
}