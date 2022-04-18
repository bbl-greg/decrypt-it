package dev.gregdrake.application.resources.controllers.data.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DecryptedOutputValue {
  private String decryptedValue;

  public DecryptedOutputValue(String decryptedValue) {
    this.decryptedValue = decryptedValue;
  }

  @JsonProperty
  public String getDecryptedValue() {
    return decryptedValue;
  }

  @JsonProperty
  public void setDecryptedValue(String decryptedValue) {
    this.decryptedValue = decryptedValue;
  }
}
