package ru.spbau.shavkunov.metrics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;

public class StatsCollector {
    private static final @NotNull String JAVA_EXTENSION = ".java";

    public static @NotNull JavaClassStats getStats(File file) throws WrongExtensionException, FileNotFoundException {
        if (!file.getPath().endsWith(JAVA_EXTENSION)) {
            throw new WrongExtensionException();
        }

        CompilationUnit fileAst = JavaParser.parse(file);

        JavaClassStats stats = new JavaClassStats();
        fileAst.accept(new ClassVisitor(stats), null);

        return stats;
    }
}
