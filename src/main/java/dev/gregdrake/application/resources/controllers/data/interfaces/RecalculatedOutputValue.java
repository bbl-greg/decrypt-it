package dev.gregdrake.application.resources.controllers.data.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecalculatedOutputValue {
  private String average;
  private String stdDeviation;

  public RecalculatedOutputValue(String average, String stdDeviation) {
    this.average = average;
    this.stdDeviation = stdDeviation;
  }

  @JsonProperty
  public String getAverage() {
    return average;
  }

  @JsonProperty
  public void setAverage(String average) {
    this.average = average;
  }

  @JsonProperty
  public String getStdDeviation() {
    return stdDeviation;
  }

  @JsonProperty
  public void setStdDeviation(String stdDeviation) {
    this.stdDeviation = stdDeviation;
  }
}
