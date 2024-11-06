package com.fipe_api_sem_web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipe_api_sem_web.model.Marca;

import java.util.ArrayList;
import java.util.List;

public class ConverteDados {
    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T converteJsonParaClasse(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    public static <T> List<T> converteJsonParaClasse(String json, TypeReference<List<T>> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
