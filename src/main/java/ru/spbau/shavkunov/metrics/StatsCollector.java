package ru.spbau.shavkunov.metrics;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.shavkunov.metrics.visitors.MethodVisitor;
import ru.spbau.shavkunov.metrics.visitors.StatsVisitor;
import ru.spbau.shavkunov.metrics.exceptions.WrongExtensionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ru.spbau.shavkunov.metrics.Constants.JAVA_EXTENSION;

public class StatsCollector {
    public static @NotNull JavaClassStats getStats(@NotNull File file)
                                          throws WrongExtensionException, FileNotFoundException {
        if (!file.getPath().endsWith(JAVA_EXTENSION)) {
            throw new WrongExtensionException();
        }

        CompilationUnit fileAst = JavaParser.parse(file);

        JavaClassStats stats = new JavaClassStats();
        fileAst.accept(new StatsVisitor(stats), null);

        return stats;
    }

    public static @Nullable String getMethodAst(@NotNull String methodName, @NotNull File file)
                                   throws WrongExtensionException, FileNotFoundException {
        if (!file.getPath().endsWith(JAVA_EXTENSION)) {
            throw new WrongExtensionException();
        }

        CompilationUnit fileAst = JavaParser.parse(file);

        List<String> methodNames = Collections.singletonList(methodName);
        MethodVisitor visitor = new MethodVisitor(methodNames);
        fileAst.accept(visitor, null);

        String result = null;
        Map<String, String> methodsAst = visitor.getMethodsAst();

        if (methodsAst.containsKey(methodName)) {
            result = methodsAst.get(methodName);
        }

        return result;
    }

    public static @NotNull Map<String, String> getMethodsAst(@NotNull List<String> methodNames, @NotNull File file)
                                               throws WrongExtensionException, FileNotFoundException {
        if (!file.getPath().endsWith(JAVA_EXTENSION)) {
            throw new WrongExtensionException();
        }

        CompilationUnit fileAst = JavaParser.parse(file);

        MethodVisitor visitor = new MethodVisitor(methodNames);
        fileAst.accept(visitor, null);

        return visitor.getMethodsAst();
    }
}