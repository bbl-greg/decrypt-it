/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValueDTO {
    @JsonProperty private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String text) {
        this.value = text;
    }
}
