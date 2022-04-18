/* (C)2022 */
package dev.gregdrake;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotEmpty;

public class DecryptItApplicationConfiguration extends Configuration {

  @NotEmpty private String defaultName = "DecryptIt";

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

  @JsonProperty
  public void setDefaultName(String defaultName) {
    this.defaultName = defaultName;
  }
}
