package uz.akfa.community.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum GenderType {
    MALE("code1"), FEMALE("code2"), OTHER("code3");

    private String code;

    private GenderType(String code){
        this.code = code;
    }

//    @JsonCreator
    public static GenderType decode(final String code){
        return Stream.of(GenderType.values())
                        .filter(genderType -> genderType.code.equals(code))
                        .findFirst().orElse(null);
    }

//    @JsonValue
    public String getCode(){
        return code;
    }
}
