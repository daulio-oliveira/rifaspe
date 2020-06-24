package br.rifas.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoStatus {

    LIVRE("L"),
    RESERVADO("R"),
    PAGO("P");

    public final String value;

    private TipoStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getName() {
        return getByValue(value).name();
    }


    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static TipoStatus getByName(String name) {
        return valueOf(name);
    }

    public static TipoStatus getByValue(String value) {
        if (value != null){
            for (int i = 0; i < TipoStatus.values().length; i++) {
                if (value.equals(TipoStatus.values()[i].value))
                    return TipoStatus.values()[i];
            }
        }
        return null;
    }
}