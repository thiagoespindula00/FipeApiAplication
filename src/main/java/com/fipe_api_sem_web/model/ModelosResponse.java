package com.fipe_api_sem_web.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResponse(@JsonAlias("modelos")ArrayList<Modelo> modelos) {
}
