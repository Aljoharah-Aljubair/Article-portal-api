package com.artical.portal.api.service.impl;


import com.artical.portal.api.dto.TranslationResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Value("${huggingface.api.url}")
    private String apiUrl;

    public TranslationResponse translateText(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = String.format("{ \"inputs\": \"%s\" }", text);
        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        System.out.println("Response JSON: " + response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        TranslationResponse translationResponse = new TranslationResponse();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            if (jsonNode.isArray() && jsonNode.size() > 0) {
                JsonNode firstElement = jsonNode.get(0);
                translationResponse.setTranslation_text(firstElement.get("translation_text").asText());
            } else {
                System.out.println("No translation found in the response.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return translationResponse;
    }
}
