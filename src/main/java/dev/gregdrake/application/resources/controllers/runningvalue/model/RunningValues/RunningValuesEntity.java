/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.model.RunningValues;

public class RunningValuesEntity implements RunningValues {
    private final double average;
    private final double stdDeviation;

    public RunningValuesEntity(double average, double stdDeviation) {
        this.average = average;
        this.stdDeviation = stdDeviation;
    }

    @Override
    public double getAverage() {
        return this.average;
    }

    @Override
    public double getStdDeviation() {
        return this.stdDeviation;
    }
}
