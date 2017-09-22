package ru.spbau.shavkunov.metrics.visitors;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.ConcreteSyntaxModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.spbau.shavkunov.metrics.JavaClassStats;

import java.util.List;
import java.util.stream.Collectors;

public class StatsVisitor extends VoidVisitorAdapter<Void> {
    private @NotNull JavaClassStats stats;

    public StatsVisitor(@NotNull JavaClassStats stats) {
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