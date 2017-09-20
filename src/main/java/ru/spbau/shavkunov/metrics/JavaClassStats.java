package ru.spbau.shavkunov.metrics;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class JavaClassStats {
    private int methodsAmount;
    private int sumLengthsOfMethods;

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

    public void appendFields(@NotNull List<String> fields) {
        fieldsAmount += fields.size();
        fields.forEach(field -> sumLengthsOfFields += field.length());
    }

    public void appendVariable(@NotNull String variable) {
        localVarsAmount++;
        sumLengthsOfLocalVars += variable.length();
    }

    public int getLocalVarsAmount() {
        // fields are alse variables
        return localVarsAmount - fieldsAmount;
    }

    public int getSumLengthsOfLocalVars() {
        // fields are alse variables
        return sumLengthsOfLocalVars - sumLengthsOfFields;
    }
}