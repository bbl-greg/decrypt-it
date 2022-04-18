/* (C)2022 */
package dev.gregdrake.application.resources.controllers.adapter;

import com.google.inject.Inject;
import dev.gregdrake.application.businesslogic.CryptoService;
import dev.gregdrake.application.businesslogic.interfaces.CalculateUseCase;
import dev.gregdrake.application.resources.controllers.data.interfaces.DecryptedOutputValue;
import dev.gregdrake.application.resources.controllers.data.interfaces.InputValueDTO;
import dev.gregdrake.application.resources.controllers.data.interfaces.RecalculatedOutputValue;
import dev.gregdrake.domain.runningvalues.interfaces.RunningValues;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/runningvalue")
@Produces(MediaType.APPLICATION_JSON)
public class RunningValueController {
  // Archetype for this resource doesn't fit neatly in CRUD - so convention dictates it is aligned
  // to a controller

  CalculateUseCase calculateService;
  CryptoService cryptoService;

  @Inject
  RunningValueController(
      final CalculateUseCase calculateService, final CryptoService cryptoService) {
    this.calculateService = calculateService;
    this.cryptoService = cryptoService;
  }

  @PUT()
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/push-and-recalculate")
  public RecalculatedOutputValue push(InputValueDTO value) {
    RunningValues runningValues =
        this.calculateService.calculate(Double.parseDouble(value.getValue()));
    System.out.println(
        "avg: " + runningValues.getAverage() + " std: " + runningValues.getStdDeviation());
    return new RecalculatedOutputValue(
        String.valueOf(runningValues.getAverage()),
        String.valueOf(runningValues.getStdDeviation()));
  }

  @PUT()
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/push-and-recalculate-and-encrypt")
  public RecalculatedOutputValue pushAndEncrypt(InputValueDTO value) {
    RunningValues runningValues =
        this.calculateService.calculate(Double.parseDouble(value.getValue()));
    System.out.println(
        "avg: " + runningValues.getAverage() + " std: " + runningValues.getStdDeviation());
    HashMap<String, String> encryptedRVs = cryptoService.encrypt(runningValues);
    System.out.println(
        "encrypt avg: "
            + encryptedRVs.get("Average")
            + " encrypt std: "
            + encryptedRVs.get("Std-Dev"));
    return new RecalculatedOutputValue(encryptedRVs.get("Average"), encryptedRVs.get("Std-Dev"));
  }

  @PUT()
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/decrypt")
  public DecryptedOutputValue decrypt(InputValueDTO value) {
    Double decryptedValue = cryptoService.decrypt(value.getValue());
    System.out.println("decryptedValue: " + decryptedValue);
    return new DecryptedOutputValue(String.valueOf(decryptedValue));
  }
}
