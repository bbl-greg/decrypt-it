/* (C)2022 */
package dev.gregdrake.infastructure.interfaces;

public interface DataStore {
  int getCount();

  double getStdDev();

  double getAverage();

  double getSum();

  double getSquaredSum();

  void updateCount(int value);

  void updateStdDev(double value);

  void updateAvg(double value);

  void updateSum(double value);

  void updateSquaredSum(double value);
}
