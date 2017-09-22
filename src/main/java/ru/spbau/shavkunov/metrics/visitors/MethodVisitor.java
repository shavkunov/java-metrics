package ru.spbau.shavkunov.metrics.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.ConcreteSyntaxModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodVisitor extends VoidVisitorAdapter<Void> {
    private @NotNull List<String> methodNames;
    private @NotNull Map<String, String> methodsAst;

    public MethodVisitor(@NotNull List<String> methodNames) {
        this.methodNames = methodNames;
        methodsAst = new HashMap<>();
    }

    @Override
    public void visit(MethodDeclaration method, Void arg) {
        for (String methodName : methodNames) {
            if (methodName.equals(method.getName().asString())) {
                methodsAst.put(methodName, getMethodAst(method));
            }
        }

        super.visit(method, arg);
    }

    public @NotNull Map<String, String> getMethodsAst() {
        return methodsAst;
    }

    private static @NotNull String getMethodAst(MethodDeclaration declaration) {
        // process AST
        String stringAstRepresentation = ConcreteSyntaxModel.genericPrettyPrint(declaration);

        return stringAstRepresentation;
    }
}
