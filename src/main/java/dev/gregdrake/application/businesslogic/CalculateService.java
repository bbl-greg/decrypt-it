/* (C)2022 */
package dev.gregdrake.application.businesslogic;

import com.google.inject.Inject;
import dev.gregdrake.application.businesslogic.interfaces.CalculateUseCase;
import dev.gregdrake.domain.runningvalues.entities.RunningValuesEntity;
import dev.gregdrake.domain.runningvalues.interfaces.RunningValues;
import dev.gregdrake.infastructure.interfaces.DataStore;

public class CalculateService implements CalculateUseCase {
  private final DataStore dataStore;

  @Inject
  CalculateService(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  @Override
  public RunningValues calculate(double input) {
    double squaredInput = Math.pow(input, 2);

    int latestCount = this.dataStore.getCount() + 1;
    double average = this.dataStore.getAverage();
    double sum = this.dataStore.getSum();
    double squaredSum = this.dataStore.getSquaredSum();

    double newSum = sum + input;
    double newSquaredSum = squaredSum + squaredInput;
    double newAvg = calculateAverage(latestCount, average, input);
    double newStdDev = calculateStdDeviation(newSquaredSum, newSum, latestCount);
    this.dataStore.updateAvg(newAvg);
    this.dataStore.updateStdDev(newStdDev);
    this.dataStore.updateCount(latestCount);
    this.dataStore.updateSum(newSum);
    this.dataStore.updateSquaredSum(newSquaredSum);
    return new RunningValuesEntity(newAvg, newStdDev);
  }

  public double calculateAverage(int count, double average, double input) {
    return average + (input - average) / count;
  }

  public double calculateStdDeviation(double squaredInputSum, double newSum, int count) {
    double stdDev = 0;
    double squaredNewSum = Math.pow(newSum, 2);
    if (count <= 1) {
      return stdDev;
    }
    return Math.sqrt((squaredInputSum - (squaredNewSum / count)) / (count));
  }
}
