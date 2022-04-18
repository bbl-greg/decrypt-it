/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.infastructure;

import dev.gregdrake.application.resources.controllers.runningvalue.model.RunningValues.RunningValues;
import java.util.HashMap;

public interface CryptoUseCase {
    HashMap<String, String> encrypt(RunningValues input);

    double decrypt(String input);
}
