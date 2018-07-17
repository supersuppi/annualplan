package com.gxh.apserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {
    @JsonProperty("description")
    private String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
