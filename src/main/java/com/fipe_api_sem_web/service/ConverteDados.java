package com.fipe_api_sem_web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fipe_api_sem_web.model.Marca;

import java.util.ArrayList;
import java.util.Collection;
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

    public static <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
