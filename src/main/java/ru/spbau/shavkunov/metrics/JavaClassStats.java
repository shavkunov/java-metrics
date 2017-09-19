package ru.spbau.shavkunov.metrics;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class JavaClassStats {
    private int methodsAmount;
    private int sumLengthsOfMethods;

    // TODO later
    private int localVarsAmount;
    private int sumLengthsOfLocalVars;

    private int fieldsAmount;
    private int sumLengthsOfFields;
    // TODO : moar stats

    public JavaClassStats() {}

    public void appendMethod(@NotNull String methodName) {
        methodsAmount++;
        sumLengthsOfMethods += methodName.length();
    }

    public void appendField(@NotNull String fieldName) {
        fieldsAmount++;
        sumLengthsOfFields += fieldName.length();
    }
}
