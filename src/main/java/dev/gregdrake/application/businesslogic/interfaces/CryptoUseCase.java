/* (C)2022 */
package dev.gregdrake.application.businesslogic.interfaces;

import dev.gregdrake.domain.runningvalues.interfaces.RunningValues;

import java.util.HashMap;

public interface CryptoUseCase {
  HashMap<String, String> encrypt(RunningValues input);

  double decrypt(String input);
}
