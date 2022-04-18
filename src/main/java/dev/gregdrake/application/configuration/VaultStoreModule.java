/* (C)2022 */
package dev.gregdrake.application.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import dev.gregdrake.infastructure.adapters.LocalVaultStore;
import dev.gregdrake.infastructure.interfaces.VaultStore;

public class VaultStoreModule extends AbstractModule {
  @Override
  protected void configure() {
    super.configure();
    bind(VaultStore.class).to(LocalVaultStore.class).in(Scopes.SINGLETON);
  }
}
