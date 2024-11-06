package com.fipe_api_sem_web.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Marca(@JsonAlias("codigo") Integer codigo,
                    @JsonAlias("nome") String nome) {

}
