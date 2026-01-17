package com.app.monacolibrary.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LENGUAJE {
    @JsonProperty("en")
    EN,
    @JsonProperty("es")
    ES,
    @JsonProperty("fr")
    FR,
    @JsonProperty("de")
    DE;

    public static LENGUAJE toString(String lenguaje) throws Exception {
        return switch (lenguaje){
          case "en" -> LENGUAJE.EN;
          case "es" -> LENGUAJE.ES;
          case "fr" -> LENGUAJE.FR;
          case "de" -> LENGUAJE.DE;
            default -> throw new Exception("Lenguaje no identificado");
        };
    }

}
