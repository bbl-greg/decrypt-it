package dev.gregdrake.domain.runningvalue.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValueDTO {
    @JsonProperty
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String text) {
        this.value = text;
    }
}
