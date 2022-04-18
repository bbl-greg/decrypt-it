/* (C)2022 */
package dev.gregdrake.application.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.DataStore;
import dev.gregdrake.infastructure.LocalDataStore;

public class DataStoreModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();
        bind(DataStore.class).to(LocalDataStore.class).in(Scopes.SINGLETON);
    }
}
