package com.test.danstest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.test.danstest.models.dtos.JobDetailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Value("${dansmultipro.base.url}")
    private String baseUrl;

    @Value("${dansmultipro.job.list}")
    private String listEndpoint;

    @Value("${dansmultipro.job.detail}")
    private String detailEndpoint;

    ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<Object> list() throws UnirestException, JsonProcessingException {
        HttpResponse<String> httpResponse = Unirest.get(baseUrl+listEndpoint).asString();
        List<JobDetailDTO> result = objectMapper.readValue(httpResponse.getBody(), List.class);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<Object> getById(String id) throws JsonProcessingException, UnirestException {
        HttpResponse<String> httpResponse = Unirest.get(baseUrl+detailEndpoint+id).asString();
        JobDetailDTO result = objectMapper.readValue(httpResponse.getBody(), JobDetailDTO.class);

        return ResponseEntity.ok(result);
    }
}
