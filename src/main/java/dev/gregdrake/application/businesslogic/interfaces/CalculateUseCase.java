/* (C)2022 */
package dev.gregdrake.application.businesslogic.interfaces;

import dev.gregdrake.domain.runningvalues.interfaces.RunningValues;

public interface CalculateUseCase {
  RunningValues calculate(double input);
}
