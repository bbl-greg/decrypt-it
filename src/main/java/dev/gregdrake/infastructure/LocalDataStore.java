/* (C)2022 */
package dev.gregdrake.infastructure;

import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.DataStore;

public class LocalDataStore implements DataStore {
    private double average;
    private double stdDev;

    private double sum;

    private double squaredSum;
    private int count;

    LocalDataStore() {
        this.average = 0.0;
        this.stdDev = 0.0;
        this.sum = 0.0;
        this.count = 0;
        this.squaredSum = 0.0;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public double getSum() {
        return this.sum;
    }

    @Override
    public double getSquaredSum() {
        return this.squaredSum;
    }

    @Override
    public double getStdDev() {
        return this.stdDev;
    }

    @Override
    public double getAverage() {
        return this.average;
    }

    @Override
    public void updateCount(int value) {
        this.count = value;
    }

    @Override
    public void updateSum(double value) {
        this.sum = value;
    }

    @Override
    public void updateSquaredSum(double value) {
        this.squaredSum = value;
    }

    @Override
    public void updateStdDev(double value) {
        this.stdDev = value;
    }

    @Override
    public void updateAvg(double value) {
        this.average = value;
    }
}
