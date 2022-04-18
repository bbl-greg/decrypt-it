/* (C)2022 */
package dev.gregdrake.application.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.gregdrake.infastructure.adapters.LocalDataStore;
import dev.gregdrake.infastructure.interfaces.DataStore;

public class DataStoreModule extends AbstractModule {
  @Override
  protected void configure() {
    super.configure();
    bind(DataStore.class).to(LocalDataStore.class).in(Scopes.SINGLETON);
  }
}
