/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.infastructure;

import dev.gregdrake.application.resources.controllers.runningvalue.model.RunningValues.RunningValues;

public interface CalculateUseCase {
    RunningValues calculate(double input);
}
