/* (C)2022 */
package dev.gregdrake.application.resources.controllers;

import com.google.inject.Inject;
import dev.gregdrake.application.resources.controllers.runningvalue.application.CryptoService;
import dev.gregdrake.application.resources.controllers.runningvalue.application.dto.ValueDTO;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.CalculateUseCase;
import dev.gregdrake.application.resources.controllers.runningvalue.model.RunningValues.RunningValues;
import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/runningvalue")
@Produces(MediaType.TEXT_PLAIN)
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
    public String push(ValueDTO value) {
        RunningValues toConsole =
                this.calculateService.calculate(Double.parseDouble(value.getValue()));
        System.out.println(
                "avg: " + toConsole.getAverage() + " std: " + toConsole.getStdDeviation());
        return "Push";
    }

    @PUT()
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/push-and-recalculate-and-encrypt")
    public String pushAndEncrypt(ValueDTO value) {
        RunningValues calculatedRVs =
                this.calculateService.calculate(Double.parseDouble(value.getValue()));
        System.out.println(
                "avg: " + calculatedRVs.getAverage() + " std: " + calculatedRVs.getStdDeviation());
        HashMap<String, String> encryptedRVs = cryptoService.encrypt(calculatedRVs);
        System.out.println(
                "encrypt avg: "
                        + encryptedRVs.get("Average")
                        + " encrypt std: "
                        + encryptedRVs.get("Std-Dev"));
        return "Push and Encrypt";
    }

    @PUT()
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/decrypt")
    public String decrypt(ValueDTO value) {
        Double decryptedValue = cryptoService.decrypt(value.getValue());
        System.out.println("decryptedValue: " + decryptedValue);
        return "Decrypt";
    }
}
