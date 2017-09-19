package ru.spbau.shavkunov.metrics;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    public void visit(FieldDeclaration field, Void arg)  {
        super.visit(field, arg);
    }
}
