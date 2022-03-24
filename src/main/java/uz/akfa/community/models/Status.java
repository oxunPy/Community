package uz.akfa.community.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Status {
    MARRIED("code1"), NOT_MARRIED("code2"), GRADUATED("code3"), STUDYING("code4");

    private String code;

    private Status(String code){
        this.code = code;
    }

//    @JsonCreator
    public static Status decode(final String code){
        return Stream.of(Status.values()).filter(status -> status.code.equals(code)).findFirst().orElse(null);
    }

//    @JsonValue
    public String getCode() {
        return code;
    }
}
