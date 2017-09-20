package ru.spbau.shavkunov.metrics;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ClassVisitor extends VoidVisitorAdapter<Void> {
    private @Nullable JavaClassStats stats;

    public ClassVisitor(@NotNull JavaClassStats stats) {
        this.stats = stats;
    }

    @Override
    public void visit(MethodDeclaration method, Void arg) {
        stats.appendMethod(method.getName().asString());
        super.visit(method, arg);
    }

    @Override
    public void visit(FieldDeclaration field, Void arg) {
        List<String> fields = field.getVariables()
                                   .stream()
                                   .map(var -> var.getName().asString())
                                   .collect(Collectors.toList());
        stats.appendFields(fields);
        super.visit(field, arg);
    }

    @Override
    public void visit(VariableDeclarator declarator, Void arg) {
        stats.appendVariable(declarator.getName().asString());
        super.visit(declarator, arg);
    }
}