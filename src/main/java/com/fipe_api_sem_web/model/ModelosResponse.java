package com.fipe_api_sem_web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResponse(List<Modelo> modelos) {
}
