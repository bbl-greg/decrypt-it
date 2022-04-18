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
    //Get current values from dataStore
    int latestCount = this.dataStore.getCount() + 1;
    double average = this.dataStore.getAverage();
    double sum = this.dataStore.getSum();
    double squaredSum = this.dataStore.getSquaredSum();

    //Calculated values for use finding new stdDev
    double newSum = sum + input;
    double squaredInput = Math.pow(input, 2);
    double newSquaredSum = squaredSum + squaredInput;

    //Main calculations to find average and std deviation
    double newAvg = calculateAverage(latestCount, average, input);
    double newStdDev = calculateStdDeviation(newSquaredSum, newSum, latestCount);

    //Update the singleton dataStore with new values to use during next request
    this.dataStore.updateAvg(newAvg);
    this.dataStore.updateStdDev(newStdDev);
    this.dataStore.updateCount(latestCount);
    this.dataStore.updateSum(newSum);
    this.dataStore.updateSquaredSum(newSquaredSum);

    //Return calculated running values
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
