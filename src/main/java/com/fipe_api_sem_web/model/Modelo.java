package com.fipe_api_sem_web.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelo(@JsonAlias("modelos") Integer codigo,
                     @JsonAlias("nome") String nome) {

}
