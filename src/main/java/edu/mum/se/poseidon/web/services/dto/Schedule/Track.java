package edu.mum.se.poseidon.web.services.dto.Schedule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Track {

    FPP("fpp"),
    MPP("mpp");

    private String value;

    Track(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Track fromValue(String key) {
        if(key != null) {
            for(Track type : values()) {
                if(type.value.equalsIgnoreCase(key))
                    return type;
            }
        }
        return null;
    }

    @JsonValue
    public String toValue() {
        return value;
    }
}