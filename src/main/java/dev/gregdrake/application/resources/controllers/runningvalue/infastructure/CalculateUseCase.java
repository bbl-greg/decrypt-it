/* (C)2022 */
package dev.gregdrake.domain.runningvalue.infastructure;

import dev.gregdrake.domain.runningvalue.model.RunningValues.RunningValues;

public interface CalculateUseCase {
    RunningValues calculate(double input);
}
