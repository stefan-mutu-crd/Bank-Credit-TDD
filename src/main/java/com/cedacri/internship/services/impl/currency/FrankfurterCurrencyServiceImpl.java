package com.cedacri.internship.services.impl.currency;

import com.cedacri.internship.services.CurrencyService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class FrankfurterCurrencyServiceImpl implements CurrencyService {

    private final String BASE_URL;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FrankfurterCurrencyServiceImpl(String baseUrl, HttpClient httpClient) {
        BASE_URL = baseUrl;
        this.httpClient = httpClient;
    }

    @Override
    public Map<String, Double> getRates() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        CurrencyResponse httpResponse = objectMapper.readValue(response.body(), CurrencyResponse.class);
        return httpResponse.rates;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record CurrencyResponse(
            BigDecimal amount,
            String base,
            String date,
            Map<String, Double> rates
    ) {
    }
}
