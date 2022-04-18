/* (C)2022 */
package dev.gregdrake.application.configuration;

import com.google.inject.AbstractModule;
import dev.gregdrake.application.resources.controllers.runningvalue.application.CryptoService;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.CryptoUseCase;

public class CryptoModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        bind(CryptoUseCase.class).to(CryptoService.class);
    }
}
