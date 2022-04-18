/* (C)2022 */
package dev.gregdrake.application.resources.controllers.data.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputValueDTO {
  private String value;

  @JsonProperty
  public String getValue() {
    return this.value;
  }

  @JsonProperty
  public void setValue(String value) {
    this.value = value;
  }
}
