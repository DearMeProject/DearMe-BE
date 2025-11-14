package com.dearme.backend.dearmebe.domain.external.ai;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AiClient {

    private final Client client;

    public AiClient(@Value("${gemini.api-key}") String apiKey) {
        this.client = Client.builder().apiKey(apiKey).build();
    }

    public String requestCounsel(String prompt) {
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                prompt, null
        );
        return response.text();
    }

}
