/* (C)2022 */
package dev.gregdrake.application.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.gregdrake.application.resources.controllers.runningvalue.application.CalculateService;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.CalculateUseCase;

public class CalculateModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        bind(CalculateUseCase.class).to(CalculateService.class).in(Scopes.SINGLETON);
    }
}
