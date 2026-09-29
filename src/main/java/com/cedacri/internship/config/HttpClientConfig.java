package com.cedacri.internship.config;

import java.net.http.HttpClient;
import java.time.Duration;

public class HttpClientConfig {

    private final HttpClient httpClient;

    public HttpClientConfig() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }
}
