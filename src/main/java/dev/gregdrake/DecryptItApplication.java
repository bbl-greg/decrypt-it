/* (C)2022 */
package dev.gregdrake;

import dev.gregdrake.application.configuration.CalculateModule;
import dev.gregdrake.application.configuration.CryptoModule;
import dev.gregdrake.application.configuration.DataStoreModule;
import dev.gregdrake.application.configuration.VaultStoreModule;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class DecryptItApplication extends Application<DecryptItApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new DecryptItApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<DecryptItApplicationConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)));

        bootstrap.addBundle(
                GuiceBundle.builder()
                        .enableAutoConfig(getClass().getPackage().getName())
                        .modules(
                                new CalculateModule(),
                                new DataStoreModule(),
                                new VaultStoreModule(),
                                new CryptoModule())
                        .build());
    }

    @Override
    public void run(DecryptItApplicationConfiguration configuration, Environment environment)
            throws Exception {}
}
